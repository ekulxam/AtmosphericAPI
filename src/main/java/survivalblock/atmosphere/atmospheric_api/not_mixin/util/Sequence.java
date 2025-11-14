package survivalblock.atmosphere.atmospheric_api.not_mixin.util;

import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
//? if =1.21.1 {
/*import net.minecraft.util.FastColor;
 *///?} elif =1.21.8 {
import net.minecraft.util.ARGB;
//?}

@SuppressWarnings("unused")
public final class Sequence {

    private Sequence() {
    }

    public static MutableComponent scrollingGradient(Component original, int wrap, float reciprocalWrap, int startColor, int endColor, boolean forward) {
        String string = original.getString();
        int length = string.length();
        //noinspection NonStrictComparisonCanBeEquality
        if (length <= 0) {
            return original instanceof MutableComponent mutableText ? mutableText : original.copy();
        }
        long time = Util.getMillis();
        MutableComponent text = Component.literal(string.substring(0, 1)).withColor(/*? =1.21.1 {*/ /*FastColor.ARGB32 *//*?} else {*/ ARGB /*?}*/.lerp(((time) % wrap) * reciprocalWrap, startColor, endColor));
        float incr = (float) wrap / length;
        for (int i = 1; i < length; i++) {
            float deltaHalfCalculated;
            if (forward) {
                deltaHalfCalculated = time - (int) (incr * i);
            } else {
                deltaHalfCalculated = time + (int) (incr * i);
            }
            text.append(Component.literal(string.substring(i, i + 1)).withColor(/*? =1.21.1 {*/ /*FastColor.ARGB32 *//*?} else {*/ ARGB /*?}*/.lerp((deltaHalfCalculated % wrap) * reciprocalWrap, startColor, endColor)));
        }
        return text;
    }
}
