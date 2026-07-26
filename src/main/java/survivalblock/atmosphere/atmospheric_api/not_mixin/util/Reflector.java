/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.util;

import org.jetbrains.annotations.Nullable;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI.LOGGER;

@SuppressWarnings("unused")
public final class Reflector {
    private static final int MAX_FIELD_SEARCH_DEPTH = 100;

    private Reflector() {
    }

    /**
     * Obtains the {@link Field} described by the given {@link FieldDescription}
     * @param desc the required metadata to obtain the {@link Field}
     * @return the field, or {@code null} if a {@link NoSuchFieldException} was thrown
     */
    @Nullable
    public static Field field(FieldDescription desc) {
        try {
            return desc.explicit
                    ? desc.clazz.getDeclaredField(desc.name)
                    : desc.clazz.getField(desc.name);
        } catch (NoSuchFieldException e) {
            LOGGER.error("Error while using reflection to get the field {}.{}", desc.clazz.getName(), desc.name, e);
            return null;
        }
    }

    @Nullable
    public static Field fieldRecursive(FieldDescription desc) {
        if (!desc.explicit) {
            throw new IllegalStateException("FieldDescription " + desc + " (passed as parameter to fieldRecursive) must be marked as explicit!");
        }
        String name = desc.name;
        Class<?> maybeSuper = desc.clazz;
        List<String> searchedThrough = new ArrayList<>();
        while (maybeSuper != null) {
            try {
                return maybeSuper.getDeclaredField(name);
            } catch (NoSuchFieldException e) {
                searchedThrough.add(maybeSuper.getName());
                if (searchedThrough.size() > MAX_FIELD_SEARCH_DEPTH) {
                    LOGGER.error("Error while using reflection to get the field {}.{} (exceeded the maximum depth of {} while searching through (super)classes: {})", desc.clazz.getName(), desc.name, MAX_FIELD_SEARCH_DEPTH, searchedThrough);
                    return null;
                }
                maybeSuper = maybeSuper.getSuperclass();
            }
        }
        //noinspection DataFlowIssue
        LOGGER.error("Error while using reflection to get the field {}.{} (searched through the following (super)classes and found nothing: {})", desc.clazz.getName(), desc.name, searchedThrough);
        return null;
    }

    @Nullable
    private static Field fieldRecursive(FieldDescription desc, String original, int depth, String... searchedThrough) {
        try {
            return desc.clazz.getDeclaredField(desc.name);
        } catch (NoSuchFieldException e) {
            if (depth - 1 >= MAX_FIELD_SEARCH_DEPTH) {
                return null;
            }

            if (searchedThrough[0] == null) {
                searchedThrough[0] = original;
            }
            searchedThrough[depth] = desc.clazz.getName();
            depth++;

            Class<?> other = desc.clazz.getSuperclass();
            if (other == null) {
                return null;
            }

            return fieldRecursive(desc.copyWithClass(other), original, depth, searchedThrough);
        }
    }

    /**
     * Obtains the {@link Method} described by the given {@link MethodDescription}
     * @param desc the required metadata to obtain the {@link Method}
     * @return the field, or {@code null} if a {@link NoSuchMethodException} was thrown
     */
    @Nullable
    public static Method method(MethodDescription desc) {
        try {
            return desc.clazz.getMethod(desc.name, desc.parameterTypes);
        } catch (NoSuchMethodException e) {
            LOGGER.error("Error while using reflection to get the method {}.{}", desc.clazz.getName(), desc.name, e);
            return null;
        }
    }

    /**
     * Obtains the {@link MethodHandle} associated with the given {@link MethodDescription}
     * @param methodDescription the required metadata to obtain the {@link MethodHandle}
     * @return the method, or {@code null} if a {@link NoSuchMethodException} or {@link IllegalAccessException} was thrown
     */
    @Nullable
    public static MethodHandle methodHandle(MethodDescription methodDescription) {
        return methodHandle(methodDescription, MethodHandles.lookup());
    }

    /**
     * Obtains the {@link MethodHandle} associated with the given {@link MethodDescription}
     * @param desc the required metadata to obtain the {@link MethodHandle}
     * @param lookup the lookup used to find the {@link MethodHandle} if absent
     * @return the method, or {@code null} if a {@link NoSuchMethodException} or {@link IllegalAccessException} was thrown
     */
    @Nullable
    public static MethodHandle methodHandle(MethodDescription desc, MethodHandles.Lookup lookup) {
        try {
            return lookup.findVirtual(
                    desc.clazz,
                    desc.name,
                    MethodType.methodType(Objects.requireNonNull(desc.returnType), desc.parameterTypes)
            );
        } catch (NoSuchMethodException | IllegalAccessException e) {
            LOGGER.error("Error while using MethodHandles to get {}.{}", desc.clazz.getName(), desc.name, e);
            return null;
        }
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

        @Override
        public String toString() {
            return this.getClass().getName()
                    + "{reference='" + this.clazz.getName() + "." + this.name + "'"
                    + ", returnType=" + (this.returnType == null ? "null" : this.returnType.getName())
                    + ", parameterTypes=" + Arrays.stream(this.parameterTypes)
                        .map(Class::getName)
                        .collect(Collectors.joining(", "))
                    + '}';
        }
    }

    /**
     * A record that stores some of the metadata of a {@link Field}
     * @param clazz the owning class of the field
     * @param name the field's name
     * @param explicit set this to true to obtain the field by {@link Class#getDeclaredField(String)} rather than {@link Class#getField(String)}
     */
    public record FieldDescription(Class<?> clazz, String name, boolean explicit) {
        public FieldDescription(Class<?> clazz, String name) {
            this(clazz, name, false);
        }

        public FieldDescription copyWithClass(Class<?> other) {
            return new FieldDescription(other, this.name, this.explicit);
        }

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

        @Override
        public String toString() {
            return this.getClass().getName()
                    + "{reference='" + this.clazz.getName() + "." + this.name + "'"
                    + ", explicit=" + this.explicit
                    + '}';
        }
    }
}
