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
