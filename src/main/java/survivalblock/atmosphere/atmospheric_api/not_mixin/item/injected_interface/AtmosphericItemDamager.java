package survivalblock.atmosphere.atmospheric_api.not_mixin.item.injected_interface;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.StatType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.Optional;

@SuppressWarnings({"unused", "UnusedReturnValue", "OptionalUsedAsFieldOrParameterType"})
public interface AtmosphericItemDamager {

    /**
     * @see Item#finishUsing(ItemStack, World, LivingEntity)
     */
    boolean atmospheric_api$applyWhenDoneUsing(PlayerEntity user, Hand hand, ItemStack stack, int durabilityDamage, int cooldownTicks, Optional<StatType<Item>> stat);
}
