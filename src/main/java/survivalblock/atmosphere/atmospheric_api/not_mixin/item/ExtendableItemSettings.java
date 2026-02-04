/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.item;

import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.Item.Properties;

@SuppressWarnings("unused")
public abstract class ExtendableItemSettings<S extends ExtendableItemSettings<S>> extends Properties {

    @Override
    public S requiredFeatures(FeatureFlag... requiredFeatures) {
        return apply((SettingsVariadicOperation<FeatureFlag>) super::requiredFeatures, (Object[]) requiredFeatures);
    }

    @SuppressWarnings("unchecked")
    public S apply(SettingsNullaryOperation operation) {
        return (S) operation.invoke();
    }

    @SuppressWarnings("unchecked")
    public <T> S apply(SettingsUnaryOperation<T> operation, T first) {
        return (S) operation.invoke(first);
    }

    @SuppressWarnings("unchecked")
    public <T, U> S apply(SettingsBinaryOperation<T, U> operation, T first, U second) {
        return (S) operation.invoke(first, second);
    }

    @SuppressWarnings("unchecked")
    public <T, U, R> S apply(SettingsTernaryOperation<T, U, R> operation, T first, U second, R third) {
        return (S) operation.invoke(first, second, third);
    }

    @SuppressWarnings("unchecked")
    public S apply(SettingsVariadicOperation operation, Object... args) {
        return (S) operation.invoke(args);
    }

    @FunctionalInterface
    public interface SettingsNullaryOperation {
        Properties invoke();
    }

    @FunctionalInterface
    public interface SettingsUnaryOperation<T> {
        Properties invoke(T first);
    }

    @FunctionalInterface
    public interface SettingsBinaryOperation<T, U> {
        Properties invoke(T first, U second);
    }

    @FunctionalInterface
    public interface SettingsTernaryOperation<T, U, R> {
        Properties invoke(T first, U second, R third);
    }

    @FunctionalInterface
    public interface SettingsQuaternaryOperation<T, U, R, V> {
        Properties invoke(T first, U second, R third);
    }

    @FunctionalInterface
    public interface SettingsVariadicOperation<T> {
        @SuppressWarnings("unchecked")
        Properties invoke(T... args);
    }
}
