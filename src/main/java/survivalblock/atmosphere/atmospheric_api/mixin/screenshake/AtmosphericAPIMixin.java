package survivalblock.atmosphere.atmospheric_api.mixin.screenshake;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;
import survivalblock.atmosphere.atmospheric_api.not_mixin.funny.ThisIsProbablyABadIdea;
import survivalblock.atmosphere.atmospheric_api.not_mixin.screenshake.ScreenShakeS2CPayload;

@ThisIsProbablyABadIdea
@Mixin(value = AtmosphericAPI.class, remap = false)
public class AtmosphericAPIMixin {

    @Inject(method = "onInitialize", at = @At("RETURN"))
    private void registerScreenShakePayload(CallbackInfo ci) {
        PayloadTypeRegistry.playS2C().register(ScreenShakeS2CPayload.ID, ScreenShakeS2CPayload.CODEC);
    }
}
