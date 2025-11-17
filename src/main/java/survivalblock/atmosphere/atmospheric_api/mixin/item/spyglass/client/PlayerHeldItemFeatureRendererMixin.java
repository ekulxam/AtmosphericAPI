//? if 1.21.1 {
/*package survivalblock.atmosphere.atmospheric_api.mixin.item.spyglass.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.renderer.entity.layers.PlayerItemInHandLayer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.IAmASpyglassItem;

@SuppressWarnings("UnusedMixin")
@Mixin(PlayerItemInHandLayer.class)
public class PlayerHeldItemFeatureRendererMixin {

    @ModifyExpressionValue(method = "renderArmWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
    private boolean isOfKaleidoscope(boolean original, @Local(argsOnly = true) ItemStack stack) {
        return original || stack.getItem() instanceof IAmASpyglassItem;
    }
}
*///?}