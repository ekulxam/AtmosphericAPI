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

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

import java.util.concurrent.CompletableFuture;

/**
 * @deprecated Just use {@linkplain FabricDynamicRegistryProvider} or {@linkplain AtmosphericDynamicRegistriesProvider}
 */
@Deprecated(since = "3.1.4")
@SuppressWarnings("unused")
public abstract class AtmosphericDynamicRegistryProvider<T> extends FabricDynamicRegistryProvider {

    protected final ResourceKey<? extends Registry<T>> registryRef;

    public AtmosphericDynamicRegistryProvider(FabricPackOutput output, ResourceKey<? extends Registry<T>> registryRef, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
        this.registryRef = registryRef;
    }

    @Override
    protected void configure(HolderLookup.Provider wrapperLookup, Entries entries) {
        this.configure(wrapperLookup, entries, new RegistryEntryLookupContainer(wrapperLookup));
    }

    protected abstract void configure(HolderLookup.Provider wrapperLookup, Entries entries, RegistryEntryLookupContainer container);

    @Override
    public String getName() {
        //~ if >=1.21.11 'location()' -> 'identifier()'
        return "Dynamic Registry for " + registryRef.identifier();
    }
}
