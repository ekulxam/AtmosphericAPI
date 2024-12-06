package survivalblock.atmosphere.atmospheric_api.not_mixin.util;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ServerAdvancementLoader;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;

import java.util.Objects;
import java.util.Set;

@SuppressWarnings("unused")
public final class AtmosphericUtil {

    public static void grantAdvancement(ServerPlayerEntity serverPlayer, Identifier advancementId) {
        grantAdvancement(serverPlayer, advancementId, false);
    }

    public static void grantAdvancement(ServerPlayerEntity serverPlayer, Identifier advancementId, boolean verboseLogging) {
        MinecraftServer server = serverPlayer.getServer();
        if (server == null) return;
        ServerAdvancementLoader loader = serverPlayer.getServer().getAdvancementLoader();
        AdvancementEntry advancementEntry = loader.get(advancementId);
        try {
            Objects.requireNonNull(advancementEntry, "Advancement entry cannot be null!");
            AdvancementProgress advancementProgress = serverPlayer.getAdvancementTracker().getProgress(advancementEntry);
            if (advancementProgress.isDone()) {
                return;
            }
            for (String string : advancementProgress.getUnobtainedCriteria()) {
                serverPlayer.getAdvancementTracker().grantCriterion(advancementEntry, string);
            }
        } catch (Throwable throwable) {
            if (verboseLogging) {
                AtmosphericAPI.LOGGER.error("An error occured while trying to manually grant an advancement!", throwable);
            }
        }
    }

    public static int getLevel(ItemStack stack, TagKey<Enchantment> tag) {
        int level = 0;
        ItemEnchantmentsComponent itemEnchantmentsComponent = stack.getOrDefault(DataComponentTypes.ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT);
        Set<Object2IntMap.Entry<RegistryEntry<Enchantment>>> enchantments = itemEnchantmentsComponent.getEnchantmentEntries();
        RegistryEntry<Enchantment> registryEntry;
        for (Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : enchantments) {
            registryEntry = entry.getKey();
            if (registryEntry.isIn(tag)) {
                level += Math.abs(EnchantmentHelper.getLevel(registryEntry, stack));
            }
        }
        return level;
    }
}
