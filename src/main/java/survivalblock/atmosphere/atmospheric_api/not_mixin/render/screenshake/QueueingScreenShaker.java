package survivalblock.atmosphere.atmospheric_api.not_mixin.render.screenshake;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public interface QueueingScreenShaker extends ActiveScreenShaker {

    boolean addToQueue();

    /**
     * Determines whether the ScreenShaker should replace the active instance if its intensity is higher, or if the intensities are the same but the duration is higher
     */
    boolean shouldAutoOverride();

    /**
     * Determines whether the ScreenShaker should be added to the queue if it cannot replace the active instance.
     */
    boolean shouldAddToQueue();
}
