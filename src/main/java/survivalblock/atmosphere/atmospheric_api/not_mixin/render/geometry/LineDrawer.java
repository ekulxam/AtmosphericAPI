package survivalblock.atmosphere.atmospheric_api.not_mixin.render.geometry;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.List;
import net.minecraft.world.phys.Vec3;

@SuppressWarnings("unused")
public class LineDrawer {

    public static void drawLine(Vec3 pos, Vec3 start, Vec3 end, PoseStack matrixStack, VertexConsumer lines, int color) {
        drawLine(start.subtract(pos).toVector3f(), end.subtract(start), matrixStack, lines, color);
    }

    public static void drawLine(Vec3 start, Vec3 end, PoseStack matrixStack, VertexConsumer lines, int color) {
        drawLine(start.toVector3f(), end.subtract(start), matrixStack, lines, color);
    }

    private static void drawLine(Vector3f offset, Vec3 rotationVec, PoseStack matrixStack, VertexConsumer lines, int color) {
        matrixStack.pushPose();
        PoseStack.Pose entry = matrixStack.last();
        lines.addVertex(entry, offset).setColor(color).setNormal(entry, (float)rotationVec.x, (float)rotationVec.y, (float)rotationVec.z);
        lines.addVertex(entry, (float)(offset.x() + rotationVec.x), (float)(offset.y() + rotationVec.y), (float)(offset.z() + rotationVec.z))
                .setColor(color)
                .setNormal(entry, (float)rotationVec.x, (float)rotationVec.y, (float)rotationVec.z);
        matrixStack.popPose();
    }

    /*
    public static void drawFilledPolygon(List<Vec3d> points, MatrixStack matrixStack, VertexConsumer vertexConsumer, int color) {
        Matrix4f model = matrixStack.peek().getPositionMatrix();
        for (Vec3d vec3d : points) {
            vertexConsumer.vertex(model, (float) vec3d.x, (float) vec3d.y, (float) vec3d.z);
        }
    }
     */
}
