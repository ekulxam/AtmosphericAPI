/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
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
