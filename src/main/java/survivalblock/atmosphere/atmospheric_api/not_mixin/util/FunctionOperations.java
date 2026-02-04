/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.util;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SpawnPlacements.SpawnPredicate;

import java.util.function.BiFunction;
import java.util.function.Predicate;

@SuppressWarnings("unused")
public final class FunctionOperations {
    private FunctionOperations() {
    }

    public static <T> Predicate<T> and(Predicate<? super T> one, Predicate<? super T> two) {
        return (obj) -> one.test(obj) && two.test(obj);
    }

    public static <T> Predicate<T> or(Predicate<? super T> one, Predicate<? super T> two) {
        return (obj) -> one.test(obj) || two.test(obj);
    }

    public static <T> Predicate<T> combine(Predicate<? super T> one, Predicate<? super T> two, BiFunction<Boolean, Boolean, Boolean> operator) {
        return (obj) -> operator.apply(one.test(obj), two.test(obj));
    }

    public static <T extends Entity> SpawnPredicate<T> and(SpawnPredicate<T> one, SpawnPredicate<T> two) {
        return (type, world, spawnReason, blockPos, random) ->
                one.test(type, world, spawnReason, blockPos, random) && two.test(type, world, spawnReason, blockPos, random);
    }

    public static <T extends Entity> SpawnPredicate<T> or(SpawnPredicate<T> one, SpawnPredicate<T> two) {
        return (type, world, spawnReason, blockPos, random) ->
                one.test(type, world, spawnReason, blockPos, random) || two.test(type, world, spawnReason, blockPos, random);
    }

    public static <T extends Entity> SpawnPredicate<T> combine(SpawnPredicate<T> one, SpawnPredicate<T> two, BiFunction<Boolean, Boolean, Boolean> operator) {
        return (type, world, spawnReason, blockPos, random) ->
                operator.apply(one.test(type, world, spawnReason, blockPos, random), two.test(type, world, spawnReason, blockPos, random));
    }
}
