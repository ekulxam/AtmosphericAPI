//? if >=1.21.8 {
package survivalblock.atmosphere.atmospheric_api.mixin.item.spyglass.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.IAmASpyglassItem;

@SuppressWarnings("UnusedMixin")
@Mixin(PlayerRenderer.class)
public class PlayerEntityRendererMixin {

    @ModifyExpressionValue(method =/*? >1.21.8 {*/ /*"extractRenderState(Lnet/minecraft/world/entity/Avatar;Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;F)V" *//*?} else {*/ "extractRenderState(Lnet/minecraft/client/player/AbstractClientPlayer;Lnet/minecraft/client/renderer/entity/state/PlayerRenderState;F)V" /*?}*/,
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
    private boolean isOfKaleidoscope(boolean original, @Local ItemStack stack) {
        return original || stack.getItem() instanceof IAmASpyglassItem;
    }
}
//?}