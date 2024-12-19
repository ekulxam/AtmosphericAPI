package survivalblock.atmosphere.atmospheric_api.not_mixin.render.screenshake;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

@Environment(EnvType.SERVER)
@SuppressWarnings("unused")
public class GlobalServerScreenShaker extends BasicScreenShaker implements ActiveScreenShaker {

    public GlobalServerScreenShaker(float intensity, int duration) {
        super(intensity, duration);
    }

    @Override
    public void activate(World world) {
        if (!(world instanceof ServerWorld serverWorld)) {
            throw new IllegalStateException("Cannot activate a GlobalScreenShaker when on the client! How did we even get here?");
        }
        serverWorld.getPlayers().forEach(serverPlayer -> ServerPlayNetworking.send(serverPlayer, createPayload()));
    }

    protected ScreenShakeS2CPayload createPayload() {
        return new ScreenShakeS2CPayload(this.intensity, this.duration);
    }
}
