package survivalblock.atmosphere.atmospheric_api.not_mixin.entity.injected_interface;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

@SuppressWarnings("unused")
public interface AtmosphericPlayerAdvancementGranter {

    default boolean atmospheric_api$grantAdvancement(Identifier advancementId) {
        return this.atmospheric_api$grantAdvancement(advancementId, false);
    }

    /**
     * Grants an advancement to a {@link ServerPlayerEntity}
     * @param advancementId  the {@link Identifier} of the {@link net.minecraft.advancement.AdvancementEntry}
     * @param verboseLogging If errors should be logged
     * @return true if the advancement was not previously granted and was now granted successfully
     */
    default boolean atmospheric_api$grantAdvancement(Identifier advancementId, boolean verboseLogging) {
        return false;
    }
}
