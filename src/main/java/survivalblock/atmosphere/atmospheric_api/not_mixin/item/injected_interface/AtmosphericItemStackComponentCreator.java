package survivalblock.atmosphere.atmospheric_api.not_mixin.item.injected_interface;

import net.minecraft.core.component.DataComponentType;

@SuppressWarnings("unused")
public interface AtmosphericItemStackComponentCreator {

    default <T> T atmospheric_api$getOrCreate(DataComponentType<T> type, T fallback) {
        return fallback;
    }
}
