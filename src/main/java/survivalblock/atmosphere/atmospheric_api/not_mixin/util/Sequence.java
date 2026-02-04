/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.util;

import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

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
        MutableComponent text = Component.literal(string.substring(0, 1)).withColor(Masonry.ColorHelper.lerp(((time) % wrap) * reciprocalWrap, startColor, endColor));
        float incr = (float) wrap / length;
        for (int i = 1; i < length; i++) {
            float deltaHalfCalculated;
            if (forward) {
                deltaHalfCalculated = time - (int) (incr * i);
            } else {
                deltaHalfCalculated = time + (int) (incr * i);
            }
            text.append(Component.literal(string.substring(i, i + 1)).withColor(Masonry.ColorHelper.lerp((deltaHalfCalculated % wrap) * reciprocalWrap, startColor, endColor)));
        }
        return text;
    }
}
