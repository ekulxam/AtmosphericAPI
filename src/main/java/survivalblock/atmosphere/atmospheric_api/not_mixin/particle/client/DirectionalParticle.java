package survivalblock.atmosphere.atmospheric_api.not_mixin.particle.client;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
//? if >=1.21.9 {
/*import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.renderer.state.QuadParticleRenderState;
*///?}
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import survivalblock.atmosphere.atmospheric_api.not_mixin.funny.BadAtProgramming;
import survivalblock.atmosphere.atmospheric_api.not_mixin.util.Masonry;

/**
 * A non-billboard particle
 * @see net.minecraft.client.particle.SingleQuadParticle
 */
@Environment(EnvType.CLIENT)
public abstract class DirectionalParticle extends Particle {

    protected float scale = 0.1F * (this.random.nextFloat() * 0.5F + 0.5F) * 2.0F;
    protected float pitch = 0;
    protected float yaw = 0;

    //? if >=1.21.9 {
    /*protected float red = 1.0F;
    protected float green = 1.0F;
    protected float blue = 1.0F;
    protected float alpha = 1.0F;
    *///?}

    protected DirectionalParticle(ClientLevel clientWorld, double d, double e, double f) {
        super(clientWorld, d, e, f);
    }

    protected DirectionalParticle(ClientLevel clientWorld, double d, double e, double f, double g, double h, double i) {
        super(clientWorld, d, e, f, g, h, i);
    }

    //? if <1.21.9
    @Override
    public void render(/*? >=1.21.9 {*/ /*QuadParticleRenderState state *//*?} else {*/ VertexConsumer vertexConsumer /*?}*/, Camera camera, float tickProgress) {
        Quaternionf quaternionf = new Quaternionf()
                .rotationYXZ(-this.yaw * Mth.DEG_TO_RAD, this.pitch * Mth.DEG_TO_RAD, 0);
        switch (this.getRenderMode()) {
            case RENDER_TWICE -> {
                this.render(/*? >=1.21.9 {*/ /*state *//*?} else {*/ vertexConsumer /*?}*/, camera, quaternionf, tickProgress);
                quaternionf.rotateY((float) -Math.PI);
                this.render(/*? >=1.21.9 {*/ /*state *//*?} else {*/ vertexConsumer /*?}*/, camera, quaternionf, tickProgress);
            }
            case FRONTFACE -> {
                // TODO: fix this
                Vec3 particleDirection = Vec3.directionFromRotation(this.yaw, this.pitch).normalize();
                //Vec3d view = camera.getPos().subtract(this.x, this.y, this.z).normalize();
                Vec3 view = Vec3.directionFromRotation(camera.getXRot(), camera.getYRot()).normalize();
                double dotProduct = view.dot(particleDirection);
                if (dotProduct < 0) {
                    quaternionf.rotateY((float) Math.PI);
                }
                this.render(/*? >=1.21.9 {*/ /*state *//*?} else {*/ vertexConsumer /*?}*/, camera, quaternionf, tickProgress);
            }
            case null, default -> {
            }
        }
    }

    protected void render(/*? >=1.21.9 {*/ /*QuadParticleRenderState state *//*?} else {*/ VertexConsumer vertexConsumer /*?}*/, Camera camera, Quaternionf quaternionf, float tickProgress) {
        Vec3 vec3d = camera.getPosition();
        float x = (float)(Mth.lerp(tickProgress, this.xo, this.x) - vec3d.x());
        float y = (float)(Mth.lerp(tickProgress, this.yo, this.y) - vec3d.y());
        float z = (float)(Mth.lerp(tickProgress, this.zo, this.z) - vec3d.z());
        this.render(/*? >=1.21.9 {*/ /*state *//*?} else {*/ vertexConsumer /*?}*/, quaternionf, x, y, z, tickProgress);
    }

    protected void render(/*? >=1.21.9 {*/ /*QuadParticleRenderState state *//*?} else {*/ VertexConsumer vertexConsumer /*?}*/, Quaternionf quaternionf, float x, float y, float z, float tickProgress) {
        float size = this.getSize(tickProgress);
        float minU = this.getMinU();
        float maxU = this.getMaxU();
        float minV = this.getMinV();
        float maxV = this.getMaxV();
        int brightness = this.getLightColor(tickProgress);
        //? if >=1.21.9 {
        /*state.add(this.getLayer(), x, y, z, quaternionf.x, quaternionf.y, quaternionf.z, quaternionf.w, size, minU, maxU, minV, maxV, Masonry.ColorHelper.fromFloats(this.alpha, this.red, this.green, this.blue), brightness);
        *///?} else {
        this.render(vertexConsumer, quaternionf, x, y, z, 1.0F, -1.0F, size, maxU, maxV, brightness);
        this.render(vertexConsumer, quaternionf, x, y, z, 1.0F, 1.0F, size, maxU, minV, brightness);
        this.render(vertexConsumer, quaternionf, x, y, z, -1.0F, 1.0F, size, minU, minV, brightness);
        this.render(vertexConsumer, quaternionf, x, y, z, -1.0F, -1.0F, size, minU, maxV, brightness);
        //?}
    }

    //? if <1.21.9 {
    private void render(
            VertexConsumer vertexConsumer, Quaternionf quaternionf, float x, float y, float z, float f, float g, float size, float l, float u, int v
    ) {
        Vector3f vector3f = new Vector3f(f, g, 0.0F).rotate(quaternionf).mul(size).add(x, y, z);
        vertexConsumer.addVertex(vector3f.x(), vector3f.y(), vector3f.z()).setUv(l, u)
                .setColor(this.rCol, this.gCol, this.bCol, this.alpha).setLight(v);
    }
    //?}

    public RenderMode getRenderMode() {
        return RenderMode.RENDER_TWICE;
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

    //? if >=1.21.9 {
    /*@SuppressWarnings("unused")
    public void setColor(float red, float green, float blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @SuppressWarnings("unused")
    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    protected abstract SingleQuadParticle.Layer getLayer();
    *///?}

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
        /**
         * Similar to backface culling for optimization. Because the particle only
         * renders once by default, this value allows the quaternion to rotate
         * according to the camera before rendering.
         *
         * @deprecated This currently doesn't work.
         */
        @BadAtProgramming
        @Deprecated
        FRONTFACE
    }
}
