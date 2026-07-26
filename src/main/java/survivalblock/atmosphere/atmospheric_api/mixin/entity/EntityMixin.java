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
package survivalblock.atmosphere.atmospheric_api.mixin.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import survivalblock.atmosphere.atmospheric_api.not_mixin.entity.injected_interface.AtmosphericEntityDaylightGetter;

@Mixin(Entity.class)
public abstract class EntityMixin implements AtmosphericEntityDaylightGetter {

    @Shadow public abstract Level level();

    @Shadow public abstract float getLightLevelDependentMagicValue();

    @Shadow public abstract double getX();

    @Shadow public abstract double getEyeY();

    @Shadow public abstract double getZ();

    @Override
    public boolean atmospheric_api$isAffectedByDaylight() {
        Level world = this.level();
        if (world./*? =1.21.1 {*/ /*isDay *//*?} else {*/ isBrightOutside /*?}*/()) { // lol isBrightOutside
            float f = this.getLightLevelDependentMagicValue(); // lol magicValue
            BlockPos blockPos = BlockPos.containing(this.getX(), this.getEyeY(), this.getZ());
            return f > 0.5F && world.canSeeSky(blockPos);
        }
        return false;
    }
}
