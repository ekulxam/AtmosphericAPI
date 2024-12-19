package survivalblock.atmosphere.atmospheric_api.mixin.item.scrolling;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.ScrollingItem;

@Mixin(PlayerInventory.class)
public abstract class PlayerInventoryMixin {

    @Shadow public int selectedSlot;

    @Shadow public abstract ItemStack getStack(int slot);

    @Shadow @Final public PlayerEntity player;

    @Inject(method = "scrollInHotbar", at = @At("HEAD"), cancellable = true)
    private void stopScroll(double scrollAmount, CallbackInfo ci) {
        ItemStack stack = this.getStack(this.selectedSlot);
        if (!(stack.getItem() instanceof ScrollingItem scrollingItem)) {
            return;
        }
        boolean shouldScroll = scrollingItem.onScroll(this.player.getWorld(), this.player, stack, scrollAmount);
        if (!shouldScroll) {
            ci.cancel();
        }
    }
}
