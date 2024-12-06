package survivalblock.atmosphere.atmospheric_api.not_mixin.screenshake;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public class ScreenShaker {

    protected static ScreenShaker active = null;
    protected static Random random = Random.createLocal();
    protected int intensity;
    protected int duration;
    protected boolean ended;

    public ScreenShaker(int intensity, int duration) {
        this.intensity = intensity;
        this.duration = duration;
        if (intensity <= 0 || duration <= 0) {
            this.ended = true;
        }
    }

    public int getIntensity() {
        return this.intensity;
    }

    public int getDuration() {
        return this.duration;
    }

    public boolean hasEnded() {
        return this.ended;
    }

    @Nullable
    public static ScreenShaker getActiveInstance() {
        return active;
    }

    public static Random getRandom() {
        return random;
    }

    public void activate() {
        active = this;
    }

    public void tick() {
        if (!Objects.equals(active, this)) {
            return;
        }
        if (this.ended) {
            return;
        }
        this.duration--;
        if (this.duration <= 0) {
            this.ended = true;
            active = null;
        }
    }
}
