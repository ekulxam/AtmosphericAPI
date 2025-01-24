package survivalblock.atmosphere.atmospheric_api.not_mixin.block;

import net.minecraft.block.Block;
import net.minecraft.registry.entry.RegistryEntry;
import org.jetbrains.annotations.Nullable;

/**
 * Instances of {@link net.minecraft.block.Block} that implement this interface will not
 * have their {@link net.minecraft.registry.entry.RegistryEntry.Reference} created, and as a
 * result, will not crash when not registered. This interface is useful for when an instance of
 * {@link net.minecraft.block.Block} is needed without actually creating the block in-game.<p>
 * This interface is annotated with {@link Deprecated} because of the potential danger, instability,
 * and incompatibility that may arise.
 */
@Deprecated
public interface NonRegisterableBlock {

    @Nullable
    default <T extends Block> RegistryEntry.Reference<T> getAlternateNullableReference() {
        return null;
    }
}
