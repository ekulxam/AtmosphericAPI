/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.mixin.item.spyglass.client;

//~ if >=1.21.11 'GuiGraphics' -> 'GuiGraphicsExtractor' {
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import org.objectweb.asm.Opcodes;
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

    //~ if >=1.21.11 'renderSpyglassOverlay' -> 'extractSpyglassOverlay' {
    @Inject(method = "extractSpyglassOverlay", at = @At("HEAD"), cancellable = true)
    private void noKaleidoscopeOverlay(GuiGraphicsExtractor context, float scale, CallbackInfo ci) {
        ItemStack activeStack = this.minecraft.player.getUseItem();
        if (!(activeStack.getItem() instanceof IAmASpyglassItem spyglass)) {
            return;
        }
        if (!AtmosphericSpecialItemRenderHandlerImpl.getOverlayHandler().get(spyglass).apply(activeStack)) {
            ci.cancel();
        }
    }

    @ModifyExpressionValue(method = "extractSpyglassOverlay", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/Gui;SPYGLASS_SCOPE_LOCATION:Lnet/minecraft/resources/Identifier;", opcode = Opcodes.GETSTATIC))
    private Identifier renderKaleidoscopeOverlayInstead(Identifier original) {
        ItemStack activeStack = this.minecraft.player.getUseItem();
        return activeStack.getItem() instanceof IAmASpyglassItem spyglass ? spyglass.getOverlay(activeStack) : original;
    }
    //~}
}
//~}