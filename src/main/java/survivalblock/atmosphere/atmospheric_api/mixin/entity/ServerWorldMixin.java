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

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.AbortableIterationConsumer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.entity.LevelEntityGetter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import survivalblock.atmosphere.atmospheric_api.not_mixin.entity.injected_interface.AtmosphericServerWorldEntityCollector;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

@Mixin(ServerLevel.class)
public abstract class ServerWorldMixin implements AtmosphericServerWorldEntityCollector {

    @Shadow protected abstract LevelEntityGetter<Entity> getEntities();

    @Override
    public void atmospheric_api$getAndAddEntitiesToCollection(Predicate<Entity> predicate, Collection<Entity> entities) {
        AtomicInteger entityCount = new AtomicInteger(0);
        this.getEntities().get(EntityTypeTest.forClass(Entity.class), (entity) -> {
            if (entityCount.get() >= Integer.MAX_VALUE - 1) { // yes this is a conscious decision
                return AbortableIterationConsumer.Continuation.ABORT;
            }
            if (entity == null || !entity.isAlive()) {
                return AbortableIterationConsumer.Continuation.CONTINUE;
            }
            if (predicate.test(entity)) {
                entityCount.incrementAndGet();
                entities.add(entity);
            }
            return AbortableIterationConsumer.Continuation.CONTINUE;
        });
    }
}
