/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.item;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jetbrains.annotations.Nullable;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public final class CreativeTabEnchantmentAdder {
    private CreativeTabEnchantmentAdder() {
    }

    public static void addEnchantedStack(Item item, CreativeModeTab.ItemDisplayParameters displayContext, String enchantment, CreativeModeTab.Output entries) {
        addEnchantedStack(item, displayContext, enchantment, entries, null);
    }

    public static void addEnchantedStack(Item item, CreativeModeTab.ItemDisplayParameters displayContext, String enchantment, CreativeModeTab.Output entries, @Nullable Consumer<ItemStack> stackModifier) {
        ResourceLocation enchantmentId = ResourceLocation.tryParse(enchantment);
        addEnchantedStack(item, displayContext, reference -> reference.is(enchantmentId), () -> enchantment, entries, stackModifier);
    }

    public static void addEnchantedStack(Item item, CreativeModeTab.ItemDisplayParameters displayContext, ResourceKey<Enchantment> enchantment, CreativeModeTab.Output entries) {
        addEnchantedStack(item, displayContext, enchantment, entries, null);
    }

    public static void addEnchantedStack(Item item, CreativeModeTab.ItemDisplayParameters displayContext, ResourceKey<Enchantment> enchantment, CreativeModeTab.Output entries, @Nullable Consumer<ItemStack> stackModifier) {
        addEnchantedStack(item, displayContext, reference -> reference.is(enchantment), () -> enchantment.location().toString(), entries, stackModifier);
    }

    public static void addEnchantedStack(Item item, CreativeModeTab.ItemDisplayParameters displayContext, Predicate<Holder.Reference<Enchantment>> enchantmentChecker, Supplier<String> idSupplierOnError, CreativeModeTab.Output entries, @Nullable Consumer<ItemStack> stackModifier) {
        try {
            ItemStack stack = new ItemStack(item);

            Optional<Holder.Reference<Enchantment>> optional = displayContext.holders()
                    .lookupOrThrow(Registries.ENCHANTMENT)
                    .listElements()
                    .filter(enchantmentChecker)
                    .findFirst();
            if (optional.isEmpty()) {
                throw new NullPointerException("Enchantment " + idSupplierOnError.get() + " was not found");
            }

            Holder<Enchantment> enchantmentEntry = optional.get();
            if (enchantmentEntry.value().canEnchant(stack)) {
                stack.enchant(enchantmentEntry, enchantmentEntry.value().getMaxLevel());
                if (stackModifier != null) {
                    stackModifier.accept(stack);
                }
                entries.accept(stack);
            } else {
                AtmosphericAPI.LOGGER.error("Avoided adding an ItemStack of {} because enchantment {} does not support that item", BuiltInRegistries.ITEM.getId(item), idSupplierOnError.get());
            }

        } catch (Throwable throwable) {
            try {
                AtmosphericAPI.LOGGER.error("Unable to add an ItemStack of {} because of an error when getting enchantment {}", BuiltInRegistries.ITEM.getId(item), idSupplierOnError.get(), throwable);
            } catch (Throwable throwable1) {
                AtmosphericAPI.LOGGER.error("There was an error while getting enchantment {}", idSupplierOnError.get(), throwable);
                AtmosphericAPI.LOGGER.error("There was an error while trying to get an item's id from the registry!", throwable1);
            }
        }
    }
}
