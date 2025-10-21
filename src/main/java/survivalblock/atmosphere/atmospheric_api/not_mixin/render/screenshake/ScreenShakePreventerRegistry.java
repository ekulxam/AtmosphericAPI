package survivalblock.atmosphere.atmospheric_api.not_mixin.render.screenshake;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public final class ScreenShakePreventerRegistry {

    private ScreenShakePreventerRegistry() {
    }

    public static final Event<AllowShaking> ALLOW_SHAKING = EventFactory.createArrayBacked(AllowShaking.class, listeners -> (screenShaker) -> {
        for (AllowShaking listener : listeners) {
            if (!listener.allowScreenShaking(screenShaker)) {
                return false;
            }
        }
        return true;
    });

    public static boolean allowScreenShaking(ScreenShaker screenShaker) {
        return ScreenShakePreventerRegistry.ALLOW_SHAKING.invoker().allowScreenShaking(screenShaker);
    }

    @FunctionalInterface
    public interface AllowShaking {
        boolean allowScreenShaking(ScreenShaker screenShaker);
    }
}
