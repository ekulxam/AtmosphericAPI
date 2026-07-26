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

@SuppressWarnings("unused")
public final class Approximatics {

    /**
     * @see com.mojang.math.Constants#EPSILON
     */
    public static final double DEFAULT_ACCURACY = 0.001;

    private Approximatics() {
    }

    public static boolean isBasicallyEqual(double value, double original, double goodEnough) {
        return Math.abs(value - original) <= goodEnough;
    }

    public static boolean isBasicallyEqual(float value, float original, float goodEnough) {
        return Math.abs(value - original) <= goodEnough;
    }

    public static boolean isBasicallyEqual(double value, double original) {
        return isBasicallyEqual(value, original, DEFAULT_ACCURACY);
    }

    public static boolean isBasicallyEqual(float value, float original) {
        return isBasicallyEqual(value, original, (float) DEFAULT_ACCURACY);
    }

    public static int clampAbs(long value, int max) {
        return Math.clamp(value, -max, max);
    }

    public static long clampAbs(long value, long max) {
        return Math.clamp(value, -max, max);
    }

    public static float clampAbs(float value, float max) {
        return Math.clamp(value, -max, max);
    }

    public static double clampAbs(double value, double max) {
        return Math.clamp(value, -max, max);
    }
}
