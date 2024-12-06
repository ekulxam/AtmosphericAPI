package survivalblock.atmosphere.atmospheric_api.mixin.item.client.spyglass;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.IAmASpyglassItem;

@Mixin(Mouse.class)
public class MouseMixin {

    @Shadow @Final private MinecraftClient client;

    @ModifyExpressionValue(method = "updateMouse", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingSpyglass()Z"))
    private boolean changeMouseSmoothnessInZoom(boolean original) {
        ItemStack activeStack = this.client.player.getActiveItem();
        if (!(activeStack.getItem() instanceof IAmASpyglassItem spyglass)) {
            return original;
        }
        if (!spyglass.shouldZoomIn(activeStack)) {
            return false;
        }
        return original;
    }
}
