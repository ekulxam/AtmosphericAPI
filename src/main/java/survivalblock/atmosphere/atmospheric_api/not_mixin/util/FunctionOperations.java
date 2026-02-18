/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.util;

import com.google.common.base.Preconditions;
import net.minecraft.util.VisibleForDebug;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SpawnPlacements.SpawnPredicate;
import org.apache.commons.lang3.function.Consumers;
import survivalblock.atmosphere.atmospheric_api.not_mixin.funny.IsThisEvenNecessary;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @see org.apache.commons.lang3.function.Consumers
 * @see com.google.common.base.Predicates
 * @see com.google.common.base.Suppliers
 * @see java.util.Objects
 */
@SuppressWarnings("unused")
public final class FunctionOperations {
    private static final Supplier NULL_SUPPLIER = lazySupplier(null);
    private static final Function IDENTITY_FUNCTION = Function.identity();

    private FunctionOperations() {
    }

    @IsThisEvenNecessary(IsThisEvenNecessary.Levels.PROBABLY_NOT)
    public static <T> Consumer<T> voidConsumer() {
        return Consumers.nop();
    }

    @IsThisEvenNecessary(IsThisEvenNecessary.Levels.PROBABLY_NOT)
    @SuppressWarnings("unchecked")
    public static <T> Function<T, T> identityFunction() {
        return IDENTITY_FUNCTION;
    }

    @IsThisEvenNecessary(IsThisEvenNecessary.Levels.PROBABLY_NOT)
    @SuppressWarnings("unchecked")
    public static <T> Supplier<T> nullSupplier() {
        return NULL_SUPPLIER;
    }

    @IsThisEvenNecessary
    public static <T> Supplier<T> lazySupplier(T obj) {
        return () -> obj;
    }

    /**
     * @see Predicate#and(Predicate)
     */
    public static <T> Predicate<T> and(Predicate<? super T> one, Predicate<? super T> two) {
        Preconditions.checkNotNull(one, "The first predicate was null!");
        Preconditions.checkNotNull(two, "The second predicate was null!");
        return (obj) -> one.test(obj) && two.test(obj);
    }

    /**
     * @see com.google.common.base.Predicates#and(com.google.common.base.Predicate...)
     */
    @SafeVarargs // probably
    public static <T> Predicate<T> and(Predicate<? super T>... predicates) {
        ArrayList<Predicate<? super T>> predicateList = new ArrayList<>();
        for (int i = 0; i < predicates.length; i++) {
            Predicate<? super T> predicate = predicates[i];
            predicateList.add(Preconditions.checkNotNull(predicate, "Predicate at index " + i + "was null!"));
        }
        return new AndPredicate<>(predicateList);
    }

    /**
     * @see Predicate#or(Predicate)
     */
    public static <T> Predicate<T> or(Predicate<? super T> one, Predicate<? super T> two) {
        Preconditions.checkNotNull(one, "The first predicate was null!");
        Preconditions.checkNotNull(two, "The second predicate was null!");
        return (obj) -> one.test(obj) || two.test(obj);
    }

    /**
     * @see com.google.common.base.Predicates#or(com.google.common.base.Predicate...)
     */
    @SafeVarargs // probably
    public static <T> Predicate<T> or(Predicate<? super T>... predicates) {
        ArrayList<Predicate<? super T>> predicateList = new ArrayList<>();
        for (int i = 0; i < predicates.length; i++) {
            Predicate<? super T> predicate = predicates[i];
            predicateList.add(Preconditions.checkNotNull(predicate, "Predicate at index " + i + "was null!"));
        }
        return new OrPredicate<>(predicateList);
    }

    public static <T> Predicate<T> combine(Predicate<? super T> one, Predicate<? super T> two, BiFunction<Boolean, Boolean, Boolean> operator) {
        Preconditions.checkNotNull(one, "The first predicate was null!");
        Preconditions.checkNotNull(two, "The second predicate was null!");
        return (obj) -> operator.apply(one.test(obj), two.test(obj));
    }

    public static <T extends Entity> SpawnPredicate<T> and(SpawnPredicate<T> one, SpawnPredicate<T> two) {
        Preconditions.checkNotNull(one, "The first predicate was null!");
        Preconditions.checkNotNull(two, "The second predicate was null!");
        return (type, world, spawnReason, blockPos, random) ->
                one.test(type, world, spawnReason, blockPos, random) && two.test(type, world, spawnReason, blockPos, random);
    }

    public static <T extends Entity> SpawnPredicate<T> or(SpawnPredicate<T> one, SpawnPredicate<T> two) {
        Preconditions.checkNotNull(one, "The first predicate was null!");
        Preconditions.checkNotNull(two, "The second predicate was null!");
        return (type, world, spawnReason, blockPos, random) ->
                one.test(type, world, spawnReason, blockPos, random) || two.test(type, world, spawnReason, blockPos, random);
    }

    public static <T extends Entity> SpawnPredicate<T> combine(SpawnPredicate<T> one, SpawnPredicate<T> two, BiFunction<Boolean, Boolean, Boolean> operator) {
        Preconditions.checkNotNull(one, "The first predicate was null!");
        Preconditions.checkNotNull(two, "The second predicate was null!");
        return (type, world, spawnReason, blockPos, random) ->
                operator.apply(one.test(type, world, spawnReason, blockPos, random), two.test(type, world, spawnReason, blockPos, random));
    }

    public static <T> T consumeAndReturn(T obj, Consumer<T> consumer) {
        consumer.accept(obj);
        return obj;
    }

    @VisibleForDebug
    public record AndPredicate<T>(ArrayList<Predicate<? super T>> predicates) implements Predicate<T> {
        @Override
        public boolean test(T obj) {
            //noinspection ForLoopReplaceableByForEach
            for (int i = 0; i < this.predicates.size(); i++) {
                if (!this.predicates.get(i).test(obj)) {
                    return false;
                }
            }
            return true;
        }
    }

    @VisibleForDebug
    public record OrPredicate<T>(ArrayList<Predicate<? super T>> predicates) implements Predicate<T> {
        @Override
        public boolean test(T obj) {
            //noinspection ForLoopReplaceableByForEach
            for (int i = 0; i < this.predicates.size(); i++) {
                if (this.predicates.get(i).test(obj)) {
                    return true;
                }
            }
            return false;
        }
    }
}
