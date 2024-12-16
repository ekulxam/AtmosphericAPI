package survivalblock.atmosphere.atmospheric_api.not_mixin.render.screenshake;

import net.minecraft.world.World;

@SuppressWarnings("unused")
public interface ActiveScreenShaker extends ScreenShaker {

    void activate(World world) throws IllegalStateException;
}
