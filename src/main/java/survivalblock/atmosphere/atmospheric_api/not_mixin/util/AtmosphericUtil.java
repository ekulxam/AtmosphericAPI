package survivalblock.atmosphere.atmospheric_api.not_mixin.util;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.AnimalArmorItem;
import net.minecraft.item.Item;
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

    public static final double CONFIG_FLOATING_POINT_PRECISION = 0.001;

    public static boolean isBasicallyEqual(double value, double original, double goodEnough) {
        return Math.abs(value - original) <= goodEnough;
    }

    public static boolean isBasicallyEqual(float value, float original, float goodEnough) {
        return Math.abs(value - original) <= goodEnough;
    }

    public static boolean isBasicallyEqual(double value, double original) {
        return isBasicallyEqual(value, original, CONFIG_FLOATING_POINT_PRECISION);
    }

    public static boolean isBasicallyEqual(float value, float original) {
        return isBasicallyEqual(value, original, (float) CONFIG_FLOATING_POINT_PRECISION);
    }
}
