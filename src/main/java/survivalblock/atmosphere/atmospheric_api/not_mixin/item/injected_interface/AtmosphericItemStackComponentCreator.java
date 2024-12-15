package survivalblock.atmosphere.atmospheric_api.not_mixin.item.injected_interface;

import net.minecraft.component.ComponentType;

@SuppressWarnings("unused")
public interface AtmosphericItemStackComponentCreator {

    default <T> T atmospheric_api$getOrCreate(ComponentType<T> type, T fallback) {
        return fallback;
    }
}
