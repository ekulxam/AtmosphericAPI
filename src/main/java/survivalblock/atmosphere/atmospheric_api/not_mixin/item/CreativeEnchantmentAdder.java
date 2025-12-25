package survivalblock.atmosphere.atmospheric_api.not_mixin.item;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jetbrains.annotations.Nullable;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public class CreativeEnchantmentAdder {

    private static void addEnchantedStack(Item item, CreativeModeTab.ItemDisplayParameters displayContext, String enchantmentId, CreativeModeTab.Output entries) {
        addEnchantedStack(item, displayContext, enchantmentId, entries, null);
    }

    private static void addEnchantedStack(Item item, CreativeModeTab.ItemDisplayParameters displayContext, String enchantmentId, CreativeModeTab.Output entries, @Nullable Consumer<ItemStack> stackModifier) {
        try {
            ItemStack stack = new ItemStack(item);
            AtomicReference<Holder<Enchantment>> reference = new AtomicReference<>(null);
            ResourceLocation id = ResourceLocation.parse(enchantmentId);
            displayContext.holders()
                    .lookupOrThrow(Registries.ENCHANTMENT)
                    .listElements()
                    .filter(enchantmentRef -> enchantmentRef.is(id))
                    .findFirst()
                    .ifPresent(reference::set);
            Holder<Enchantment> enchantmentEntry = Objects.requireNonNull(reference.get());
            if (enchantmentEntry.value().canEnchant(stack)) {
                stack.enchant(enchantmentEntry, enchantmentEntry.value().getMaxLevel());

                if (stackModifier != null) {
                    stackModifier.accept(stack);
                }

                entries.accept(stack);
            } else {
                AtmosphericAPI.LOGGER.error("Avoided adding an ItemStack of {} because enchantment {} does not support that item", BuiltInRegistries.ITEM.getId(item), id);
            }
        } catch (Throwable throwable) {
            try {
                AtmosphericAPI.LOGGER.error("Unable to add an ItemStack of {} because of an error when getting enchantment {}", BuiltInRegistries.ITEM.getId(item), enchantmentId, throwable);
            } catch (Throwable throwable1) {
                AtmosphericAPI.LOGGER.error("There was an error while getting enchantment {}", enchantmentId, throwable);
                AtmosphericAPI.LOGGER.error("There was an error while trying to get an item's id from the registry!", throwable1);
            }
        }
    }
}
