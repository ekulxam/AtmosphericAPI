/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.registrant.dynamic;

import net.fabricmc.fabric.impl.datagen.FabricDataGenHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import survivalblock.atmosphere.atmospheric_api.not_mixin.registrant.Registrant;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * A special {@link Registrant} for dynamic registries.
 * {@link DynamicRegistrant#bootstrap(BootstrapContext)} can be used as a {@link RegistrySetBuilder.RegistryBootstrap}
 * @see net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint#buildRegistry(RegistrySetBuilder) 
 */
public class DynamicRegistrant<T> {
    protected final Function<String, ResourceLocation> idFunction;
    protected final ResourceKey<? extends Registry<T>> registry;

    protected final Map<ResourceKey<T>, Function<BootstrapContext<T>, T>> toRegister = new HashMap<>();

    public DynamicRegistrant(String modId, ResourceKey<? extends Registry<T>> registry) {
        this(path -> ResourceLocation.fromNamespaceAndPath(modId, path), registry);
    }

    public DynamicRegistrant(Function<String, ResourceLocation> idFunction, ResourceKey<? extends Registry<T>> registry) {
        this.idFunction = idFunction;
        this.registry = registry;
    }

    public ResourceKey<T> register(String path) {
        return ResourceKey.create(this.registry, this.idFunction.apply(path));
    }

    public ResourceKey<T> register(String path, T obj) {
        return this.register(path, registerable -> obj);
    }

    protected ResourceKey<T> register(String path, Function<BootstrapContext<T>, T> objCreator) {
        ResourceKey<T> key = this.register(path);
        this.maybeAdd(key, objCreator);
        return key;
    }

    protected void maybeAdd(ResourceKey<T> key, Function<BootstrapContext<T>, T> objCreator) {
        if (this.runningDatagen()) {
            this.toRegister.put(key, objCreator);
        }
    }

    @SuppressWarnings("UnstableApiUsage")
    protected boolean runningDatagen() {
        return FabricDataGenHelper.ENABLED;
    }

    @SuppressWarnings("unused")
    public void bootstrap(BootstrapContext<T> registerable) {
        for (Map.Entry<ResourceKey<T>, Function<BootstrapContext<T>, T>> entry : this.toRegister.entrySet()) {
            registerable.register(entry.getKey(), entry.getValue().apply(registerable));
        }
        this.toRegister.clear();
    }
}
