package survivalblock.atmosphere.atmospheric_api.not_mixin.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public abstract class FabricEnchantmentProvider extends FabricDynamicRegistryProvider {

    public FabricEnchantmentProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup, Entries entries) {
        RegistryEntryLookup<Item> itemRegistryEntryLookup = wrapperLookup.getWrapperOrThrow(RegistryKeys.ITEM);
        this.addEnchantmentsToEntries(wrapperLookup, itemRegistryEntryLookup, entries);
    }

    public abstract void addEnchantmentsToEntries(RegistryWrapper.WrapperLookup wrapperLookup,
                                                  RegistryEntryLookup<Item> itemRegistryEntryLookup,
                                                  Entries entries);

    @Override
    public String getName() {
        return "Enchantments";
    }
}
