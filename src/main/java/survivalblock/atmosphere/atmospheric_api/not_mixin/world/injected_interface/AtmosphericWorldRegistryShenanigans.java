/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
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
