/*
 * Copyright (c) 21:56 UTC 26 July 2026 - present ekulxam
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.util;

import com.mojang.math.Constants;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

@SuppressWarnings("unused")
public record PitchYawPair(float pitch, float yaw) {

    public static PitchYawPair fromVec3d(Vec3 vec3d) {
        //noinspection SuspiciousNameCombination
        float pitch = Mth.wrapDegrees((float)(Mth.atan2(vec3d.x, vec3d.z) * Constants.RAD_TO_DEG));
        float yaw = Mth.wrapDegrees((float)(Mth.atan2(vec3d.y, vec3d.horizontalDistance()) * Constants.RAD_TO_DEG) - 90.0F);
        return new PitchYawPair(pitch, yaw);
    }

    public static PitchYawPair fromVec3ds(Vec3 vec3d, Vec3 target) {
        double d = target.x - vec3d.x;
        double e = target.y - vec3d.y;
        double f = target.z - vec3d.z;
        double g = Math.sqrt(d * d + f * f);
        float pitch = Mth.wrapDegrees((float)(-(Mth.atan2(e, g) * Constants.RAD_TO_DEG)));
        float yaw = Mth.wrapDegrees((float)(Mth.atan2(f, d) * Constants.RAD_TO_DEG) - 90.0F);
        return new PitchYawPair(pitch, yaw);
    }

    public Vec3 toVec3d() {
        return Vec3.directionFromRotation(this.pitch, this.yaw);
    }
}