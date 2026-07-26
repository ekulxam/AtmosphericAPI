/*
 * Copyright (c) 21:56 UTC 26 July 2026 - present ekulxam
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package survivalblock.atmosphere.atmospheric_api.mixin.particle.client;

//~ if >=26 'ParticleRendererRegistry' -> 'ParticleGroupRegistry' {
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
//? if >=1.21.9
import net.fabricmc.fabric.api.client.particle.v1.ParticleGroupRegistry;
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
import survivalblock.atmosphere.atmospheric_api.not_mixin.particle.client.render.DirectionalParticleRenderer;

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
        ParticleGroupRegistry.register(DirectionalParticleRenderer.DIRECTIONAL, DirectionalParticleRenderer::new);
    }
}
//~}