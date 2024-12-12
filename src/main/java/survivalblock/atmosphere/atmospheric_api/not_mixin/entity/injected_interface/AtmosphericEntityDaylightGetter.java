package survivalblock.atmosphere.atmospheric_api.not_mixin.entity.injected_interface;

@SuppressWarnings("unused")
public interface AtmosphericEntityDaylightGetter {

    default boolean atmospheric_api$isAffectedByDaylight() {
        return false;
    }
}
