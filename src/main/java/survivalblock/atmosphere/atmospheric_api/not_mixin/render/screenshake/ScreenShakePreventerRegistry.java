package survivalblock.atmosphere.atmospheric_api.not_mixin.render.screenshake;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

@SuppressWarnings("unused")
public final class ScreenShakePreventerRegistry {

    public static final Event<AllowShaking> ALLOW_SHAKING = EventFactory.createArrayBacked(AllowShaking.class, listeners -> (modId, reason) -> {
        for (AllowShaking listener : listeners) {
            if (!listener.allowScreenShaking(modId, reason)) {
                return false;
            }
        }
        return true;
    });

    public static boolean allowScreenShaking(String modId, String reason) {
        return ScreenShakePreventerRegistry.ALLOW_SHAKING.invoker().allowScreenShaking(modId, reason);
    }

    @FunctionalInterface
    public interface AllowShaking {
        boolean allowScreenShaking(String modId, String reason);
    }
}
