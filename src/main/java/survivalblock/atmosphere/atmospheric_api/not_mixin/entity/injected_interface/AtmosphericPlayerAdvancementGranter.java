/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.entity.injected_interface;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

@SuppressWarnings("unused")
public interface AtmosphericPlayerAdvancementGranter {

    default boolean atmospheric_api$grantAdvancement(ResourceLocation advancementId) {
        return this.atmospheric_api$grantAdvancement(advancementId, false);
    }

    /**
     * Grants an advancement to a {@link ServerPlayer}
     * @param advancementId  the {@link ResourceLocation} of the {@link net.minecraft.advancements.AdvancementHolder}
     * @param verboseLogging If errors should be logged
     * @return true if the advancement was not previously granted and was now granted successfully
     */
    default boolean atmospheric_api$grantAdvancement(ResourceLocation advancementId, boolean verboseLogging) {
        return false;
    }
}
