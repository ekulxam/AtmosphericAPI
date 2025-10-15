package survivalblock.atmosphere.atmospheric_api.mixin.item.two_handed.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
/*? =1.21.1 {*/ import net.minecraft.client.render.entity.model.CrossbowPosing; /*?} else {*/ /*import net.minecraft.client.render.entity.model.ArmPosing; *//*?}*/
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.TwoHandedItem;

@Mixin(/*? =1.21.1 {*/ CrossbowPosing.class /*?} else {*/ /*ArmPosing.class *//*?}*/)
public class CrossbowPosingMixin {

    // I HATE RENDERING I HATE RENDERING I HATE RENDERING I HATE RENDERING I HATE RENDERING
    // I strongly dislike rendering
    @ModifyExpressionValue(method = "hold", at = @At(value = "FIELD", target = "Lnet/minecraft/client/model/ModelPart;pitch:F", opcode = Opcodes.GETFIELD))
    private static float whyDidIEvenNeedToMakeThisMixinAgain(float original) {
        return TwoHandedItem.TwoHandedRenderType.longswordPosing ? TwoHandedItem.TwoHandedRenderType.angle : original;
    }
}
