package survivalblock.atmosphere.atmospheric_api.not_mixin.entity.injected_interface;

import net.minecraft.entity.Entity;

import java.util.Collection;
import java.util.function.Predicate;

@SuppressWarnings("unused")
public interface AtmosphericServerWorldEntityCollector {

    default void atmospheric_api$getAndAddEntitiesToCollection(Predicate<Entity> predicate, Collection<Entity> entities) {

    }
}
