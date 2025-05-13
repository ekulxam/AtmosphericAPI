package survivalblock.atmosphere.atmospheric_api.not_mixin.render.geometry;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.List;

@SuppressWarnings("unused")
public class LineDrawer {

    public static void drawLine(Vec3d pos, Vec3d start, Vec3d end, MatrixStack matrixStack, VertexConsumer lines, int color) {
        drawLine(start.subtract(pos).toVector3f(), end.subtract(start), matrixStack, lines, color);
    }

    public static void drawLine(Vec3d start, Vec3d end, MatrixStack matrixStack, VertexConsumer lines, int color) {
        drawLine(start.toVector3f(), end.subtract(start), matrixStack, lines, color);
    }

    private static void drawLine(Vector3f offset, Vec3d rotationVec, MatrixStack matrixStack, VertexConsumer lines, int color) {
        matrixStack.push();
        MatrixStack.Entry entry = matrixStack.peek();
        lines.vertex(entry, offset).color(color).normal(entry, (float)rotationVec.x, (float)rotationVec.y, (float)rotationVec.z);
        lines.vertex(entry, (float)(offset.x() + rotationVec.x), (float)(offset.y() + rotationVec.y), (float)(offset.z() + rotationVec.z))
                .color(color)
                .normal(entry, (float)rotationVec.x, (float)rotationVec.y, (float)rotationVec.z);
        matrixStack.pop();
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
