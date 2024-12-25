package survivalblock.atmosphere.atmospheric_api.not_mixin.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.registry.*;

import java.util.concurrent.CompletableFuture;

public abstract class FabricEnchantmentProvider extends AtmosphericDynamicRegistryProvider<Enchantment> {

    public FabricEnchantmentProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ENCHANTMENT, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup, Entries entries) {
        RegistryEntryLookup<Item> itemRegistryEntryLookup = wrapperLookup.getWrapperOrThrow(RegistryKeys.ITEM);
        this.addEnchantmentsToEntries(wrapperLookup, itemRegistryEntryLookup, entries);
    }

    public abstract void addEnchantmentsToEntries(RegistryWrapper.WrapperLookup wrapperLookup,
                                                  RegistryEntryLookup<Item> itemRegistryEntryLookup,
                                                  Entries entries);
}
