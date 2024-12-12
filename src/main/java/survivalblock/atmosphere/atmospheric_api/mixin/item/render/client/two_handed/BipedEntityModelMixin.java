package survivalblock.atmosphere.atmospheric_api.mixin.item.render.client.two_handed;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.TwoHandedItem;

import java.util.Objects;

@Mixin(BipedEntityModel.class)
public class BipedEntityModelMixin {

    @WrapOperation(method = {"positionLeftArm", "positionRightArm"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/model/CrossbowPosing;hold(Lnet/minecraft/client/model/ModelPart;Lnet/minecraft/client/model/ModelPart;Lnet/minecraft/client/model/ModelPart;Z)V"))
    private void doSomeCursedLongswordPosing(ModelPart holdingArm, ModelPart otherArm, ModelPart head, boolean rightArmed, Operation<Void> original, LivingEntity living) {
        ItemStack twoHandedStack = null;
        TwoHandedItem twoHandedItem = null;
        ItemStack mainHandStack = living.getMainHandStack();
        if (!mainHandStack.isEmpty() && mainHandStack.getItem() instanceof TwoHandedItem twoHandedItem1) {
            twoHandedItem = twoHandedItem1;
            twoHandedStack = mainHandStack;
        } else {
            ItemStack offHandStack = living.getOffHandStack();
            if (!offHandStack.isEmpty() && offHandStack.getItem() instanceof TwoHandedItem twoHandedItem1) {
                twoHandedItem = twoHandedItem1;
                twoHandedStack = offHandStack;
            }
        }
        if (twoHandedItem != null && twoHandedItem.shouldRenderTwoHanded(twoHandedStack) && Objects.equals(twoHandedItem.renderAtAnAngle(twoHandedStack), TwoHandedItem.TwoHandedRenderType.LONGSWORD)) {
            TwoHandedItem.TwoHandedRenderType.longswordPosing = true;
            TwoHandedItem.TwoHandedRenderType.angle = twoHandedItem.angle(twoHandedStack);
        }
        original.call(holdingArm, otherArm, head, rightArmed);
        TwoHandedItem.TwoHandedRenderType.longswordPosing = false;
        TwoHandedItem.TwoHandedRenderType.angle = 0f;
    }
}
