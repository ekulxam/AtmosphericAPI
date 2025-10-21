package survivalblock.atmosphere.atmospheric_api.not_mixin.particle.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Quaternionf;
import org.joml.Vector3f;

/**
 * A non-billboard particle
 * @see net.minecraft.client.particle.BillboardParticle
 */
public abstract class DirectionalParticle extends Particle {

    protected float scale = 0.1F * (this.random.nextFloat() * 0.5F + 0.5F) * 2.0F;
    protected float pitch = 0;
    protected float yaw = 0;

    protected DirectionalParticle(ClientWorld clientWorld, double d, double e, double f) {
        super(clientWorld, d, e, f);
    }

    protected DirectionalParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
        super(clientWorld, d, e, f, g, h, i);
    }

    @Override
    public void /*? <1.21.4 {*/ /*buildGeometry *//*?} else {*/ render /*?}*/(VertexConsumer vertexConsumer, Camera camera, float tickDelta) {
        Quaternionf quaternionf = new Quaternionf()
                .rotationYXZ(-this.yaw * MathHelper.RADIANS_PER_DEGREE, this.pitch * MathHelper.RADIANS_PER_DEGREE, 0);
        switch (this.getRenderMode()) {
            case RENDER_TWICE -> {
                this.render(vertexConsumer, camera, quaternionf, tickDelta);
                quaternionf.rotateY((float) -Math.PI);
                this.render(vertexConsumer, camera, quaternionf, tickDelta);
            }
            case FRONTFACE -> {
                // TODO: fix this
                Vec3d particleDirection = Vec3d.fromPolar(this.yaw, this.pitch).normalize();
                //Vec3d view = camera.getPos().subtract(this.x, this.y, this.z).normalize();
                Vec3d view = Vec3d.fromPolar(camera.getPitch(), camera.getYaw()).normalize();
                double dotProduct = view.dotProduct(particleDirection);
                if (dotProduct < 0) {
                    quaternionf.rotateY((float) Math.PI);
                }
                this.render(vertexConsumer, camera, quaternionf, tickDelta);
            }
            case null, default -> {
            }
        }
    }

    public RenderMode getRenderMode() {
        return RenderMode.RENDER_TWICE;
    }

    protected void render(VertexConsumer vertexConsumer, Camera camera, Quaternionf quaternionf, float tickProgress) {
        Vec3d vec3d = camera.getPos();
        float x = (float)(MathHelper.lerp(tickProgress, this./*? <1.21.5 {*/ /*prevPosX *//*?} else {*/ lastX /*?}*/, this.x) - vec3d.getX());
        float y = (float)(MathHelper.lerp(tickProgress, this./*? <1.21.5 {*/ /*prevPosY *//*?} else {*/ lastY /*?}*/, this.y) - vec3d.getY());
        float z = (float)(MathHelper.lerp(tickProgress, this./*? <1.21.5 {*/ /*prevPosZ *//*?} else {*/ lastZ /*?}*/, this.z) - vec3d.getZ());
        this.render(vertexConsumer, quaternionf, x, y, z, tickProgress);
    }

    protected void render(VertexConsumer vertexConsumer, Quaternionf quaternionf, float x, float y, float z, float tickProgress) {
        float size = this.getSize(tickProgress);
        float minU = this.getMinU();
        float maxU = this.getMaxU();
        float minV = this.getMinV();
        float maxV = this.getMaxV();
        int brightness = this.getBrightness(tickProgress);
        this.render(vertexConsumer, quaternionf, x, y, z, 1.0F, -1.0F, size, maxU, maxV, brightness);
        this.render(vertexConsumer, quaternionf, x, y, z, 1.0F, 1.0F, size, maxU, minV, brightness);
        this.render(vertexConsumer, quaternionf, x, y, z, -1.0F, 1.0F, size, minU, minV, brightness);
        this.render(vertexConsumer, quaternionf, x, y, z, -1.0F, -1.0F, size, minU, maxV, brightness);
    }

    private void render(
            VertexConsumer vertexConsumer, Quaternionf quaternionf, float x, float y, float z, float f, float g, float size, float l, float u, int v
    ) {
        Vector3f vector3f = new Vector3f(f, g, 0.0F).rotate(quaternionf).mul(size).add(x, y, z);
        vertexConsumer.vertex(vector3f.x(), vector3f.y(), vector3f.z()).texture(l, u).color(this.red, this.green, this.blue, this.alpha).light(v);
    }

    @SuppressWarnings("unused")
    public float getSize(float tickProgress) {
        return this.scale;
    }

    @Override
    public Particle scale(float scale) {
        this.scale *= scale;
        return super.scale(scale);
    }

    protected abstract float getMinU();

    protected abstract float getMaxU();

    protected abstract float getMinV();

    protected abstract float getMaxV();

    public enum RenderMode {

        /**
         * Renders the particle twice, once with the default quaternion rotation and
         * once with the quaternion rotated by {@link Math.PI} to always show both sides.
         */
        RENDER_TWICE,
        @Deprecated
        /**
         * Similar to backface culling for optimization. Because the particle only
         * renders once by default, this value allows the quaternion to rotate
         * according to the camera before rendering.
         *
         * This currently doesn't work.
         */
        FRONTFACE;
    }
}
