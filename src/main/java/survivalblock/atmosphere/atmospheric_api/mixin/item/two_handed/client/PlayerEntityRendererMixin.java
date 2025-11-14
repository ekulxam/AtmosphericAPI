package survivalblock.atmosphere.atmospheric_api.mixin.item.two_handed.client;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.TwoHandedItem;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.client.AtmosphericSpecialItemRenderHandlerImpl;

@Mixin(PlayerRenderer.class)
public class PlayerEntityRendererMixin {

    @SuppressWarnings("DiscouragedShift")
    @Inject(method = /*? =1.21.1 {*/ /*"getArmPose"  *//*?} else {*/ "getArmPose(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/client/model/HumanoidModel$ArmPose;" /*?}*/, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z", shift = At.Shift.BEFORE), cancellable = true)
    private static void crossbowLongsword(/*? =1.21.1 {*/ /*AbstractClientPlayer *//*?} else {*/ Player /*?}*/ player, /*? =1.21.1 {*/   /*?} else {*/ ItemStack stack, /*?}*/ InteractionHand hand, CallbackInfoReturnable<HumanoidModel.ArmPose> cir/*? =1.21.1 {*/  /*, @Local ItemStack stack *//*?} else {*/  /*?}*/){
        if (stack.getItem() instanceof TwoHandedItem twoHandedItem && AtmosphericSpecialItemRenderHandlerImpl.getTwoHandedHandler().get(twoHandedItem).apply(stack)) {
            cir.setReturnValue(HumanoidModel.ArmPose.CROSSBOW_HOLD);
        }
    }
}
