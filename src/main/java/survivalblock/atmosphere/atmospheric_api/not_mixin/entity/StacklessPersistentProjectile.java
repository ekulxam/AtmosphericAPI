/*
 * Copyright (c) 21:56 UTC 26 July 2026 - present ekulxam
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.entity;

//~ if >=1.21.11 'projectile.AbstractArrow' -> 'projectile.arrow.AbstractArrow' {
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;

/**
 * A {@link net.minecraft.world.entity.projectile.arrow.AbstractArrow} that can/should have null or
 * {@link net.minecraft.world.item.ItemStack#EMPTY} as the return value of {@link AbstractArrow#getPickupItemStackOrigin()}
 * @see survivalblock.atmosphere.atmospheric_api.mixin.entity.PersistentProjectileEntityMixin
 */
//~}
public interface StacklessPersistentProjectile {

    default boolean shouldAvoidEncodingStack() {
        ItemStack stack = ((AbstractArrow) this).getPickupItemStackOrigin();
        return stack == null || stack.isEmpty();
    }
}
