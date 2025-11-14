package survivalblock.atmosphere.atmospheric_api.not_mixin.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

@SuppressWarnings("unused")
public interface ScrollingItem {

	/**
	 * Allows item to perform custom logic when the user scrolls in the hotbar.
	 * In vanilla, this should always be called on the client.
	 * @param world The world
	 * @param player The player using the item
	 * @param stack The itemstack
	 * @param scrollAmount The amount scrolled
	 * @return false to cancel default logic, true to allow
	 */
	boolean onScroll(Level world, Player player, ItemStack stack, double scrollAmount);
}
