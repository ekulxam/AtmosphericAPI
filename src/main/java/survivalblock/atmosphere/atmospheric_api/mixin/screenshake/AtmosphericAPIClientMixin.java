package survivalblock.atmosphere.atmospheric_api.mixin.screenshake;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPIClient;
import survivalblock.atmosphere.atmospheric_api.not_mixin.screenshake.ScreenShaker;

@Mixin(value = AtmosphericAPIClient.class, remap = false)
public class AtmosphericAPIClientMixin {

    @Inject(method = "onInitializeClient", at = @At("HEAD"))
    private void tickActiveScreenShaker(CallbackInfo ci) {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            ScreenShaker active = ScreenShaker.getActiveInstance();
            if (active != null && !active.hasEnded()) active.tick();
        });
    }
}
