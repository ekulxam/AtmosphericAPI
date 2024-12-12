package survivalblock.atmosphere.atmospheric_api.not_mixin.item.injected_interface;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.stat.StatType;
import net.minecraft.util.Hand;

import java.util.Optional;

@SuppressWarnings({"unused", "UnusedReturnValue", "OptionalUsedAsFieldOrParameterType"})
public interface AtmosphericItemStackDamager {

    boolean atmospheric_api$applyWhenDoneUsing(PlayerEntity user, Hand hand, int damage, int cooldownTicks, Optional<StatType<Item>> stat);
}
