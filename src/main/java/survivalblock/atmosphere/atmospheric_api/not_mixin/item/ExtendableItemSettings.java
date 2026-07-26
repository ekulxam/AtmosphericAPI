/*
 * Copyright (c) 21:56 UTC 26 July 2026 - present ekulxam
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
