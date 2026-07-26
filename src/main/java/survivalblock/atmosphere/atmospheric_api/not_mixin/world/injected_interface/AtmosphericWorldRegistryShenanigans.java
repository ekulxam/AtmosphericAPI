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
package survivalblock.atmosphere.atmospheric_api.not_mixin.world.injected_interface;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
public interface AtmosphericWorldRegistryShenanigans {

    @Nullable
    default <T> Holder.Reference<T> atmospheric_api$getEntryFromKey(ResourceKey<? extends Registry<? extends T>> dynamicRegistryRegistryKey, ResourceKey<T> key) {
        return null;
    }

    default <T> Holder.Reference<T> atmospheric_api$getEntryFromKeyOrThrow(ResourceKey<? extends Registry<? extends T>> dynamicRegistryRegistryKey, ResourceKey<T> key) {
        throw new UnsupportedOperationException(this.getClass() + " is an Injected Interface!");
    }
}
