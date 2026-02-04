/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.item;

import net.minecraft.world.item.Item;

@SuppressWarnings("unused")
@FunctionalInterface
public interface ItemProvider<S extends Item.Properties, T extends Item> {

    T fromSettings(S settings);
}
