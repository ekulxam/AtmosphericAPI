package survivalblock.atmosphere.atmospheric_api.not_mixin.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.item.Item;
import net.minecraft.registry.*;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("unused")
public abstract class FabricEnchantmentProvider extends AtmosphericDynamicRegistryProvider<Enchantment> {

    public FabricEnchantmentProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ENCHANTMENT, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup, Entries entries) {
        RegistryEntryLookup<DamageType> registryEntryLookup = wrapperLookup.getWrapperOrThrow(RegistryKeys.DAMAGE_TYPE);
        RegistryEntryLookup<Enchantment> registryEntryLookup2 = wrapperLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        RegistryEntryLookup<Item> registryEntryLookup3 = wrapperLookup.getWrapperOrThrow(RegistryKeys.ITEM);
        RegistryEntryLookup<Block> registryEntryLookup4 = wrapperLookup.getWrapperOrThrow(RegistryKeys.BLOCK);
        this.addEnchantmentsToEntries(wrapperLookup, new EnchantmentRegistryEntryLookupContainer(registryEntryLookup, registryEntryLookup2, registryEntryLookup3, registryEntryLookup4), entries);
    }

    public abstract void addEnchantmentsToEntries(RegistryWrapper.WrapperLookup wrapperLookup,
                                                  EnchantmentRegistryEntryLookupContainer enchantmentRegistryEntryLookupContainer,
                                                  Entries entries);

    public record EnchantmentRegistryEntryLookupContainer(RegistryEntryLookup<DamageType> damageTypeRegistryEntryLookup,
                                                                  RegistryEntryLookup<Enchantment> enchantmentRegistryEntryLookup,
                                                                  RegistryEntryLookup<Item> itemRegistryEntryLookup,
                                                                  RegistryEntryLookup<Block> blockRegistryEntryLookup) {
    }
}
