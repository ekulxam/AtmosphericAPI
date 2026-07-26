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
//? if >=1.21.11 {
package survivalblock.atmosphere.atmospheric_api.mixin.render.screenshake.client;

import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.atmospheric_api.not_mixin.render.screenshake.client.ClientScreenShaker;

@SuppressWarnings({"UnusedMixin", "unused"})
@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Shadow
    @Final
    private Camera mainCamera;

    @Inject(method = "extract", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GameRenderer;extractCamera(Lnet/minecraft/client/DeltaTracker;FF)V", shift = At.Shift.BEFORE))
    private void offsetCamera(DeltaTracker deltaTracker, boolean advanceGameTime, CallbackInfo ci, @Share("atmospheric_api$prevCameraPos")LocalRef<Vec3> localRef) {
        Vec3 prevPos = this.mainCamera.position();
        localRef.set(prevPos);

        ClientScreenShaker clientScreenShaker = ClientScreenShaker.getActiveInstance();
        if (clientScreenShaker == null || !clientScreenShaker.shouldShake()) {
            return;
        }
        RandomSource random = ClientScreenShaker.RANDOM;
        float intensity = clientScreenShaker.getIntensity();
        ((CameraAccessor) this.mainCamera).atmospheric_api$invokeSetPosition(
                prevPos.add(
                        Mth.randomBetween(random, -intensity, intensity),
                        Mth.randomBetween(random, -intensity, intensity),
                        Mth.randomBetween(random, -intensity, intensity)
                )
        );
    }

    @Inject(method = "extract", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/LevelRenderer;extractLevel(Lnet/minecraft/client/DeltaTracker;Lnet/minecraft/client/Camera;F)V", shift = At.Shift.AFTER))
    private void undoCameraOffset(DeltaTracker deltaTracker, boolean advanceGameTime, CallbackInfo ci, @Share("atmospheric_api$prevCameraPos")LocalRef<Vec3> localRef) {
        ((CameraAccessor) this.mainCamera).atmospheric_api$invokeSetPosition(localRef.get());
    }
}
//?}