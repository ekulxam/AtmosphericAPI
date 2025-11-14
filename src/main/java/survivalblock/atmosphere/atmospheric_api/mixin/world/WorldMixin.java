package survivalblock.atmosphere.atmospheric_api.mixin.world;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import survivalblock.atmosphere.atmospheric_api.not_mixin.world.injected_interface.AtmosphericWorldRegistryShenanigans;

import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

@Mixin(Level.class)
public abstract class WorldMixin implements AtmosphericWorldRegistryShenanigans {

    @Shadow public abstract RegistryAccess registryAccess();

    //? if =1.21.1 {
    
    @Override
    @Nullable
    public <T> Holder.Reference<T> atmospheric_api$getEntryFromKey(ResourceKey<? extends Registry<? extends T>> dynamicRegistryRegistryKey, ResourceKey<T> key) {
        return this.registryAccess().registryOrThrow(dynamicRegistryRegistryKey).getHolder(key).orElse(null);
    }

    @Override
    public <T> Holder.Reference<T> atmospheric_api$getEntryFromKeyOrThrow(ResourceKey<? extends Registry<? extends T>> dynamicRegistryRegistryKey, ResourceKey<T> key) {
        return this.registryAccess().registryOrThrow(dynamicRegistryRegistryKey).getHolderOrThrow(key);
    }
     //?} elif =1.21.8 {

    /*@Override
    @Nullable
    public <T> RegistryEntry.Reference<T> atmospheric_api$getEntryFromKey(RegistryKey<? extends Registry<? extends T>> dynamicRegistryRegistryKey, RegistryKey<T> key) {
        Optional<Registry<T>> optional = this.getRegistryManager().getOptional(dynamicRegistryRegistryKey);
        return optional.flatMap(registry -> registry.getOptional(key)).orElse(null);
    }

    @Override
    public <T> RegistryEntry.Reference<T> atmospheric_api$getEntryFromKeyOrThrow(RegistryKey<? extends Registry<? extends T>> dynamicRegistryRegistryKey, RegistryKey<T> key) {
        return this.getRegistryManager().getOrThrow(dynamicRegistryRegistryKey).getOrThrow(key);
    }
    *///?}
}
