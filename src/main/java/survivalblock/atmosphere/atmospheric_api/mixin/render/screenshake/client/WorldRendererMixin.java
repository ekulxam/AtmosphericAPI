/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.mixin.render.screenshake.client;

//? if <1.21.11
//import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
//? if >=1.21.11
import net.minecraft.client.Camera;
//? if >=1.21.11
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
//? if <1.21.11
//import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.atmospheric_api.not_mixin.render.screenshake.client.ClientScreenShaker;

@Mixin(LevelRenderer.class)
public class WorldRendererMixin {

    //? if >=1.21.11 {
    @WrapMethod(method = "extractLevel")
    private void offsetCamera(DeltaTracker deltaTracker, Camera camera, float deltaPartialTick, Operation<Void> original) {
    //?} else {
    /*@ModifyExpressionValue(method = "renderLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Camera;getPosition()Lnet/minecraft/world/phys/Vec3;"))
    private Vec3 shakeCamera(Vec3 original) {
    *///?}
        ClientScreenShaker clientScreenShaker = ClientScreenShaker.getActiveInstance();
        if (clientScreenShaker == null || !clientScreenShaker.shouldShake()) {
            //? if >=1.21.11 {
            original.call(deltaTracker, camera, deltaPartialTick);
            return;
            //?} else {
            /*return original;
            *///?}
        }
        RandomSource random = ClientScreenShaker.RANDOM;
        float intensity = clientScreenShaker.getIntensity() * ClientScreenShaker.IntensityMultiplier.MODIFY_EVENT.invoker().getIntensityPercentage(clientScreenShaker);
        Vec3 offset = new Vec3(
                Mth.randomBetween(random, -intensity, intensity),
                Mth.randomBetween(random, -intensity, intensity),
                Mth.randomBetween(random, -intensity, intensity)
        );
        //? if >=1.21.11 {
        Vec3 prevPos = camera.position();
        ((CameraAccessor) camera).atmospheric_api$invokeSetPosition(prevPos.add(offset));
        original.call(deltaTracker, camera, deltaPartialTick);
        ((CameraAccessor) camera).atmospheric_api$invokeSetPosition(prevPos);
        //?} else {
        /*return original.add(
                offset
        );
        *///?}
    }
}
