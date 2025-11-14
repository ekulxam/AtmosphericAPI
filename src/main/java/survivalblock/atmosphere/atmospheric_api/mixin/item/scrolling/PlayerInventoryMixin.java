//? if 1.21.1 {
/*package survivalblock.atmosphere.atmospheric_api.mixin.item.scrolling;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.ScrollingItem;

@Mixin(Inventory.class)
public abstract class PlayerInventoryMixin {

    @Shadow public int selected;

    @Shadow public abstract ItemStack getItem(int slot);

    @Shadow @Final public Player player;

    @Inject(method = "swapPaint", at = @At("HEAD"), cancellable = true)
    private void stopScroll(double scrollAmount, CallbackInfo ci) {
        ItemStack stack = this.getItem(this.selected);
        if (!(stack.getItem() instanceof ScrollingItem scrollingItem)) {
            return;
        }
        boolean shouldScroll = scrollingItem.onScroll(this.player.level(), this.player, stack, scrollAmount);
        if (!shouldScroll) {
            ci.cancel();
        }
    }
}
*///?}