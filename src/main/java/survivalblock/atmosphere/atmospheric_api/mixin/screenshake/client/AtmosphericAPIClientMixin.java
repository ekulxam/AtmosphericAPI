package survivalblock.atmosphere.atmospheric_api.mixin.screenshake.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPIClient;
import survivalblock.atmosphere.atmospheric_api.not_mixin.funny.ThisIsProbablyABadIdea;
import survivalblock.atmosphere.atmospheric_api.not_mixin.screenshake.ScreenShaker;

@ThisIsProbablyABadIdea
@Environment(EnvType.CLIENT)
@Mixin(value = AtmosphericAPIClient.class, remap = false)
public class AtmosphericAPIClientMixin {

    @Inject(method = "onInitializeClient", at = @At("HEAD"))
    private void invokeInitializeScreenShakers(CallbackInfo ci) {
        ScreenShaker.initialize();
    }
}
