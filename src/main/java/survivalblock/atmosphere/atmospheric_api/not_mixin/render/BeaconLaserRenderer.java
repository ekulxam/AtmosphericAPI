package survivalblock.atmosphere.atmospheric_api.not_mixin.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import survivalblock.atmosphere.atmospheric_api.not_mixin.util.Masonry;

/**
 * @see net.minecraft.client.renderer.blockentity.BeaconRenderer#renderBeaconBeam(PoseStack, MultiBufferSource, float, long, int, int, int)
 */
@SuppressWarnings({"unused", "JavadocReference"})
public final class BeaconLaserRenderer {

    public static void renderBeamTheRealWay(PoseStack matrices, MultiBufferSource vertexConsumers, ResourceLocation textureId, float tickDelta, float heightScale, long worldTime, double yOffset, double maxY, int rgb, float innerRadius, float outerRadius) {
        renderBeamTheRealWay(matrices, vertexConsumers, textureId, tickDelta, heightScale, worldTime, yOffset, maxY, rgb, innerRadius, outerRadius, false, Int2IntFunction.identity());
    }

    /**
     * Renders a beacon beam in the world.</p>
     * Changes - yOffset and maxY are now doubles, added middleOfBlock and innerWithAlpha parameters
     *
     * @param textureId what texture should be rendered, beacon uses {@link net.minecraft.client.renderer.blockentity.BeaconRenderer#BEAM_LOCATION}
     * @param heightScale default 1.0F
     * @param yOffset is now a double
     * @param maxY is now a double
     * @param innerRadius default 0.2F
     * @param outerRadius default 0.25F
     * @param middleOfBlock good for beacons, bad for entities
     * @param innerWithAlpha default just returns rgb
     */
    // make them floats instead? maybe
    public static void renderBeamTheRealWay(PoseStack matrices, MultiBufferSource vertexConsumers, ResourceLocation textureId, float tickDelta, float heightScale, long worldTime, double yOffset, double maxY, int rgb, float innerRadius, float outerRadius, boolean middleOfBlock, Int2IntFunction innerWithAlpha) {
        double i = yOffset + maxY;
        matrices.pushPose();

        if (middleOfBlock) {
            matrices.translate(0.5, 0.0, 0.5);
        }

        float f = (float) Math.floorMod(worldTime, 40) + tickDelta;
        float g = maxY < 0 ? f : -f;
        float h = Mth.frac(g * 0.2f - (float)Mth.floor(g * 0.1f));

        matrices.pushPose();
        matrices.mulPose(Axis.YP.rotationDegrees(f * 2.25f - 45.0f));

        // I'm keeping the decompiler nonsense and you can't stop me
        float j;
        float k = innerRadius;
        float l = innerRadius;
        float m;
        float n = -innerRadius;
        float o;
        float p;
        float q = -innerRadius;
        float t = -1.0f + h;
        float u = (float) maxY * heightScale * (0.5f / innerRadius) + t;
        renderBeamLayer(matrices, vertexConsumers.getBuffer(RenderType.beaconBeam(textureId, false)), innerWithAlpha.apply(rgb), yOffset, i, 0.0f, k, l, 0.0f, n, 0.0f, 0.0f, q, 0.0f, 1.0f, u, t);
        matrices.popPose();

        j = -outerRadius;
        k = -outerRadius;
        l = outerRadius;
        m = -outerRadius;
        n = -outerRadius;
        o = outerRadius;
        p = outerRadius;
        q = outerRadius;
        t = -1.0f + h;
        u = (float) maxY * heightScale + t;
        renderBeamLayer(matrices, vertexConsumers.getBuffer(RenderType.beaconBeam(textureId, true)), Masonry.ColorHelper.withAlpha(32, rgb), yOffset, i, j, k, l, m, n, o, p, q, 0.0f, 1.0f, u, t);
        matrices.popPose();
    }

    public static void renderBeamLayer(PoseStack matrices, VertexConsumer vertices, int color, double yOffset, double height, float x1, float z1, float x2, float z2, float x3, float z3, float x4, float z4, float u1, float u2, float v1, float v2) {
        PoseStack.Pose entry = matrices.last();
        renderBeamFace(entry, vertices, color, yOffset, height, x1, z1, x2, z2, u1, u2, v1, v2);
        renderBeamFace(entry, vertices, color, yOffset, height, x4, z4, x3, z3, u1, u2, v1, v2);
        renderBeamFace(entry, vertices, color, yOffset, height, x2, z2, x4, z4, u1, u2, v1, v2);
        renderBeamFace(entry, vertices, color, yOffset, height, x3, z3, x1, z1, u1, u2, v1, v2);
    }

    public static void renderBeamFace(PoseStack.Pose matrix, VertexConsumer vertices, int color, double yOffset, double height, float x1, float z1, float x2, float z2, float u1, float u2, float v1, float v2) {
        renderBeamVertex(matrix, vertices, color, height, x1, z1, u2, v1);
        renderBeamVertex(matrix, vertices, color, yOffset, x1, z1, u2, v2);
        renderBeamVertex(matrix, vertices, color, yOffset, x2, z2, u1, v2);
        renderBeamVertex(matrix, vertices, color, height, x2, z2, u1, v1);
    }

    public static void renderBeamVertex(PoseStack.Pose matrix, VertexConsumer vertices, int color, double y, float x, float z, float u, float v) {
        vertices.addVertex(matrix, x, (float)y, z).setColor(color).setUv(u, v).setOverlay(OverlayTexture.NO_OVERLAY).setLight(LightTexture.FULL_BRIGHT).setNormal(matrix, 0.0f, 1.0f, 0.0f);
    }
}
