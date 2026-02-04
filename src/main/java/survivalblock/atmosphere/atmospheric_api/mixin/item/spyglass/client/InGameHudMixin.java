/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.mixin.item.spyglass.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.IAmASpyglassItem;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.client.AtmosphericSpecialItemRenderHandlerImpl;

@Mixin(value = Gui.class, priority = 0)
public class InGameHudMixin {

    @Shadow @Final private Minecraft minecraft;

    @Inject(method = "renderSpyglassOverlay", at = @At("HEAD"), cancellable = true)
    private void noKaleidoscopeOverlay(GuiGraphics context, float scale, CallbackInfo ci) {
        ItemStack activeStack = this.minecraft.player.getUseItem();
        if (!(activeStack.getItem() instanceof IAmASpyglassItem spyglass)) {
            return;
        }
        if (!AtmosphericSpecialItemRenderHandlerImpl.getOverlayHandler().get(spyglass).apply(activeStack)) {
            ci.cancel();
        }
    }

    @ModifyExpressionValue(method = "renderSpyglassOverlay", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/Gui;SPYGLASS_SCOPE_LOCATION:Lnet/minecraft/resources/ResourceLocation;"))
    private ResourceLocation renderKaleidoscopeOverlayInstead(ResourceLocation original) {
        ItemStack activeStack = this.minecraft.player.getUseItem();
        return activeStack.getItem() instanceof IAmASpyglassItem spyglass ? spyglass.getOverlay(activeStack) : original;
    }
}
