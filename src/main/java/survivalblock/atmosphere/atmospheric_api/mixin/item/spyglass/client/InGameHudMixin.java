package survivalblock.atmosphere.atmospheric_api.mixin.item.spyglass.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.IAmASpyglassItem;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.client.AtmosphericSpecialItemRenderHandlerImpl;

@Mixin(value = InGameHud.class, priority = 0)
public class InGameHudMixin {

    @Shadow @Final private MinecraftClient client;

    @Inject(method = "renderSpyglassOverlay", at = @At("HEAD"), cancellable = true)
    private void noKaleidoscopeOverlay(DrawContext context, float scale, CallbackInfo ci) {
        ItemStack activeStack = this.client.player.getActiveItem();
        if (!(activeStack.getItem() instanceof IAmASpyglassItem spyglass)) {
            return;
        }
        if (!AtmosphericSpecialItemRenderHandlerImpl.getOverlayHandler().get(spyglass).apply(activeStack)) {
            ci.cancel();
        }
    }

    @ModifyExpressionValue(method = "renderSpyglassOverlay", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/hud/InGameHud;SPYGLASS_SCOPE:Lnet/minecraft/util/Identifier;"))
    private Identifier renderKaleidoscopeOverlayInstead(Identifier original) {
        ItemStack activeStack = this.client.player.getActiveItem();
        return activeStack.getItem() instanceof IAmASpyglassItem spyglass ? spyglass.getOverlay(activeStack) : original;
    }
}
