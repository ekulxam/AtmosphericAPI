package survivalblock.atmosphere.atmospheric_api.not_mixin.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

public class ReflectionCacher {

    public static final WeakHashMap<MethodDescription, Method> METHODS = new WeakHashMap<>();
    @SuppressWarnings("unused")
    public static final WeakHashMap<FieldDescription, Field> FIELDS = new WeakHashMap<>();

    public record MethodDescription(Class<?> clazz, String name, Class<?>[] parameterTypes) {

    }

    public record FieldDescription(Class<?> clazz, String name) {

    }
}
