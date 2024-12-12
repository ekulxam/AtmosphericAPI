package survivalblock.atmosphere.atmospheric_api.not_mixin.datagen;

import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@SuppressWarnings("unused")
public final class AtmosphericItemModelGenerationPreventer {

    private static final List<Item> items = new ArrayList<>();

    public static void doNotGenerateItemModelFor(ItemConvertible itemConvertible) {
        items.add(itemConvertible.asItem());
    }

    public static void doNotGenerateItemModelFor(ItemConvertible... itemConvertibles) {
        items.addAll(Arrays.stream(itemConvertibles).map(ItemConvertible::asItem).toList());
    }

    public static void doNotGenerateItemModelFor(Collection<ItemConvertible> itemConvertibles) {
        items.addAll(itemConvertibles.stream().map(ItemConvertible::asItem).toList());
    }

    public static boolean shouldPreventItemModelGeneration(ItemConvertible itemConvertible) {
        return items.contains(itemConvertible.asItem());
    }
}
