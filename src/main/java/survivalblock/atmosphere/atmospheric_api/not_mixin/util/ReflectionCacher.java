/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

public final class ReflectionCacher {

    public static final WeakHashMap<MethodDescription, Method> METHODS = new WeakHashMap<>();

    @SuppressWarnings("unused")
    public static final WeakHashMap<FieldDescription, Field> FIELDS = new WeakHashMap<>();

    private ReflectionCacher() {
    }

    public record MethodDescription(Class<?> clazz, String name, Class<?>[] parameterTypes) {

    }

    public record FieldDescription(Class<?> clazz, String name) {

    }
}
