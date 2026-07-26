/*
 * Copyright (c) 21:56 UTC 26 July 2026 - present ekulxam
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

@SuppressWarnings("UnusedMixin")
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