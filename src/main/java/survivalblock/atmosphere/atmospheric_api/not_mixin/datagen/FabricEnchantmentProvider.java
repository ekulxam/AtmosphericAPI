package survivalblock.atmosphere.atmospheric_api.not_mixin.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.*;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("unused")
public abstract class FabricEnchantmentProvider extends AtmosphericDynamicRegistryProvider<Enchantment> {

    public FabricEnchantmentProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ENCHANTMENT, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup, Entries entries) {
        this.addEnchantmentsToEntries(wrapperLookup, new EnchantmentRegistryEntryLookupContainer(wrapperLookup), entries);
    }

    public abstract void addEnchantmentsToEntries(RegistryWrapper.WrapperLookup wrapperLookup,
                                                  EnchantmentRegistryEntryLookupContainer enchantmentRegistryEntryLookupContainer,
                                                  Entries entries);
}
