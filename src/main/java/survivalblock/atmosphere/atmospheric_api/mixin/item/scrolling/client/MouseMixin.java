//? if 1.21.8 {
package survivalblock.atmosphere.atmospheric_api.mixin.item.scrolling.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.ScrollingItem;

@Mixin(Mouse.class)
public abstract class MouseMixin {

    @Shadow @Final private MinecraftClient client;

    @WrapOperation(method = "onMouseScroll", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/input/Scroller;scrollCycling(DII)I"))
    private int stopScroll(double scrollAmount, int selectedIndex, int total, Operation<Integer> original, @Local PlayerInventory playerInventory) {
        PlayerEntity player = this.client.player;
        ItemStack stack = playerInventory.getStack(selectedIndex);
        if (!(stack.getItem() instanceof ScrollingItem scrollingItem)) {
            return original.call(scrollAmount, selectedIndex, total);
        }
        boolean shouldScroll = scrollingItem.onScroll(player.getWorld(), player, stack, scrollAmount);
        if (!shouldScroll) {
            return selectedIndex;
        }
        return original.call(scrollAmount, selectedIndex, total);
    }
}
//?}