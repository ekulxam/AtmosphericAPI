package survivalblock.atmosphere.atmospheric_api.mixin.item.two_handed.client;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.TwoHandedItem;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.client.AtmosphericSpecialItemRenderHandlerImpl;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {

    @SuppressWarnings("DiscouragedShift")
    @Inject(method = /*? =1.21.1 {*/ /*"getArmPose" *//*?} else {*/ "getArmPose(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/util/Hand;)Lnet/minecraft/client/render/entity/model/BipedEntityModel$ArmPose;" /*?}*/, at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isEmpty()Z", shift = At.Shift.BEFORE), cancellable = true)
    private static void crossbowLongsword(/*? =1.21.1 {*/  /*AbstractClientPlayerEntity *//*?} else {*/ PlayerEntity /*?}*/ player, /*? =1.21.1 {*/   /*?} else {*/ ItemStack stack, /*?}*/ Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> cir/*? =1.21.1 {*/  /*, @Local ItemStack stack *//*?} else {*/  /*?}*/){
        if (stack.getItem() instanceof TwoHandedItem twoHandedItem && AtmosphericSpecialItemRenderHandlerImpl.getTwoHandedHandler().get(twoHandedItem).apply(stack)) {
            cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_HOLD);
        }
    }
}
