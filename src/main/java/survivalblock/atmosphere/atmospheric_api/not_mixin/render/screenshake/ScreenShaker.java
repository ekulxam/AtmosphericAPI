package survivalblock.atmosphere.atmospheric_api.not_mixin.render.screenshake;

import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

@SuppressWarnings("unused")
public interface ScreenShaker {

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
}
