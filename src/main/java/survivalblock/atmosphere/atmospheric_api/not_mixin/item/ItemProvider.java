package survivalblock.atmosphere.atmospheric_api.not_mixin.item;

import net.minecraft.item.Item;

@SuppressWarnings("unused")
@FunctionalInterface
public interface ItemProvider {

    Item fromSettings(Item.Settings settings);
}
