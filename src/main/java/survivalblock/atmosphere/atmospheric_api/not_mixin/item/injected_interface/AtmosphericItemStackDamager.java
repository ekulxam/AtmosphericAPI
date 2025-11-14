package survivalblock.atmosphere.atmospheric_api.not_mixin.item.injected_interface;

import java.util.Optional;
import net.minecraft.stats.StatType;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

@SuppressWarnings({"unused", "UnusedReturnValue", "OptionalUsedAsFieldOrParameterType"})
public interface AtmosphericItemStackDamager {

    default boolean atmospheric_api$applyWhenDoneUsing(Player user, InteractionHand hand, int damage, int cooldownTicks, Optional<StatType<Item>> stat) {
        return false;
    }
}
