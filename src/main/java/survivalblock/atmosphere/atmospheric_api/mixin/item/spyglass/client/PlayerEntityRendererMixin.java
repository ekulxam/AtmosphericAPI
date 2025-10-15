//? if 1.21.8 {
/*package survivalblock.atmosphere.atmospheric_api.mixin.item.spyglass.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.IAmASpyglassItem;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {

    @ModifyExpressionValue(method = "updateRenderState(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/client/render/entity/state/PlayerEntityRenderState;F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean isOfKaleidoscope(boolean original, @Local ItemStack stack) {
        return original || stack.getItem() instanceof IAmASpyglassItem;
    }
}
*///?}