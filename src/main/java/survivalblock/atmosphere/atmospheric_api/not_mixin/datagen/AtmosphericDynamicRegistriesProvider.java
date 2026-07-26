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

import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AtmosphericDynamicRegistriesProvider extends FabricDynamicRegistryProvider {

    protected final List<ResourceKey<? extends Registry<?>>> keys;

    public AtmosphericDynamicRegistriesProvider(FabricPackOutput output, Collection<ResourceKey<? extends Registry<?>>> keys, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);

        ImmutableList.Builder<ResourceKey<? extends Registry<?>>> builder = ImmutableList.builderWithExpectedSize(keys.size());
        builder.addAll(keys);
        this.keys = builder.build();
    }

    @Override
    protected void configure(HolderLookup.Provider wrapperLookup, Entries entries) {
        this.keys.forEach(resourceKey -> entries.addAll(wrapperLookup.lookupOrThrow(resourceKey)));
    }

    @Override
    public String getName() {
        return "Dynamic Registries";
    }
}
