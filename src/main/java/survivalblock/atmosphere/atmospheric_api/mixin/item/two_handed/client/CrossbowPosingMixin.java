/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.mixin.item.two_handed.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.model.AnimationUtils;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.TwoHandedItem;

@Mixin(AnimationUtils.class)
public class CrossbowPosingMixin {

    // I HATE RENDERING I HATE RENDERING I HATE RENDERING I HATE RENDERING I HATE RENDERING
    // I strongly dislike rendering
    @ModifyExpressionValue(method = "animateCrossbowHold", at = @At(value = "FIELD", target = "Lnet/minecraft/client/model/geom/ModelPart;xRot:F", opcode = Opcodes.GETFIELD))
    private static float whyDidIEvenNeedToMakeThisMixinAgain(float original) {
        return TwoHandedItem.TwoHandedRenderType.longswordPosing ? TwoHandedItem.TwoHandedRenderType.angle : original;
    }
}
