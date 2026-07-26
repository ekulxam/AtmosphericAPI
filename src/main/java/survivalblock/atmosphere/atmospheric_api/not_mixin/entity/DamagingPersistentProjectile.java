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
