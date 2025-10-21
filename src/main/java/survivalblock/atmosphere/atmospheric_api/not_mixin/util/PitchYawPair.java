package survivalblock.atmosphere.atmospheric_api.not_mixin.util;

import net.minecraft.util.math.MathConstants;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

@SuppressWarnings("unused")
public record PitchYawPair(float pitch, float yaw) {

    public static PitchYawPair fromVec3d(Vec3d vec3d) {
        //noinspection SuspiciousNameCombination
        float pitch = MathHelper.wrapDegrees((float)(MathHelper.atan2(vec3d.x, vec3d.z) * MathConstants.DEGREES_PER_RADIAN));
        float yaw = MathHelper.wrapDegrees((float)(MathHelper.atan2(vec3d.y, vec3d.horizontalLength()) * MathConstants.DEGREES_PER_RADIAN) - 90.0F);
        return new PitchYawPair(pitch, yaw);
    }

    public static PitchYawPair fromVec3ds(Vec3d vec3d, Vec3d target) {
        double d = target.x - vec3d.x;
        double e = target.y - vec3d.y;
        double f = target.z - vec3d.z;
        double g = Math.sqrt(d * d + f * f);
        float pitch = MathHelper.wrapDegrees((float)(-(MathHelper.atan2(e, g) * MathConstants.DEGREES_PER_RADIAN)));
        float yaw = MathHelper.wrapDegrees((float)(MathHelper.atan2(f, d) * MathConstants.DEGREES_PER_RADIAN) - 90.0F);
        return new PitchYawPair(pitch, yaw);
    }

    public Vec3d toVec3d() {
        return Vec3d.fromPolar(this.pitch, this.yaw);
    }
}