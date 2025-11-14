package survivalblock.atmosphere.atmospheric_api.not_mixin.render.screenshake;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;

@SuppressWarnings("unused")
public interface ScreenShaker {

    ResourceLocation DISABLE_ALL_SCREENSHAKERS_RESOURCE_PACK = AtmosphericAPI.id("disableallscreenshakers");
    RandomSource RANDOM = RandomSource.createNewThreadLocalInstance();

    float getIntensity();

    /**
     * Gets the duration of the ScreenShaker
     * @return the duration of the ScreenShaker in ticks
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
