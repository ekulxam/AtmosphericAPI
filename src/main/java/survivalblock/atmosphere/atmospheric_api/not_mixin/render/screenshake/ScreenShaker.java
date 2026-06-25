/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.render.screenshake;

import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;

@SuppressWarnings("unused")
public interface ScreenShaker {

    Identifier DISABLE_ALL_SCREENSHAKERS_RESOURCE_PACK = AtmosphericAPI.id("disableallscreenshakers");
    //~ if >=26 'createNewThreadLocalInstance' -> 'createThreadLocalInstance'
    RandomSource RANDOM = RandomSource.createThreadLocalInstance();

    float getIntensity();

    /**
     * @return the duration of the {@linkplain ScreenShaker} in ticks
     */
    int getDuration();

    default void tick(Level world) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    default String getModId() {
        return AtmosphericAPI.MOD_ID;
    }

    default String getReason() {
        return "";
    }

    default boolean isShakingAllowed() {
        return ScreenShakePreventerRegistry.allowScreenShaking(this);
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    default boolean shouldShake() {
        return this.isShakingAllowed() && this.getDuration() > 0;
    }
}
