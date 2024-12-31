package survivalblock.atmosphere.atmospheric_api.not_mixin.datagen;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.item.Item;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

@SuppressWarnings("unused")
public record EnchantmentRegistryEntryLookupContainer(RegistryEntryLookup<DamageType> damageTypeRegistryEntryLookup,
                                                      RegistryEntryLookup<Enchantment> enchantmentRegistryEntryLookup,
                                                      RegistryEntryLookup<Item> itemRegistryEntryLookup,
                                                      RegistryEntryLookup<Block> blockRegistryEntryLookup) {

    public EnchantmentRegistryEntryLookupContainer(Registerable<Enchantment> registerable) {
        this(registerable.getRegistryLookup(RegistryKeys.DAMAGE_TYPE), registerable.getRegistryLookup(RegistryKeys.ENCHANTMENT), registerable.getRegistryLookup(RegistryKeys.ITEM), registerable.getRegistryLookup(RegistryKeys.BLOCK));
    }

    public EnchantmentRegistryEntryLookupContainer(RegistryWrapper.WrapperLookup wrapperLookup) {
        this(wrapperLookup.getWrapperOrThrow(RegistryKeys.DAMAGE_TYPE), wrapperLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT), wrapperLookup.getWrapperOrThrow(RegistryKeys.ITEM), wrapperLookup.getWrapperOrThrow(RegistryKeys.BLOCK));
    }
}
