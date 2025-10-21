package survivalblock.atmosphere.atmospheric_api.not_mixin.util;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import net.minecraft.util.math.ColorHelper;

@SuppressWarnings("unused")
public final class Sequence {

    private Sequence() {
    }

    public static MutableText scrollingGradient(Text original, int wrap, float reciprocalWrap, int startColor, int endColor, boolean forward) {
        String string = original.getString();
        int length = string.length();
        //noinspection NonStrictComparisonCanBeEquality
        if (length <= 0) {
            return original instanceof MutableText mutableText ? mutableText : original.copy();
        }
        long time = Util.getMeasuringTimeMs();
        MutableText text = Text.literal(string.substring(0, 1)).withColor(ColorHelper/*? =1.21.1 {*/  /*.Argb *//*?}*/.lerp(((time) % wrap) * reciprocalWrap, startColor, endColor));
        float incr = (float) wrap / length;
        for (int i = 1; i < length; i++) {
            float deltaHalfCalculated;
            if (forward) {
                deltaHalfCalculated = time - (int) (incr * i);
            } else {
                deltaHalfCalculated = time + (int) (incr * i);
            }
            text.append(Text.literal(string.substring(i, i + 1)).withColor(ColorHelper/*? =1.21.1 {*/  /*.Argb *//*?}*/.lerp((deltaHalfCalculated % wrap) * reciprocalWrap, startColor, endColor)));
        }
        return text;
    }
}
