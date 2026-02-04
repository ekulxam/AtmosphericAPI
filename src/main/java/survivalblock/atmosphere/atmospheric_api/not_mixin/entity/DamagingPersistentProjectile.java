/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.entity;

import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;

/**
 * A small utility interface for instances of {@link net.minecraft.world.entity.projectile.AbstractArrow}
 * that want more control over {@link net.minecraft.world.entity.projectile.AbstractArrow#hurt(DamageSource, float)}
 * without explicitly overriding the method
 */
public interface DamagingPersistentProjectile {

    Holder.Reference<DamageType> getDamageType();

    default boolean shouldIncreaseStuckArrowCount() {
        return false;
    }
}
