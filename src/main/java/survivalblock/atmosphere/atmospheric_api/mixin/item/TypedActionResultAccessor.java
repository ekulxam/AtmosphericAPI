/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
//? if =1.21.1 {
/*package survivalblock.atmosphere.atmospheric_api.mixin.item;

import net.minecraft.world.InteractionResultHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@SuppressWarnings({"unused", "UnusedMixin"})
@Mixin(InteractionResultHolder.class)
public interface TypedActionResultAccessor<T> {

    @Mutable
    @Accessor("object")
    void atmospheric_api$setValue(T value);
}
*///?}