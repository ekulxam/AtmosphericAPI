package survivalblock.atmosphere.atmospheric_api.mixin.item.spyglass.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.IAmASpyglassItem;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.client.AtmosphericSpecialItemRenderHandlerImpl;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin extends PlayerEntity {

    public AbstractClientPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @ModifyExpressionValue(method = "getFovMultiplier", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;isUsingSpyglass()Z"))
    private boolean turnOffZoom(boolean original) {
        ItemStack activeStack = this.getActiveItem();
        if (!(activeStack.getItem() instanceof IAmASpyglassItem spyglass)) {
            return original;
        }
        if (!AtmosphericSpecialItemRenderHandlerImpl.getZoomHandler().get(spyglass).apply(activeStack)) {
            return false;
        }
        return original;
    }
}
