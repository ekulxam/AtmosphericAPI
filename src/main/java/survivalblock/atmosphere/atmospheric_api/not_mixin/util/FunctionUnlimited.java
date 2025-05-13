package survivalblock.atmosphere.atmospheric_api.not_mixin.util;

/**
 * @see com.llamalad7.mixinextras.injector.wrapoperation.Operation
 * @param <R> the return type
 */
@FunctionalInterface
public interface FunctionUnlimited<R> {

    R apply(Object[] args);
}
