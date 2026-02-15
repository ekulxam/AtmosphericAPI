/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.block;

import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;
import survivalblock.atmosphere.atmospheric_api.not_mixin.funny.DangerousAndOrUnstable;

/**
 * Instances of {@link net.minecraft.world.level.block.Block} that implement this interface will not
 * have their {@link net.minecraft.core.Holder.Reference} created, and as a
 * result, will not crash when not registered. This interface is useful for when an instance of
 * {@link net.minecraft.world.level.block.Block} is needed without actually creating the block in-game.
 * <p>
 * This interface is annotated with {@link DangerousAndOrUnstable} because of the potential danger, instability,
 * and incompatibility that may arise.
 */
@DangerousAndOrUnstable
public interface NonRegisterableBlock {

    @Nullable
    default <T extends Block> Holder.Reference<T> getAlternateNullableReference() {
        return null;
    }
}
