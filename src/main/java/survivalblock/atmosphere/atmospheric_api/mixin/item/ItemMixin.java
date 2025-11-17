package survivalblock.atmosphere.atmospheric_api.mixin.item;

import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.stats.StatType;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
//? if <1.21.5
/*import net.minecraft.world.item.AnimalArmorItem;*/
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.injected_interface.AtmosphericHorseArmorItem;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.injected_interface.AtmosphericItemDamager;

import java.util.Optional;

@Mixin(Item.class)
public class ItemMixin implements AtmosphericHorseArmorItem, AtmosphericItemDamager {

    @Override
    public boolean atmospheric_api$isHorseArmor() {
        //? if =1.21.1 {
        
        /*return (Item) (Object) this instanceof AnimalArmorItem animalArmorItem && animalArmorItem.getBodyType().equals(AnimalArmorItem.BodyType.EQUESTRIAN);

         *///?} elif >=1.21.8 {

        return false;

        //?}
    }

    @Override
    public boolean atmospheric_api$applyWhenDoneUsing(Player user, InteractionHand hand, ItemStack stack, int durabilityDamage, int cooldownTicks, Optional<StatType<Item>> stat) {
        if (!user.isCreative()) {
            if (durabilityDamage > 0) stack.hurtAndBreak(durabilityDamage, user, /*? <1.21.9 {*/ LivingEntity.getSlotForHand( /*?}*/hand/*? <1.21.9 {*/ ) /*?}*/);
            if (cooldownTicks > 0) user.getCooldowns().addCooldown(/*? =1.21.1 {*/ /*(Item) (Object) this *//*?} else {*/ stack /*?}*/, cooldownTicks);
        }
        stat.ifPresent(statType -> statType.get((Item) (Object) this));
        return true;
    }
}
