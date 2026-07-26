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
package survivalblock.atmosphere.atmospheric_api.not_mixin.datagen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import survivalblock.atmosphere.atmospheric_api.not_mixin.funny.IsThisEvenNecessary;

@Deprecated(since = "3.3.0")
@IsThisEvenNecessary(IsThisEvenNecessary.Levels.PROBABLY_NOT)
@SuppressWarnings("unused")
public class RegistryEntryLookupContainer {

    @Nullable
    private BootstrapContext<?> registerable = null;

    @Nullable
    private HolderLookup.Provider wrapperLookup = null;

    public RegistryEntryLookupContainer(@NotNull BootstrapContext<?> registerable) {
        this.registerable = registerable;
    }

    public RegistryEntryLookupContainer(@NotNull HolderLookup.Provider wrapperLookup) {
        this.wrapperLookup = wrapperLookup;
    }

    public <T> HolderGetter<T> get(ResourceKey<? extends Registry<? extends T>> registryKey) {
        if (this.registerable != null) {
            return registerable.lookup(registryKey);
        }
        if (this.wrapperLookup != null) {
            return wrapperLookup./*? =1.21.1 {*/ /*lookupOrThrow *//*?} else {*/ lookupOrThrow /*?}*/(registryKey);
        }
        throw new IllegalStateException("A RegistryEntryLookupContainer cannot have both its fields be null!");
    }
}