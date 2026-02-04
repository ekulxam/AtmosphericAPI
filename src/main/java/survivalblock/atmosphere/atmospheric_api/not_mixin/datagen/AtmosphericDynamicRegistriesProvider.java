/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.datagen;

import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AtmosphericDynamicRegistriesProvider extends FabricDynamicRegistryProvider {

    protected final List<ResourceKey<? extends Registry<?>>> keys;

    public AtmosphericDynamicRegistriesProvider(FabricDataOutput output, Collection<ResourceKey<? extends Registry<?>>> keys, CompletableFuture<HolderLookup.Provider> registriesFuture) {
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
