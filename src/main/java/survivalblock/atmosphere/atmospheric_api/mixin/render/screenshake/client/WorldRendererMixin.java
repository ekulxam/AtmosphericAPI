package survivalblock.atmosphere.atmospheric_api.mixin.render.screenshake.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.render.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.atmospheric_api.not_mixin.render.screenshake.client.ClientScreenShaker;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

    @ModifyExpressionValue(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/Camera;getPos()Lnet/minecraft/util/math/Vec3d;"))
    private Vec3d shakeCamera(Vec3d original) {
        ClientScreenShaker clientScreenShaker = ClientScreenShaker.getActiveInstance();
        if (clientScreenShaker == null || !clientScreenShaker.shouldShake()) {
            return original;
        }
        Random random = ClientScreenShaker.RANDOM;
        float intensity = clientScreenShaker.getIntensity();
        return original.add(
                MathHelper.nextBetween(random, -intensity, intensity),
                MathHelper.nextBetween(random, -intensity, intensity),
                MathHelper.nextBetween(random, -intensity, intensity));
    }
}
