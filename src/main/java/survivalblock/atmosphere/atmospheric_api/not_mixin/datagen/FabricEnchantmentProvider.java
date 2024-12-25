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
        this.addEnchantmentsToEntries(wrapperLookup, new EnchantmentRegistryEntryLookupContainer(wrapperLookup), entries);
    }

    public abstract void addEnchantmentsToEntries(RegistryWrapper.WrapperLookup wrapperLookup,
                                                  EnchantmentRegistryEntryLookupContainer enchantmentRegistryEntryLookupContainer,
                                                  Entries entries);

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
}
