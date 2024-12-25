package survivalblock.atmosphere.atmospheric_api.not_mixin.datagen.injected_interface;

import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;

import java.util.Collection;

@SuppressWarnings("unused")
public interface AtmosphericItemModelGenerationPreventer {

    default void atmospheric_api$excludeFromSimpleItemModelGeneration(Item item) {
    }

    default void atmospheric_api$excludeFromSimpleItemModelGeneration(Item... items) {
    }

    default void atmospheric_api$excludeFromSimpleItemModelGeneration(Collection<Item> items) {
    }

    default void atmospheric_api$excludeConvertibleFromSimpleItemModelGeneration(ItemConvertible itemConvertible) {
    }

    default void atmospheric_api$excludeConvertibleFromSimpleItemModelGeneration(ItemConvertible... itemConvertibles) {
    }

    default void atmospheric_api$excludeConvertibleFromSimpleItemModelGeneration(Collection<ItemConvertible> itemConvertibles) {
    }
}
