package survivalblock.atmosphere.atmospheric_api.mixin.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AnimalArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.StatType;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.injected_interface.AtmosphericHorseArmorItem;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.injected_interface.AtmosphericItemDamager;

import java.util.Optional;

@Mixin(Item.class)
public class ItemMixin implements AtmosphericHorseArmorItem, AtmosphericItemDamager {

    @Override
    public boolean atmospheric_api$isHorseArmor() {
        return (Item) (Object) this instanceof AnimalArmorItem animalArmorItem && animalArmorItem.getType().equals(AnimalArmorItem.Type.EQUESTRIAN);
    }

    @Override
    public boolean atmospheric_api$applyWhenDoneUsing(PlayerEntity user, Hand hand, ItemStack stack, int durabilityDamage, int cooldownTicks, Optional<StatType<Item>> stat) {
        if (!user.isCreative()) {
            if (durabilityDamage > 0) stack.damage(durabilityDamage, user, LivingEntity.getSlotForHand(hand));
            if (cooldownTicks > 0) user.getItemCooldownManager().set((Item) (Object) this, cooldownTicks);
        }
        stat.ifPresent(statType -> statType.getOrCreateStat((Item) (Object) this));
        return true;
    }
}
