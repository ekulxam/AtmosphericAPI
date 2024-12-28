package survivalblock.atmosphere.atmospheric_api.mixin.render.screenshake.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.atmospheric_api.not_mixin.render.screenshake.client.ClientScreenShaker;
import survivalblock.atmosphere.atmospheric_api.not_mixin.util.AtmosphericUtil;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

    @Shadow private @Nullable ClientWorld world;

    @ModifyExpressionValue(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/Camera;getPos()Lnet/minecraft/util/math/Vec3d;"))
    private Vec3d shakeCamera(Vec3d original, RenderTickCounter tickCounter, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightmapTextureManager lightmapTextureManager, Matrix4f matrix4f, Matrix4f matrix4f2) {
        float tickDelta = tickCounter.getTickDelta(false);
        if (AtmosphericUtil.isBasicallyEqual(tickDelta, 0.0F)) {
            return original;
        }
        ClientScreenShaker clientScreenShaker = ClientScreenShaker.getActiveInstance();
        if (clientScreenShaker == null || !clientScreenShaker.isShakingAllowed() || clientScreenShaker.hasEnded()) {
            return original;
        }
        clientScreenShaker.tick(this.world);
        Random random = ClientScreenShaker.RANDOM;
        float intensity = clientScreenShaker.getIntensity();
        return original.add(
                MathHelper.nextBetween(random, -intensity, intensity),
                MathHelper.nextBetween(random, -intensity, intensity),
                MathHelper.nextBetween(random, -intensity, intensity));
    }
}
