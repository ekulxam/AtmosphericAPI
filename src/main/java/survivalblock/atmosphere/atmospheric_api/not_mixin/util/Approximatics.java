package survivalblock.atmosphere.atmospheric_api.not_mixin.util;

@SuppressWarnings("unused")
public final class Approximatics {

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
}
