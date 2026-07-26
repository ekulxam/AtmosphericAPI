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
package survivalblock.atmosphere.atmospheric_api.not_mixin.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

@SuppressWarnings("unused")
public interface ScrollingItem {

	/**
	 * Allows item to perform custom logic when the user scrolls in the hotbar.
	 * In vanilla, this should always be called on the client.
	 * @param world the world
	 * @param player the player using the item
	 * @param stack the itemstack instance
	 * @param scrollAmount the amount scrolled
	 * @return true to allow scrolling (vanilla), false to cancel
	 */
	boolean onScroll(Level world, Player player, ItemStack stack, double scrollAmount);
}
