//? if >=1.21.8 {
package survivalblock.atmosphere.atmospheric_api.mixin.item.scrolling.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.ScrollingItem;

@SuppressWarnings("UnusedMixin")
@Mixin(MouseHandler.class)
public abstract class MouseMixin {

    @Shadow @Final private Minecraft minecraft;

    @WrapOperation(method = "onScroll", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/ScrollWheelHandler;getNextScrollWheelSelection(DII)I"))
    private int stopScroll(double scrollAmount, int selectedIndex, int total, Operation<Integer> original, @Local Inventory playerInventory) {
        Player player = this.minecraft.player;
        ItemStack stack = playerInventory.getItem(selectedIndex);
        if (!(stack.getItem() instanceof ScrollingItem scrollingItem)) {
            return original.call(scrollAmount, selectedIndex, total);
        }
        boolean shouldScroll = scrollingItem.onScroll(player.level(), player, stack, scrollAmount);
        if (!shouldScroll) {
            return selectedIndex;
        }
        return original.call(scrollAmount, selectedIndex, total);
    }
}
//?}