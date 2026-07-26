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
package survivalblock.atmosphere.atmospheric_api.not_mixin.resource.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@ApiStatus.Internal
@Environment(EnvType.CLIENT)
public final class AdditionalSplashTextRegistryImpl {

    public static final List<SplashHolder> ADDITIONAL_SPLASHES = new ArrayList<>();

    private AdditionalSplashTextRegistryImpl() {
    }

    public static void register(String string) {
        register(string, 1000);
    }

    public static void register(String string, int priority) {
        ADDITIONAL_SPLASHES.add(new SplashHolder(string, priority));
        ADDITIONAL_SPLASHES.sort(null);
    }

    public record SplashHolder(String splash, int priority) implements Comparable<SplashHolder> {
        @Override
        public int compareTo(@NotNull SplashHolder other) {
            return other.priority - this.priority;
        }
    }
}
