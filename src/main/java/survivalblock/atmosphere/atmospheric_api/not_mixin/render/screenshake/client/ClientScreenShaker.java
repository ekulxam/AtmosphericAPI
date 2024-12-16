package survivalblock.atmosphere.atmospheric_api.not_mixin.render.screenshake;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientLoginConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.atmospheric_api.not_mixin.util.AtmosphericUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public class ScreenShaker {

    /**
     * Stores the ScreenShakers that are waiting to be activated. This list is cleared upon leaving the world.
     */
    private static final List<ScreenShaker> QUEUE = new ArrayList<>();
    protected static ScreenShaker active = null;
    protected static Random random = Random.createLocal();
    protected float intensity;
    protected int duration;
    /**
     * This boolean field determines whether the ScreenShaker should replace the active instance if its intensity is higher, or if the intensities are the same but the duration is higher
     */
    protected boolean shouldAutoOverride = false;
    /**
     * This boolean field determines whether the ScreenShaker should be added to the queue if it cannot replace the active instance.
     */
    protected boolean shouldAddToQueue = false;
    protected boolean ended;

    /**
     * Constructs an instance with an intensity and duration
     * @param intensity The intensity of the ScreenShaker
     * @param duration The duration of the ScreenShaker
     * @throws IllegalStateException if the intensity or duration are less than or equal to 0
     */
    public ScreenShaker(float intensity, int duration) {
        this.intensity = intensity;
        this.duration = duration;
        if (intensity <= 0) {
            throw new IllegalStateException("The intensity of a ScreenShaker cannot be less than or equal to 0! The intensity provided was " + intensity);
        }
        if (duration <= 0) {
            throw new IllegalStateException("The duration of a ScreenShaker cannot be less than or equal to 0! The duration provided was " + duration);
        }
    }

    public ScreenShaker(float intensity, int duration, boolean shouldAutoOverride, boolean shouldAddToQueue) {
        this(intensity, duration);
        this.setShouldAutoOverride(shouldAutoOverride).setShouldAddToQueue(shouldAddToQueue);
    }

    public ScreenShaker setShouldAutoOverride(boolean shouldAutoOverride) {
        this.shouldAutoOverride = shouldAutoOverride;
        return this;
    }

    public ScreenShaker setShouldAddToQueue(boolean shouldAddToQueue) {
        this.shouldAddToQueue = shouldAddToQueue;
        return this;
    }

    public boolean addToQueue() {
        if (this.equals(active)) {
            return false;
        }
        if (!this.shouldAddToQueue) {
            return false;
        }
        return QUEUE.add(this);
    }

    public boolean getShouldAutoOverride() {
        return this.shouldAutoOverride;
    }

    public boolean shouldAddToQueue() {
        return this.shouldAddToQueue;
    }

    public float getIntensity() {
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

    /**
     * Sets the active instance to be this ScreenShaker
     */
    public void activate() {
        if (active == null) {
            active = this;
            return;
        }
        if (this.shouldAutoOverride) active = this;
    }

    /**
     * Ticks the active ScreenShaker. This fails if this instance is not the active instance or if this instance has ended.
     */
    public void tick() {
        if (!Objects.equals(active, this)) {
            return;
        }
        this.duration--;
        if (this.duration <= 0) {
            this.ended = true;
            if (QUEUE.isEmpty()) {
                active = null;
            } else {
                active = QUEUE.getFirst();
                QUEUE.remove(active);
            }
        }
    }

    /**
     * Initializes the events and other logic in {@link survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPIClient#onInitializeClient()}.<p>
     * This should not be called anywhere else. (However, this method is actually called by {@link survivalblock.atmosphere.atmospheric_api.mixin.render.screenshake.client.AtmosphericAPIClientMixin#invokeInitializeScreenShakers(CallbackInfo)} in possible preparation for turning this into a modular API.)
     */
    @ApiStatus.Internal
    @SuppressWarnings("JavadocReference")
    public static void initialize() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (active != null && !active.hasEnded()) active.tick();
        });
        ClientLoginConnectionEvents.DISCONNECT.register((handler, client) -> {
            if (!QUEUE.isEmpty()) QUEUE.clear();
        });
        ClientPlayNetworking.registerGlobalReceiver(ScreenShakeS2CPayload.ID, (payload, context) -> {
            ScreenShaker screenShaker = new ScreenShaker(payload.intensity(), payload.duration())
                    .setShouldAutoOverride(payload.shouldAutoOverride())
                    .setShouldAddToQueue(payload.shouldAddToQueue());
            if (active == null) {
                screenShaker.activate();
                return;
            }
            if (!screenShaker.shouldAutoOverride) {
                if (screenShaker.shouldAddToQueue) {
                    screenShaker.addToQueue();
                }
                return;
            }
            if (screenShaker.intensity > active.intensity) {
                screenShaker.activate();
            } else if (AtmosphericUtil.isBasicallyEqual(screenShaker.intensity, active.intensity, 0.0001)
                    && screenShaker.duration > active.duration) {
                screenShaker.activate();
            } else if (screenShaker.shouldAddToQueue) {
                screenShaker.addToQueue();
            }
        });
    }
}
