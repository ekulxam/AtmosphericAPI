package survivalblock.atmosphere.atmospheric_api.mixin.item.spyglass.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.IAmASpyglassItem;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.client.AtmosphericSpecialItemRenderHandlerImpl;

@Mixin(AbstractClientPlayer.class)
public abstract class AbstractClientPlayerEntityMixin extends Player {

    //? if =1.21.1 {
    public AbstractClientPlayerEntityMixin(Level world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }
     //?} elif =1.21.8 {
    /*public AbstractClientPlayerEntityMixin(World world, GameProfile profile) {
        super(world, profile);
    }
    *///?}

    @ModifyExpressionValue(method = "getFieldOfViewModifier", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/AbstractClientPlayer;isScoping()Z"))
    private boolean turnOffZoom(boolean original) {
        ItemStack activeStack = this.getUseItem();
        if (!(activeStack.getItem() instanceof IAmASpyglassItem spyglass)) {
            return original;
        }
        if (!AtmosphericSpecialItemRenderHandlerImpl.getZoomHandler().get(spyglass).apply(activeStack)) {
            return false;
        }
        return original;
    }
}
