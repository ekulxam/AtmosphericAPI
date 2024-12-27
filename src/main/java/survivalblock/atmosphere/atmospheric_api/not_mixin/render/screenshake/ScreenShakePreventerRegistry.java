package survivalblock.atmosphere.atmospheric_api.not_mixin.render.screenshake;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

@SuppressWarnings("unused")
public class ScreenShakePreventerRegistry {

    public static final Event<AllowShaking> ALLOW_SHAKING = EventFactory.createArrayBacked(AllowShaking.class, listeners -> (screenShaker) -> {
        for (AllowShaking listener : listeners) {
            if (!listener.allowScreenShaking(screenShaker)) {
                return false;
            }
        }
        return true;
    });

    @FunctionalInterface
    public interface AllowShaking {
        boolean allowScreenShaking(ScreenShaker screenShaker);
    }
}
