/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.mixin.particle.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
//? if >=1.21.9
/*import net.fabricmc.fabric.api.client.particle.v1.ParticleRendererRegistry;*/
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPIClient;
import survivalblock.atmosphere.atmospheric_api.not_mixin.funny.ThisIsABadIdea;
import survivalblock.atmosphere.atmospheric_api.not_mixin.particle.DirectionalParticleS2CPayload;
//? if >=1.21.9
/*import survivalblock.atmosphere.atmospheric_api.not_mixin.particle.client.render.DirectionalParticleRenderer;*/

@ApiStatus.Internal
@ThisIsABadIdea(ThisIsABadIdea.LevelsOfHorrendousness.PROBABLY)
@Environment(EnvType.CLIENT)
@Mixin(value = AtmosphericAPIClient.class, remap = false)
public class AtmosphericAPIClientMixin {

    @Inject(method = "onInitializeClient", at = @At("HEAD"))
    private void handleDirectionalParticlePayloadReceiving(CallbackInfo ci) {
        ClientPlayNetworking.registerGlobalReceiver(DirectionalParticleS2CPayload.ID, (payload, context) -> {
            ClientLevel world = context.client().level;
            RandomSource random = world.getRandom();
            double g = random.nextGaussian() * payload.deltaX();
            double h = random.nextGaussian() * payload.deltaY();
            double j = random.nextGaussian() * payload.deltaZ();

            try {
                world.addParticle(payload.particleEffect(),
                        payload.force(), /*? >=1.21.5 {*/ payload.canSpawnOnMinimal(), /*?}*/
                        payload.x() + g, payload.y() + h, payload.z()+ j,
                        payload.velocityX(), payload.velocityY(), payload.velocityZ());
            } catch (Throwable throwable) {
                AtmosphericAPI.LOGGER.warn("Could not spawn particle effect {}", payload.particleEffect());
            }
        });
        //? if >=1.21.9
        /*ParticleRendererRegistry.register(DirectionalParticleRenderer.DIRECTIONAL, DirectionalParticleRenderer::new);*/
    }
}
