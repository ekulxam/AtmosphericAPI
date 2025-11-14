package survivalblock.atmosphere.atmospheric_api.mixin.render.screenshake.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.render.*;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.atmospheric_api.not_mixin.render.screenshake.client.ClientScreenShaker;

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
