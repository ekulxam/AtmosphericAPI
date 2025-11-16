package survivalblock.atmosphere.atmospheric_api.not_mixin.util;

//? if =1.21.1 {
/*import net.minecraft.util.FastColor;
 *///?} elif =1.21.8 {
import net.minecraft.util.ARGB;
//?}

/**
 * Multiversion utilities for Atmospheric API
 * <p>
 * The name makes sense, right? Because stonecutter (multiversion) and masonry... never mind.
 */
@SuppressWarnings("unused")
public class Masonry {

    public static class ColorHelper {

        public static int getRed(int color) {
            return /*? =1.21.1 {*/ /*FastColor.ARGB32 *//*?} else {*/ ARGB /*?}*/.red(color);
        }

        public static int getGreen(int color) {
            return /*? =1.21.1 {*/ /*FastColor.ARGB32 *//*?} else {*/ ARGB /*?}*/.green(color);
        }

        public static int getBlue(int color) {
            return /*? =1.21.1 {*/ /*FastColor.ARGB32 *//*?} else {*/ ARGB /*?}*/.blue(color);
        }

        public static int getAlpha(int color) {
            return /*? =1.21.1 {*/ /*FastColor.ARGB32 *//*?} else {*/ ARGB /*?}*/.alpha(color);
        }

        public static int withAlpha(int alpha, int color) {
            return /*? =1.21.1 {*/ /*FastColor.ARGB32 *//*?} else {*/ ARGB /*?}*/.color(alpha, color);
        }

        public static int fullAlpha(int color) {
            return /*? =1.21.1 {*/ /*FastColor.ARGB32 *//*?} else {*/ ARGB /*?}*/.opaque(color);
        }

        public static int lerp(float delta, int color1, int color2) {
            return /*? =1.21.1 {*/ /*FastColor.ARGB32 *//*?} else {*/ ARGB /*?}*/.lerp(delta, color1, color2);
        }
    }
}
