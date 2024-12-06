package survivalblock.atmosphere.atmospheric_api.mixin.item.client.two_handed;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.render.entity.model.CrossbowPosing;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.TwoHandedItem;

@Mixin(CrossbowPosing.class)
public class CrossbowPosingMixin {

    // I HATE RENDERING I HATE RENDERING I HATE RENDERING I HATE RENDERING I HATE RENDERING
    // I strongly dislike rendering
    @ModifyExpressionValue(method = "hold", at = @At(value = "FIELD", target = "Lnet/minecraft/client/model/ModelPart;pitch:F", opcode = Opcodes.GETFIELD))
    private static float whyDidIEvenNeedToMakeThisMixinAgain(float original) {
        return TwoHandedItem.TwoHandedRenderType.longswordPosing ? TwoHandedItem.TwoHandedRenderType.angle : original;
    }
}
