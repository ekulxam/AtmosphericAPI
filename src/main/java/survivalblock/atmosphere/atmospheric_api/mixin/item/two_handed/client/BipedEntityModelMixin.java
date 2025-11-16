package survivalblock.atmosphere.atmospheric_api.mixin.item.two_handed.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
//? if >=1.21.2
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.atmospheric_api.access.WaitingOnFabricRenderState;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.TwoHandedItem;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.client.AtmosphericSpecialItemRenderHandlerImpl;

import java.util.Objects;

@Mixin(HumanoidModel.class)
public class BipedEntityModelMixin {

    @WrapOperation(method = {"poseLeftArm", "poseRightArm"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/AnimationUtils;animateCrossbowHold(Lnet/minecraft/client/model/geom/ModelPart;Lnet/minecraft/client/model/geom/ModelPart;Lnet/minecraft/client/model/geom/ModelPart;Z)V"))
    private void doSomeCursedLongswordPosing(
            ModelPart holdingArm, ModelPart otherArm, ModelPart head, boolean rightArmed, Operation<Void> original, /*? =1.21.1 {*/  /*LivingEntity living *//*?} else {*/ HumanoidRenderState state /*?}*/
    ) {
        ItemStack twoHandedStack = null;
        TwoHandedItem twoHandedItem = null;
        ItemStack mainHandStack = /*? =1.21.1 {*/  /*living.getMainHandItem() *//*?} else {*/ ((WaitingOnFabricRenderState) state).atmospheric_api$getMainHandStack() /*?}*/;
        if (!mainHandStack.isEmpty() && mainHandStack.getItem() instanceof TwoHandedItem twoHandedItem1) {
            twoHandedItem = twoHandedItem1;
            twoHandedStack = mainHandStack;
        } else {
            ItemStack offHandStack = /*? =1.21.1 {*/  /*living.getOffhandItem() *//*?} else {*/ ((WaitingOnFabricRenderState) state).atmospheric_api$getOffHandStack() /*?}*/;
            if (!offHandStack.isEmpty() && offHandStack.getItem() instanceof TwoHandedItem twoHandedItem1) {
                twoHandedItem = twoHandedItem1;
                twoHandedStack = offHandStack;
            }
        }
        if (twoHandedItem != null && AtmosphericSpecialItemRenderHandlerImpl.getTwoHandedHandler().get(twoHandedItem).apply(twoHandedStack) && Objects.equals(twoHandedItem.getTwoHandedRenderType(twoHandedStack), TwoHandedItem.TwoHandedRenderType.LONGSWORD)) {
            TwoHandedItem.TwoHandedRenderType.longswordPosing = true;
            TwoHandedItem.TwoHandedRenderType.angle = twoHandedItem.angle(twoHandedStack);
        }
        original.call(holdingArm, otherArm, head, rightArmed);
        TwoHandedItem.TwoHandedRenderType.longswordPosing = false;
        TwoHandedItem.TwoHandedRenderType.angle = 0f;
    }
}