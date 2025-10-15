package survivalblock.atmosphere.atmospheric_api.mixin.item.two_handed.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.TwoHandedItem;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.client.AtmosphericSpecialItemRenderHandlerImpl;

@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {

    @ModifyExpressionValue(method = "getUsingItemHandRenderType", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 1))
    private static boolean usingLongswordRenderType(boolean original, @Local ItemStack stack){
        return original || (stack.getItem() instanceof TwoHandedItem twoHandedItem && AtmosphericSpecialItemRenderHandlerImpl.getTwoHandedHandler().get(twoHandedItem).apply(stack));
    }

    @ModifyExpressionValue(method = "getHandRenderType", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 3))
    private static boolean longswordIsACrossbowToo(boolean original, @Local(ordinal = 0) ItemStack itemStack, @Local(ordinal = 1) ItemStack itemStack2){
        if (original) {
            return true;
        }
        if (itemStack.getItem() instanceof TwoHandedItem twoHandedItem && AtmosphericSpecialItemRenderHandlerImpl.getTwoHandedHandler().get(twoHandedItem).apply(itemStack)) {
            return true;
        }
        return itemStack2.getItem() instanceof TwoHandedItem twoHandedItem && AtmosphericSpecialItemRenderHandlerImpl.getTwoHandedHandler().get(twoHandedItem).apply(itemStack2);
    }

    @ModifyExpressionValue(method = "getHandRenderType", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/HeldItemRenderer;isChargedCrossbow(Lnet/minecraft/item/ItemStack;)Z"))
    private static boolean mainHandLongsword(boolean original, @Local(ordinal = 0) ItemStack itemStack){
        return original || (itemStack.getItem() instanceof TwoHandedItem twoHandedItem && AtmosphericSpecialItemRenderHandlerImpl.getTwoHandedHandler().get(twoHandedItem).apply(itemStack));
    }

    @ModifyExpressionValue(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = /*? =1.21.1 {*/  1 /*?} else {*/ /*0 *//*?}*/))
    private boolean renderFirstPersonLongsword(boolean original, @Local(argsOnly = true) ItemStack stack){
        return original || (stack.getItem() instanceof TwoHandedItem twoHandedItem && AtmosphericSpecialItemRenderHandlerImpl.getTwoHandedHandler().get(twoHandedItem).apply(stack));
    }

    @ModifyExpressionValue(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;isUsingItem()Z", ordinal = 0))
    private boolean noLongswordDrawbackAnimation(boolean original, @Local(argsOnly = true) ItemStack stack){
        return !(stack.getItem() instanceof TwoHandedItem twoHandedItem && AtmosphericSpecialItemRenderHandlerImpl.getTwoHandedHandler().get(twoHandedItem).apply(stack)) && original;
    }
}
