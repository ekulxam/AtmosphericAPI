package survivalblock.atmosphere.atmospheric_api.mixin.entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import survivalblock.atmosphere.atmospheric_api.not_mixin.entity.injected_interface.AtmosphericServerWorldEntityCollector;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.AbortableIterationConsumer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.entity.LevelEntityGetter;

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
