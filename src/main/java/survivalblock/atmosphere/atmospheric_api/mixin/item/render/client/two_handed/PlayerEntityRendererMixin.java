package survivalblock.atmosphere.atmospheric_api.mixin.item.render.client.two_handed;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.TwoHandedItem;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {

    @SuppressWarnings("DiscouragedShift")
    @Inject(method = "getArmPose", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isEmpty()Z", shift = At.Shift.BEFORE), cancellable = true)
    private static void crossbowLongsword(AbstractClientPlayerEntity player, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> cir, @Local ItemStack stack){
        if (stack.getItem() instanceof TwoHandedItem twoHandedItem && twoHandedItem.shouldRenderTwoHanded(stack)) {
            cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_HOLD);
        }
    }
}
