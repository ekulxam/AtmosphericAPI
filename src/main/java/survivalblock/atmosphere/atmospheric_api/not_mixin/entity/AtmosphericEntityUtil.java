package survivalblock.atmosphere.atmospheric_api.not_mixin.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.function.LazyIterationConsumer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import survivalblock.atmosphere.atmospheric_api.mixin.util.ServerWorldAccessor;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

@SuppressWarnings("unused")
public final class AtmosphericEntityUtil {

    public static boolean isAffectedByDaylight(Entity entity) {
        if (entity == null) {
            return false;
        }
        World world = entity.getWorld();
        if (world.isDay()) {
            @SuppressWarnings("deprecation") float f = entity.getBrightnessAtEyes();
            BlockPos blockPos = BlockPos.ofFloored(entity.getX(), entity.getEyeY(), entity.getZ());
            return f > 0.5F && world.isSkyVisible(blockPos);
        }
        return false;
    }

    public static void getAndAddEntitiesToCollection(ServerWorld serverWorld, Predicate<Entity> predicate, Collection<Entity> entities) {
        AtomicInteger entityCount = new AtomicInteger(0);
        ((ServerWorldAccessor) serverWorld).atmospheric_api$invokeGetEntityLookup().forEach(TypeFilter.instanceOf(Entity.class), (entity) -> {
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
