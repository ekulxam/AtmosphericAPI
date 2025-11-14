package survivalblock.atmosphere.atmospheric_api.mixin.item;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentHolder;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.stats.StatType;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
//? if >=1.21.2
import net.minecraft.world.item.equipment.Equippable;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.injected_interface.AtmosphericEnchantmentLevelObtainer;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.injected_interface.AtmosphericHorseArmorItem;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.injected_interface.AtmosphericItemStackComponentCreator;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.injected_interface.AtmosphericItemStackDamager;

import java.util.Optional;
import java.util.Set;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements DataComponentHolder, AtmosphericEnchantmentLevelObtainer, AtmosphericHorseArmorItem, AtmosphericItemStackComponentCreator,  AtmosphericItemStackDamager {

    @Shadow public abstract Item getItem();

    @Shadow public abstract DataComponentMap getComponents();

    @Shadow @Nullable public abstract <T> T set(DataComponentType<? super T> type, @Nullable T value);

    @Override
    public int atmospheric_api$getAbsoluteLevel(TagKey<Enchantment> tag) {
        ItemEnchantments itemEnchantmentsComponent = this.get(DataComponents.ENCHANTMENTS);
        if (itemEnchantmentsComponent == null) {
            return 0;
        }
        int level = 0;
        Set<Object2IntMap.Entry<Holder<Enchantment>>> enchantments = itemEnchantmentsComponent.entrySet();
        Holder<Enchantment> registryEntry;
        for (Object2IntMap.Entry<Holder<Enchantment>> entry : enchantments) {
            registryEntry = entry.getKey();
            if (registryEntry.is(tag)) {
                level += Math.abs(EnchantmentHelper.getItemEnchantmentLevel(registryEntry, (ItemStack) (Object) this));
            }
        }
        return level;
    }

    @Override
    public boolean atmospheric_api$isHorseArmor() {
        //? if =1.21.1 {
        
        /*return this.getItem().atmospheric_api$isHorseArmor();

         *///?} elif =1.21.8 {
        
        DataComponentMap map = this.getComponents();
        if (!map.has(DataComponents.EQUIPPABLE)) {
            return false;
        }
        Equippable component = map.get(DataComponents.EQUIPPABLE);
        // this seems to be the best I can do, given the component changes
        return EquipmentSlot.BODY == component.slot() && component.canBeEquippedBy(EntityType.HORSE);
        //?}
    }

    @Override
    public boolean atmospheric_api$applyWhenDoneUsing(Player user, InteractionHand hand, int durabilityDamage, int cooldownTicks, Optional<StatType<Item>> stat) {
        return this.getItem().atmospheric_api$applyWhenDoneUsing(user, hand, (ItemStack) (Object) this, durabilityDamage, cooldownTicks, stat);
    }

    @Override
    public <T> T atmospheric_api$getOrCreate(DataComponentType<T> type, T fallback) {
        T component = this.get(type);
        if (component != null) {
            return component;
        }
        this.set(type, fallback);
        return this.get(type);
    }
}