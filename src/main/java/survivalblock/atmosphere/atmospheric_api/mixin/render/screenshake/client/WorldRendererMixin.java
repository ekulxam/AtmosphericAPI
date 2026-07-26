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
//? if <1.21.11 {
/*package survivalblock.atmosphere.atmospheric_api.mixin.render.screenshake.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.atmospheric_api.not_mixin.render.screenshake.client.ClientScreenShaker;

@SuppressWarnings({"UnusedMixin", "unused"})
@Mixin(LevelRenderer.class)
public class WorldRendererMixin {

    @ModifyExpressionValue(method = "renderLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Camera;getPosition()Lnet/minecraft/world/phys/Vec3;"))
    private Vec3 shakeCamera(Vec3 original) {
        ClientScreenShaker clientScreenShaker = ClientScreenShaker.getActiveInstance();
        if (clientScreenShaker == null || !clientScreenShaker.shouldShake()) {
            return original;
        }
        RandomSource random = ClientScreenShaker.RANDOM;
        float intensity = clientScreenShaker.getIntensity();
        return original.add(
                Mth.randomBetween(random, -intensity, intensity),
                Mth.randomBetween(random, -intensity, intensity),
                Mth.randomBetween(random, -intensity, intensity));
    }
}
*///?}
