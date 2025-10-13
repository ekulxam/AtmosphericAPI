package survivalblock.atmosphere.atmospheric_api.mixin.item;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.component.*;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.stat.StatType;
import net.minecraft.util.Hand;
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
public abstract class ItemStackMixin implements ComponentHolder, AtmosphericEnchantmentLevelObtainer, AtmosphericHorseArmorItem, AtmosphericItemStackComponentCreator,  AtmosphericItemStackDamager {

    @Shadow public abstract Item getItem();

    @Shadow public abstract ComponentMap getComponents();

    @Shadow @Nullable public abstract <T> T set(ComponentType<? super T> type, @Nullable T value);

    @Override
    public int atmospheric_api$getAbsoluteLevel(TagKey<Enchantment> tag) {
        ItemEnchantmentsComponent itemEnchantmentsComponent = this.get(DataComponentTypes.ENCHANTMENTS);
        if (itemEnchantmentsComponent == null) {
            return 0;
        }
        int level = 0;
        Set<Object2IntMap.Entry<RegistryEntry<Enchantment>>> enchantments = itemEnchantmentsComponent.getEnchantmentEntries();
        RegistryEntry<Enchantment> registryEntry;
        for (Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : enchantments) {
            registryEntry = entry.getKey();
            if (registryEntry.isIn(tag)) {
                level += Math.abs(EnchantmentHelper.getLevel(registryEntry, (ItemStack) (Object) this));
            }
        }
        return level;
    }

    @Override
    public boolean atmospheric_api$isHorseArmor() {
        //? if =1.21.1 {
        /*
        return this.getItem().atmospheric_api$isHorseArmor();
         *///?} elif =1.21.8 {
        ComponentMap map = this.getComponents();
        if (!map.contains(DataComponentTypes.EQUIPPABLE)) {
            return false;
        }
        EquippableComponent component = map.get(DataComponentTypes.EQUIPPABLE);
        return EquipmentSlot.BODY == component.slot() && component.allows(EntityType.HORSE);
        //?}
    }

    @Override
    public boolean atmospheric_api$applyWhenDoneUsing(PlayerEntity user, Hand hand, int durabilityDamage, int cooldownTicks, Optional<StatType<Item>> stat) {
        return this.getItem().atmospheric_api$applyWhenDoneUsing(user, hand, (ItemStack) (Object) this, durabilityDamage, cooldownTicks, stat);
    }

    @Override
    public <T> T atmospheric_api$getOrCreate(ComponentType<T> type, T fallback) {
        T component = this.get(type);
        if (component != null) {
            return component;
        }
        this.set(type, fallback);
        return this.get(type);
    }
}
