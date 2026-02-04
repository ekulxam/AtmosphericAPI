/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.mixin.entity;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@SuppressWarnings("unused")
@Mixin(Entity.class)
public interface EntityAccessor {

    @Accessor("firstTick")
    boolean atmospheric_api$isFirstUpdate();

    @Accessor("firstTick")
    void atmospheric_api$setFirstUpdate(boolean firstUpdate);
}
