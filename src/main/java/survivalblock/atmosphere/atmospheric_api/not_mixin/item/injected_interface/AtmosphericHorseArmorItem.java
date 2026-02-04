/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.item.injected_interface;

@SuppressWarnings("unused")
public interface AtmosphericHorseArmorItem {

    default boolean atmospheric_api$isHorseArmor() {
        return false;
    }
}
