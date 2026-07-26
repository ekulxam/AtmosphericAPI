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

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import survivalblock.atmosphere.atmospheric_api.not_mixin.funny.IsThisEvenNecessary;

import java.util.Locale;
import java.util.function.BinaryOperator;

@IsThisEvenNecessary(IsThisEvenNecessary.Levels.PROBABLY_NOT)
@SuppressWarnings("unused")
public enum BooleanOperations implements StringRepresentable {
    /**
     * Represents the logical AND ({@code &&}) operator
     */
    AND((one, two) -> one && two),
    /**
     * Represents the logical OR ({@code ||}) operator
     */
    OR((one, two) -> one || two),
    /**
     * Represents the bitwise AND ({@code &}) operator
     */
    BAND((one, two) -> one & two),
    /**
     * Represents the bitwise OR ({@code |}) operator
     */
    BOR((one, two) -> one | two),
    /**
     * Represents the exclusive OR ({@code ^} or ⊕) operator
     */
    XOR((one, two) -> one ^ two);

    public static final Codec<BooleanOperations> CODEC = StringRepresentable.fromEnum(BooleanOperations::values);

    private final BinaryOperator<Boolean> applicator;
    private final BinaryOperator<Boolean> negator;

    BooleanOperations(BinaryOperator<Boolean> applicator) {
        this.applicator = applicator;
        this.negator = ((one, two) -> !applicator.apply(one, two));
    }

    /**
     * @return the {@linkplain BinaryOperator} associated with this operation
     */
    public BinaryOperator<Boolean> applicator() {
        return this.applicator;
    }

    /**
     * Returns a {@linkplain BinaryOperator} that is the opposite of the original operation.
     * For example, this would return XNOR for {@linkplain BooleanOperations#XOR}.
     * @return the inverse of the {@linkplain BooleanOperations#applicator()}
     */
    public BinaryOperator<Boolean> negator() {
        return this.negator;
    }

    @Override
    public String getSerializedName() {
        return this.name().toLowerCase(Locale.ROOT);
    }
}
