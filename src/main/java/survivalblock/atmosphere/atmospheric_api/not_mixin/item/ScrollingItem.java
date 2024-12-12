package survivalblock.atmosphere.atmospheric_api.not_mixin.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * WIP<p>
 * Todo: actually make this work
 */
@SuppressWarnings("unused")
public interface ScrollingItem {

	/**
	 * Allows item to perform custom logic when the user scrolls in the hotbar.
	 * In vanilla, this should always be called on the client.
	 * @param world The world
	 * @param player The player using the item
	 * @param stack The itemstack
	 * @param scrollAmount The amount scrolled
	 * @return if the default logic should occur (scroll in hotbar)
	 */
	boolean onScroll(World world, PlayerEntity player, ItemStack stack, double scrollAmount);
}
