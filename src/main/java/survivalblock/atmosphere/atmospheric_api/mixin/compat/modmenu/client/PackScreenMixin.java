package survivalblock.atmosphere.atmospheric_api.mixin.compat.modmenu.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.screen.pack.PackScreen;
import net.minecraft.client.gui.screen.pack.ResourcePackOrganizer;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.atmospheric_api.not_mixin.compat.config.ConfigPackScreen;

import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

@Mixin(value = PackScreen.class, priority = 1250) // 1500 felt a little too high
public class PackScreenMixin {

    @Shadow private ButtonWidget doneButton;

    @WrapOperation(method = "updatePackList", at = @At(value = "INVOKE", target = "Ljava/util/stream/Stream;forEach(Ljava/util/function/Consumer;)V"))
    private <T extends ResourcePackOrganizer.Pack> void removeUnwantedPacksFromConfig(Stream<T> instance, Consumer<? super T> consumer, Operation<Void> original) {
        if (!((PackScreen) (Object) this instanceof ConfigPackScreen configPackScreen)) {
            original.call(instance, consumer);
            return;
        }
        Set<String> allowedPacks = configPackScreen.getAllowedPacks();
        original.call(instance.filter(pack -> allowedPacks.contains(pack.getName())), consumer);
    }

    @Inject(method = "updatePackLists", at = @At("RETURN"))
    private void doneButtonAlwaysActiveInConfig(CallbackInfo ci) {
        if (!((PackScreen) (Object) this instanceof ConfigPackScreen)) {
            return;
        }
        this.doneButton.active = true;
        /* I think this is basically equivalent to
        super.updatePackLists();
        this.doneButton.active = true;
        in an override of this method, but sadly this method is private (it's mixin time)
         */
    }
}
