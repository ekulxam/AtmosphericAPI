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

import java.util.Optional;

@Mixin(World.class)
public abstract class WorldMixin implements AtmosphericWorldRegistryShenanigans {

    @Shadow public abstract DynamicRegistryManager getRegistryManager();

    //? if =1.21.1 {
    
    @Override
    @Nullable
    public <T> RegistryEntry.Reference<T> atmospheric_api$getEntryFromKey(RegistryKey<? extends Registry<? extends T>> dynamicRegistryRegistryKey, RegistryKey<T> key) {
        return this.getRegistryManager().get(dynamicRegistryRegistryKey).getEntry(key).orElse(null);
    }

    @Override
    public <T> RegistryEntry.Reference<T> atmospheric_api$getEntryFromKeyOrThrow(RegistryKey<? extends Registry<? extends T>> dynamicRegistryRegistryKey, RegistryKey<T> key) {
        return this.getRegistryManager().get(dynamicRegistryRegistryKey).entryOf(key);
    }
     //?} elif =1.21.8 {

    /*@Override
    @Nullable
    public <T> RegistryEntry.Reference<T> atmospheric_api$getEntryFromKey(RegistryKey<? extends Registry<? extends T>> dynamicRegistryRegistryKey, RegistryKey<T> key) {
        Optional<Registry<T>> optional = this.getRegistryManager().getOptional(dynamicRegistryRegistryKey);
        return optional.map(registry -> registry.getOptional(key).orElse(null)).orElse(null);
    }

    @Override
    public <T> RegistryEntry.Reference<T> atmospheric_api$getEntryFromKeyOrThrow(RegistryKey<? extends Registry<? extends T>> dynamicRegistryRegistryKey, RegistryKey<T> key) {
        return this.getRegistryManager().getOrThrow(dynamicRegistryRegistryKey).getOrThrow(key);
    }
    *///?}
}
