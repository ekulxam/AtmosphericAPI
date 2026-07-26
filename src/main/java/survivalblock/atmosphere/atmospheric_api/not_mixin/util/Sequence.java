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

//~ if >=1.21.11 'minecraft.Util' -> 'minecraft.util.Util'
import net.minecraft.util.Util;
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
