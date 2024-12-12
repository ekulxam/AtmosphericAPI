package survivalblock.atmosphere.atmospheric_api.mixin.entity;

import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.function.LazyIterationConsumer;
import net.minecraft.world.entity.EntityLookup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import survivalblock.atmosphere.atmospheric_api.not_mixin.entity.injected_interface.AtmosphericServerWorldEntityCollector;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin implements AtmosphericServerWorldEntityCollector {

    @Shadow protected abstract EntityLookup<Entity> getEntityLookup();

    @Override
    public void atmospheric_api$getAndAddEntitiesToCollection(Predicate<Entity> predicate, Collection<Entity> entities) {
        AtomicInteger entityCount = new AtomicInteger(0);
        this.getEntityLookup().forEach(TypeFilter.instanceOf(Entity.class), (entity) -> {
            if (entityCount.get() >= Integer.MAX_VALUE - 1) { // yes this is a conscious decision
                return LazyIterationConsumer.NextIteration.ABORT;
            }
            if (entity == null || !entity.isAlive()) {
                return LazyIterationConsumer.NextIteration.CONTINUE;
            }
            if (predicate.test(entity)) {
                entityCount.incrementAndGet();
                entities.add(entity);
            }
            return LazyIterationConsumer.NextIteration.CONTINUE;
        });
    }
}
