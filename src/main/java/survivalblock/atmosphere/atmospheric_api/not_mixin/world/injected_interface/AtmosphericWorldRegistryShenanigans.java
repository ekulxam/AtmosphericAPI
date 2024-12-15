package survivalblock.atmosphere.atmospheric_api.not_mixin.world.injected_interface;

import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
public interface AtmosphericWorldRegistryShenanigans {

    @Nullable
    default <T> RegistryEntry.Reference<T> atmospheric_api$getEntryFromKey(RegistryKey<? extends Registry<? extends T>> dynamicRegistryRegistryKey, RegistryKey<T> key) {
        return null;
    }

    default <T> RegistryEntry.Reference<T> atmospheric_api$getEntryFromKeyOrThrow(RegistryKey<? extends Registry<? extends T>> dynamicRegistryRegistryKey, RegistryKey<T> key) {
        throw new UnsupportedOperationException(this.getClass() + " is an Injected Interface!");
    }
}
