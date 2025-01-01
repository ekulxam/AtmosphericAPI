package survivalblock.atmosphere.atmospheric_api.not_mixin.datagen;

import net.minecraft.registry.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import survivalblock.atmosphere.atmospheric_api.not_mixin.funny.IsThisEvenNecessary;

@IsThisEvenNecessary(IsThisEvenNecessary.LevelsOfUnnecessity.PROBABLY_NOT)
@SuppressWarnings("unused")
public class RegistryEntryLookupContainer {

    @Nullable
    private Registerable<?> registerable = null;

    @Nullable
    private RegistryWrapper.WrapperLookup wrapperLookup = null;

    public RegistryEntryLookupContainer(@NotNull Registerable<?> registerable) {
        this.registerable = registerable;
    }

    public RegistryEntryLookupContainer(@NotNull RegistryWrapper.WrapperLookup wrapperLookup) {
        this.wrapperLookup = wrapperLookup;
    }

    public <T> RegistryEntryLookup<T> get(RegistryKey<? extends Registry<? extends T>> registryKey) {
        if (this.registerable != null) {
            return registerable.getRegistryLookup(registryKey);
        }
        if (this.wrapperLookup != null) {
            return wrapperLookup.getWrapperOrThrow(registryKey);
        }
        throw new IllegalStateException("A RegistryEntryLookupContainer cannot have both its fields be null!");
    }
}