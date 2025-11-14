package survivalblock.atmosphere.atmospheric_api.not_mixin.entity.injected_interface;

import java.util.Collection;
import java.util.function.Predicate;
import net.minecraft.world.entity.Entity;

@SuppressWarnings("unused")
public interface AtmosphericServerWorldEntityCollector {

    default void atmospheric_api$getAndAddEntitiesToCollection(Predicate<Entity> predicate, Collection<Entity> entities) {

    }
}
