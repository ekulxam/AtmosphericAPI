package survivalblock.atmosphere.atmospheric_api.not_mixin.render.screenshake.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientLoginConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.atmospheric_api.not_mixin.funny.AllowsForChaining;
import survivalblock.atmosphere.atmospheric_api.not_mixin.render.screenshake.BasicScreenShaker;
import survivalblock.atmosphere.atmospheric_api.not_mixin.render.screenshake.QueueingScreenShaker;
import survivalblock.atmosphere.atmospheric_api.not_mixin.render.screenshake.ScreenShakeS2CPayload;
import survivalblock.atmosphere.atmospheric_api.not_mixin.util.AtmosphericUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings({"unused", "UnusedReturnValue"})
@Environment(EnvType.CLIENT)
public class ClientScreenShaker extends BasicScreenShaker implements QueueingScreenShaker {

    /**
     * Stores the ScreenShakers that are waiting to be activated. This list is cleared upon leaving the world.
     */
    private static final List<ClientScreenShaker> QUEUE = new ArrayList<>();
    protected static ClientScreenShaker active = null;
    protected boolean shouldAutoOverride = false;
    protected boolean shouldAddToQueue = false;
    protected boolean ended;

    public ClientScreenShaker(float intensity, int duration) {
        super(intensity, duration);
    }

    public ClientScreenShaker(float intensity, int duration, boolean shouldAutoOverride, boolean shouldAddToQueue) {
        this(intensity, duration);
        this.setShouldAutoOverride(shouldAutoOverride).setShouldAddToQueue(shouldAddToQueue);
    }

    @AllowsForChaining
    public ClientScreenShaker setShouldAutoOverride(boolean shouldAutoOverride) {
        this.shouldAutoOverride = shouldAutoOverride;
        return this;
    }

    @AllowsForChaining
    public ClientScreenShaker setShouldAddToQueue(boolean shouldAddToQueue) {
        this.shouldAddToQueue = shouldAddToQueue;
        return this;
    }

    @Override
    public boolean addToQueue() {
        if (this.equals(active)) {
            return false;
        }
        if (!this.shouldAddToQueue) {
            return false;
        }
        return QUEUE.add(this);
    }

    @Override
    public boolean shouldAutoOverride() {
        return this.shouldAutoOverride;
    }

    @Override
    public boolean shouldAddToQueue() {
        return this.shouldAddToQueue;
    }

    public boolean hasEnded() {
        return this.ended;
    }

    @Nullable
    public static ClientScreenShaker getActiveInstance() {
        return active;
    }

    /**
     * Sets the active instance to be this ScreenShaker
     */
    public void activate(World world) {
        if (!(world instanceof ClientWorld clientWorld)) {
            throw new IllegalStateException("Cannot activate a ClientScreenShaker when not on the client! How did we even get here?");
        }
        if (active == null) {
            active = this;
            return;
        }
        if (this.shouldAutoOverride) active = this;
    }

    /**
     * Ticks the active ScreenShaker. This fails if this instance is not the active instance or if this instance has ended.
     */
    @Override
    public void tick(World world) {
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
            if (active != null && !active.hasEnded()) active.tick(client.world);
        });
        ClientLoginConnectionEvents.DISCONNECT.register((handler, client) -> {
            if (!QUEUE.isEmpty()) QUEUE.clear();
        });
        ClientPlayNetworking.registerGlobalReceiver(ScreenShakeS2CPayload.ID, (payload, context) -> {
            ClientScreenShaker clientScreenShaker = new ClientScreenShaker(
                    payload.intensity(), payload.duration(),
                    payload.shouldAutoOverride(), payload.shouldAddToQueue());
            //noinspection resource
            ClientWorld clientWorld = context.client().world;
            if (active == null) {
                clientScreenShaker.activate(clientWorld);
                return;
            }
            if (!clientScreenShaker.shouldAutoOverride) {
                if (clientScreenShaker.shouldAddToQueue) {
                    clientScreenShaker.addToQueue();
                }
                return;
            }
            if (clientScreenShaker.intensity > active.intensity) {
                clientScreenShaker.activate(clientWorld);
            } else if (AtmosphericUtil.isBasicallyEqual(clientScreenShaker.intensity, active.intensity, 0.0001)
                    && clientScreenShaker.duration > active.duration) {
                clientScreenShaker.activate(clientWorld);
            } else if (clientScreenShaker.shouldAddToQueue) {
                clientScreenShaker.addToQueue();
            }
        });
    }
}
