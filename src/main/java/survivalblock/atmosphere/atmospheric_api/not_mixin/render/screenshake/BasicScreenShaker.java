package survivalblock.atmosphere.atmospheric_api.not_mixin.render.screenshake;

import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;

public abstract class BasicScreenShaker implements ScreenShaker {

    protected final String modId;
    protected final String reason;
    protected float intensity;
    protected int duration;

    /**
     * Constructs an instance with an intensity and duration
     * @param intensity The intensity of the ScreenShaker
     * @param duration The duration of the ScreenShaker
     * @throws IllegalStateException if the intensity or duration are less than or equal to 0
     */
    public BasicScreenShaker(float intensity, int duration) {
        this(intensity, duration, AtmosphericAPI.MOD_ID);
    }

    public BasicScreenShaker(float intensity, int duration, String modId) {
        this(intensity, duration, modId, "");
    }

    public BasicScreenShaker(float intensity, int duration, String modId, String reason) {
        this.intensity = intensity;
        this.duration = duration;
        if (intensity <= 0) {
            throw new IllegalStateException("The intensity of a ScreenShaker cannot be less than or equal to 0! The intensity provided was " + intensity);
        }
        if (duration <= 0) {
            throw new IllegalStateException("The duration of a ScreenShaker cannot be less than or equal to 0! The duration provided was " + duration);
        }
        this.modId = modId;
        this.reason = reason;
    }

    public float getIntensity() {
        return this.intensity;
    }

    public int getDuration() {
        return this.duration;
    }

    public void tick(@Nullable World world) {
        if (this.duration <= 0) {
            return;
        }
        this.duration--;
    }

    @Override
    public String getModId() {
        return this.modId;
    }

    @Override
    public String getReason() {
        return this.reason;
    }
}
