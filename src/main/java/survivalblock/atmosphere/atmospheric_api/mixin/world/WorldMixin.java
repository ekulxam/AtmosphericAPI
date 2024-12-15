package survivalblock.atmosphere.atmospheric_api.mixin.world;

import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import survivalblock.atmosphere.atmospheric_api.not_mixin.world.injected_interface.AtmosphericWorldRegistryShenanigans;

@Mixin(World.class)
public abstract class WorldMixin implements AtmosphericWorldRegistryShenanigans {

    @Shadow public abstract DynamicRegistryManager getRegistryManager();

    @Override
    @Nullable
    public <T> RegistryEntry.Reference<T> atmospheric_api$getEntryFromKey(RegistryKey<? extends Registry<? extends T>> dynamicRegistryRegistryKey, RegistryKey<T> key) {
        return this.getRegistryManager().get(dynamicRegistryRegistryKey).getEntry(key).orElse(null);
    }

    @Override
    public <T> RegistryEntry.Reference<T> atmospheric_api$getEntryFromKeyOrThrow(RegistryKey<? extends Registry<? extends T>> dynamicRegistryRegistryKey, RegistryKey<T> key) {
        return this.getRegistryManager().get(dynamicRegistryRegistryKey).entryOf(key);
    }
}
