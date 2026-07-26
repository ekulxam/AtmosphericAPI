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
package survivalblock.atmosphere.atmospheric_api.not_mixin.render.screenshake;

import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;

@SuppressWarnings("unused")
public interface ScreenShaker {

    Identifier DISABLE_ALL_SCREENSHAKERS_RESOURCE_PACK = AtmosphericAPI.id("disableallscreenshakers");
    //~ if >=26 'createNewThreadLocalInstance' -> 'createThreadLocalInstance'
    RandomSource RANDOM = RandomSource.createThreadLocalInstance();

    float getIntensity();

    /**
     * @return the duration of the {@linkplain ScreenShaker} in ticks
     */
    int getDuration();

    default void tick(Level world) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    default String getModId() {
        return AtmosphericAPI.MOD_ID;
    }

    default String getReason() {
        return "";
    }

    default boolean isShakingAllowed() {
        return ScreenShakePreventerRegistry.allowScreenShaking(this);
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    default boolean shouldShake() {
        return this.isShakingAllowed() && this.getDuration() > 0;
    }
}
