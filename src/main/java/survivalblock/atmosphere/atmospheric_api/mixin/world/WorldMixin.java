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
package survivalblock.atmosphere.atmospheric_api.mixin.world;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import survivalblock.atmosphere.atmospheric_api.not_mixin.world.injected_interface.AtmosphericWorldRegistryShenanigans;

import java.util.Optional;

@Mixin(Level.class)
public abstract class WorldMixin implements AtmosphericWorldRegistryShenanigans {

    @Shadow public abstract RegistryAccess registryAccess();

    //? if =1.21.1 {
    
    /*@Override
    @Nullable
    public <T> Holder.Reference<T> atmospheric_api$getEntryFromKey(ResourceKey<? extends Registry<? extends T>> dynamicRegistryRegistryKey, ResourceKey<T> key) {
        return this.registryAccess().registryOrThrow(dynamicRegistryRegistryKey).getHolder(key).orElse(null);
    }

    @Override
    public <T> Holder.Reference<T> atmospheric_api$getEntryFromKeyOrThrow(ResourceKey<? extends Registry<? extends T>> dynamicRegistryRegistryKey, ResourceKey<T> key) {
        return this.registryAccess().registryOrThrow(dynamicRegistryRegistryKey).getHolderOrThrow(key);
    }
     *///?} elif >=1.21.8 {

    @Override
    @Nullable
    public <T> Holder.Reference<T> atmospheric_api$getEntryFromKey(ResourceKey<? extends Registry<? extends T>> dynamicRegistryRegistryKey, ResourceKey<T> key) {
        Optional<Registry<T>> optional = this.registryAccess().lookup(dynamicRegistryRegistryKey);
        return optional.flatMap(registry -> registry.get(key)).orElse(null);
    }

    /**
     * @deprecated {@link RegistryAccess#getOrThrow(ResourceKey)} now exists
     */
    @Override
    @Deprecated
    public <T> Holder.Reference<T> atmospheric_api$getEntryFromKeyOrThrow(ResourceKey<? extends Registry<? extends T>> dynamicRegistryRegistryKey, ResourceKey<T> key) {
        return this.registryAccess().lookupOrThrow(dynamicRegistryRegistryKey).getOrThrow(key);
    }
    //?}
}
