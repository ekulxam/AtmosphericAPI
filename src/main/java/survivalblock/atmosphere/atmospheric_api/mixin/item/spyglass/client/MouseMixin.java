/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.mixin.item.spyglass.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.IAmASpyglassItem;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.client.AtmosphericSpecialItemRenderHandlerImpl;

@Mixin(MouseHandler.class)
public class MouseMixin {

    @Shadow @Final private Minecraft minecraft;

    @ModifyExpressionValue(method = "turnPlayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isScoping()Z"))
    private boolean changeMouseSmoothnessInZoom(boolean original) {
        ItemStack activeStack = this.minecraft.player.getUseItem();
        if (!(activeStack.getItem() instanceof IAmASpyglassItem spyglass)) {
            return original;
        }
        if (!AtmosphericSpecialItemRenderHandlerImpl.getZoomHandler().get(spyglass).apply(activeStack)) {
            return false;
        }
        return original;
    }
}
