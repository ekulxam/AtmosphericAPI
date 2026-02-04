/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.mixin.compat.modmenu.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.packs.PackSelectionModel;
import net.minecraft.client.gui.screens.packs.PackSelectionScreen;
import net.minecraft.client.gui.screens.packs.TransferableSelectionList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.atmospheric_api.not_mixin.compat.config.ConfigPackScreen;

import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

@Mixin(value = PackSelectionScreen.class, priority = 1250) // 1500 felt a little too high
public class PackScreenMixin {

    @Shadow private Button doneButton;

    //? if <=1.21.8 {
    @WrapOperation(method = "updateList", at = @At(value = "INVOKE", target = "Ljava/util/stream/Stream;forEach(Ljava/util/function/Consumer;)V"))
    private <T extends PackSelectionModel.Entry> void removeUnwantedPacksFromConfig(Stream<T> instance, Consumer<? super T> consumer, Operation<Void> original) {
        if (!((PackSelectionScreen) (Object) this instanceof ConfigPackScreen configPackScreen)) {
            original.call(instance, consumer);
            return;
        }
        Set<String> allowedPacks = configPackScreen.getAllowedPacks();
        original.call(instance.filter(pack -> allowedPacks.contains(pack.getId())), consumer);
    }
    //?} else {
    /*@WrapOperation(method = "filterEntries", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/packs/TransferableSelectionList;updateList(Ljava/util/stream/Stream;Lnet/minecraft/client/gui/screens/packs/PackSelectionModel$EntryBase;)V"))
    private <T extends PackSelectionModel.Entry> void removeUnwantedPacksFromConfig(TransferableSelectionList instance, Stream<T> stream, PackSelectionModel.EntryBase entryBase, Operation<Void> original) {
        if (!((PackSelectionScreen) (Object) this instanceof ConfigPackScreen configPackScreen)) {
            original.call(instance, stream, entryBase);
            return;
        }
        Set<String> allowedPacks = configPackScreen.getAllowedPacks();
        original.call(instance, stream.filter(pack -> allowedPacks.contains(pack.getId())), entryBase);
    }
     *///?}

    @Inject(method = "populateLists", at = @At("RETURN"))
    private void doneButtonAlwaysActiveInConfig(CallbackInfo ci) {
        if (!((PackSelectionScreen) (Object) this instanceof ConfigPackScreen)) {
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
