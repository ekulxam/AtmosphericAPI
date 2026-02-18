/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.util;

/**
 * @see com.llamalad7.mixinextras.injector.wrapoperation.Operation
 * @param <R> the return type of the function
 */
@FunctionalInterface
public interface FunctionUnlimited<R> {
    R apply(Object[] args);
}
