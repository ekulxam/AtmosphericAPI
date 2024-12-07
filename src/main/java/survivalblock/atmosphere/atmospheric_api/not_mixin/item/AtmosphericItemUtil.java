package survivalblock.atmosphere.atmospheric_api.not_mixin.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;

@SuppressWarnings("unused")
public final class AtmosphericItemUtil {

    public static void damageAndIncrementStat(PlayerEntity user, Hand hand, Item item, ItemStack stack, int damage, int cooldown) {
        if (!user.isCreative()) {
            stack.damage(damage, user, LivingEntity.getSlotForHand(hand));
            user.getItemCooldownManager().set(item, cooldown);
            user.stopUsingItem();
        }
        user.incrementStat(Stats.USED.getOrCreateStat(item));
    }
}
