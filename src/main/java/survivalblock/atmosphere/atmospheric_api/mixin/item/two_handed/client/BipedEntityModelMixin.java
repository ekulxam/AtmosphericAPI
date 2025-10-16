package survivalblock.atmosphere.atmospheric_api.mixin.item.two_handed.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
/*? =1.21.1 {*/  /*?} else {*/ /*/* import net.minecraft.client.render.entity.state.BipedEntityRenderState; /*?}*/
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.atmospheric_api.access.WaitingOnFabricRenderState;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.TwoHandedItem;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.client.AtmosphericSpecialItemRenderHandlerImpl;

import java.util.Objects;

@Mixin(BipedEntityModel.class)
public class BipedEntityModelMixin {

    @WrapOperation(method = {"positionLeftArm", "positionRightArm"}, at = @At(value = "INVOKE", target =
            /*? =1.21.1 {*/ /*"Lnet/minecraft/client/render/entity/model/CrossbowPosing;hold(Lnet/minecraft/client/model/ModelPart;Lnet/minecraft/client/model/ModelPart;Lnet/minecraft/client/model/ModelPart;Z)V" *//*?} else {*/ "Lnet/minecraft/client/render/entity/model/ArmPosing;hold(Lnet/minecraft/client/model/ModelPart;Lnet/minecraft/client/model/ModelPart;Lnet/minecraft/client/model/ModelPart;Z)V" /*?}*/
    ))
    private void doSomeCursedLongswordPosing(
            ModelPart holdingArm, ModelPart otherArm, ModelPart head, boolean rightArmed, Operation<Void> original, /*? =1.21.1 {*/  /*LivingEntity living *//*?} else {*/ BipedEntityRenderState state /*?}*/
    ) {
        ItemStack twoHandedStack = null;
        TwoHandedItem twoHandedItem = null;
        ItemStack mainHandStack = /*? =1.21.1 {*/  /*living.getMainHandStack() *//*?} else {*/ ((WaitingOnFabricRenderState) state).atmospheric_api$getMainHandStack() /*?}*/;
        if (!mainHandStack.isEmpty() && mainHandStack.getItem() instanceof TwoHandedItem twoHandedItem1) {
            twoHandedItem = twoHandedItem1;
            twoHandedStack = mainHandStack;
        } else {
            ItemStack offHandStack = /*? =1.21.1 {*/  /*living.getOffHandStack() *//*?} else {*/ ((WaitingOnFabricRenderState) state).atmospheric_api$getOffHandStack() /*?}*/;
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