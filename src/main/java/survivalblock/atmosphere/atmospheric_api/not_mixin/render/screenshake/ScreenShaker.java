package survivalblock.atmosphere.atmospheric_api.not_mixin.render.screenshake;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;

@SuppressWarnings("unused")
public interface ScreenShaker {

    Identifier DISABLE_ALL_SCREENSHAKERS_RESOURCE_PACK = AtmosphericAPI.id("disableallscreenshakers");
    Random RANDOM = Random.createLocal();

    float getIntensity();

    /**
     * Gets the duration of the ScreenShaker
     * @return the duration of the ScreenShaker in ticks
     */
    int getDuration();

    default void tick(World world) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    default String getModId() {
        return AtmosphericAPI.MOD_ID;
    }

    default String getReason() {
        return "";
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    default boolean isShakingAllowed() {
        return ScreenShakePreventerRegistry.allowScreenShaking(this.getModId(), this.getReason());
    }

    default boolean shouldShake() {
        return this.isShakingAllowed() && this.getDuration() > 0;
    }
}
