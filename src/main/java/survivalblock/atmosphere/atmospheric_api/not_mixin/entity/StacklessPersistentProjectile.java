/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.entity;

import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;

/**
 * A {@link net.minecraft.world.entity.projectile.AbstractArrow} that can/should have null or
 * {@link net.minecraft.world.item.ItemStack#EMPTY} as the return value of {@link AbstractArrow#getPickupItemStackOrigin()}
 * @see survivalblock.atmosphere.atmospheric_api.mixin.entity.PersistentProjectileEntityMixin
 */
public interface StacklessPersistentProjectile {

    default boolean shouldAvoidEncodingStack() {
        ItemStack stack = ((AbstractArrow) this).getPickupItemStackOrigin();
        return stack == null || stack.isEmpty();
    }
}
