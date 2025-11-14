package survivalblock.atmosphere.atmospheric_api.not_mixin.item.injected_interface;

import net.minecraft.stats.StatType;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Optional;

@SuppressWarnings({"unused", "UnusedReturnValue", "OptionalUsedAsFieldOrParameterType"})
public interface AtmosphericItemDamager {

    /**
     * @see Item#finishUsingItem(ItemStack, Level, LivingEntity)
     */
    default boolean atmospheric_api$applyWhenDoneUsing(Player user, InteractionHand hand, ItemStack stack, int durabilityDamage, int cooldownTicks, Optional<StatType<Item>> stat) {
        return false;
    }
}
