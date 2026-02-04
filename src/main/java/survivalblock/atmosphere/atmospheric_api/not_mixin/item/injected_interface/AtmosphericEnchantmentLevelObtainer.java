/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.item.injected_interface;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantment;

@SuppressWarnings("unused")
public interface AtmosphericEnchantmentLevelObtainer {

    default int atmospheric_api$getAbsoluteLevel(TagKey<Enchantment> tag) {
        return 0;
    }
}
