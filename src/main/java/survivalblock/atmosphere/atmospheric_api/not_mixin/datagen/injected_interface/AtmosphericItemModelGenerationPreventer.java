package survivalblock.atmosphere.atmospheric_api.not_mixin.datagen.injected_interface;

import java.util.Collection;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

@SuppressWarnings("unused")
public interface AtmosphericItemModelGenerationPreventer {

    default void atmospheric_api$excludeFromSimpleItemModelGeneration(Item item) {
    }

    default void atmospheric_api$excludeFromSimpleItemModelGeneration(Item... items) {
    }

    default void atmospheric_api$excludeFromSimpleItemModelGeneration(Collection<Item> items) {
    }

    default void atmospheric_api$excludeConvertibleFromSimpleItemModelGeneration(ItemLike itemConvertible) {
    }

    default void atmospheric_api$excludeConvertibleFromSimpleItemModelGeneration(ItemLike... itemConvertibles) {
    }

    default void atmospheric_api$excludeConvertibleFromSimpleItemModelGeneration(Collection<ItemLike> itemConvertibles) {
    }
}
