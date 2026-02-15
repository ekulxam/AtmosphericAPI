/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.util;

import org.jetbrains.annotations.Nullable;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.function.Function;

@SuppressWarnings("unused")
public final class ReflectionCacher {

    public static final WeakHashMap<MethodDescription, MethodHandle> METHOD_HANDLES = new WeakHashMap<>();
    public static final WeakHashMap<MethodDescription, Method> METHODS = new WeakHashMap<>();
    public static final WeakHashMap<FieldDescription, Field> FIELDS = new WeakHashMap<>();

    private ReflectionCacher() {
    }

    /**
     * Obtains the {@link Field} described by the given {@link FieldDescription} from {@link ReflectionCacher#FIELDS} or finds it if absent ({@link WeakHashMap#computeIfAbsent(Object, Function)})
     * @param fieldDescription the required metadata to obtain the {@link Field}
     * @return the field, or {@code null} if a {@link NoSuchFieldException} was thrown
     */
    @Nullable
    public static Field field(FieldDescription fieldDescription) {
        return FIELDS.computeIfAbsent(fieldDescription, desc -> {
            try {
                return desc.explicit
                        ? desc.clazz.getDeclaredField(desc.name)
                        : desc.clazz.getField(desc.name);
            } catch (NoSuchFieldException e) {
                AtmosphericAPI.LOGGER.error("Error while using reflection to get the field {}.{}", desc.clazz.getName(), desc.name, e);
                return null;
            }
        });
    }

    /**
     * Obtains the {@link Method} described by the given {@link MethodDescription} from {@link ReflectionCacher#METHODS} or finds it if absent ({@link WeakHashMap#computeIfAbsent(Object, Function)})
     * @param methodDescription the required metadata to obtain the {@link Method}
     * @return the field, or {@code null} if a {@link NoSuchMethodException} was thrown
     */
    @Nullable
    public static Method method(MethodDescription methodDescription) {
        return METHODS.computeIfAbsent(methodDescription, desc -> {
            try {
                return desc.clazz.getMethod(desc.name, desc.parameterTypes);
            } catch (NoSuchMethodException e) {
                AtmosphericAPI.LOGGER.error("Error while using reflection to get the method {}.{}", desc.clazz.getName(), desc.name, e);
                return null;
            }
        });
    }

    /**
     * Obtains the {@link MethodHandle} associated with the given {@link MethodDescription} from {@link ReflectionCacher#METHOD_HANDLES} or finds it if absent ({@link WeakHashMap#computeIfAbsent(Object, Function)})
     * @param methodDescription the required metadata to obtain the {@link MethodHandle}
     * @return the method, or {@code null} if a {@link NoSuchMethodException} or {@link IllegalAccessException} was thrown
     */
    @Nullable
    public static MethodHandle methodHandle(MethodDescription methodDescription) {
        return methodHandle(methodDescription, MethodHandles.lookup());
    }

    /**
     * Obtains the {@link MethodHandle} associated with the given {@link MethodDescription} from {@link ReflectionCacher#METHOD_HANDLES} or finds it if absent ({@link WeakHashMap#computeIfAbsent(Object, Function)})
     * @param methodDescription the required metadata to obtain the {@link MethodHandle}
     * @param lookup the lookup used to find the {@link MethodHandle} if absent
     * @return the method, or {@code null} if a {@link NoSuchMethodException} or {@link IllegalAccessException} was thrown
     */
    @Nullable
    public static MethodHandle methodHandle(MethodDescription methodDescription, MethodHandles.Lookup lookup) {
        return METHOD_HANDLES.computeIfAbsent(methodDescription, desc -> {
            try {
                return lookup.findVirtual(
                        desc.clazz,
                        desc.name,
                        MethodType.methodType(Objects.requireNonNull(desc.returnType), desc.parameterTypes)
                );
            } catch (NoSuchMethodException | IllegalAccessException e) {
                AtmosphericAPI.LOGGER.error("Error while using MethodHandles to get {}.{}", desc.clazz.getName(), desc.name, e);
                return null;
            }
        });
    }

    /**
     * A record that stores some of the metadata of a {@link Method}
     * @param clazz the owning class of the method
     * @param name the method's name
     * @param returnType the return type of the method. Can be {@code null} if not using {@link MethodHandles}
     * @param parameterTypes the types of the parameters of the method, in sequential order
     */
    public record MethodDescription(Class<?> clazz, String name, @Nullable Class<?> returnType, Class<?>[] parameterTypes) {
        public MethodDescription(Class<?> clazz, String name, Class<?>[] parameterTypes) {
            this(clazz, name, null, parameterTypes);
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || this.getClass() != o.getClass()) return false;
            MethodDescription that = (MethodDescription) o;
            return Objects.equals(this.name, that.name)
                    && Objects.equals(this.clazz, that.clazz)
                    && Objects.equals(this.returnType, that.returnType)
                    && Objects.deepEquals(this.parameterTypes, that.parameterTypes);
        }

        @Override
        public int hashCode() {
            return Objects.hash(
                    this.clazz,
                    this.name,
                    this.returnType, // I think this is fine
                    Arrays.hashCode(this.parameterTypes)
            );
        }
    }

    /**
     * A record that stores some of the metadata of a {@link Field}
     * @param clazz the owning class of the field
     * @param name the field's name
     * @param explicit set this to true to obtain the field by {@link Class#getDeclaredField(String)} rather than {@link Class#getField(String)}
     */
    public record FieldDescription(Class<?> clazz, String name, boolean explicit) {
        @Override
        public boolean equals(Object o) {
            if (o == null || this.getClass() != o.getClass()) return false;
            FieldDescription that = (FieldDescription) o;
            return this.explicit == that.explicit
                    && Objects.equals(this.name, that.name)
                    && Objects.equals(this.clazz, that.clazz);
        }

        @Override
        public int hashCode() {
            return Objects.hash(
                    this.clazz,
                    this.name,
                    this.explicit
            );
        }
    }
}
