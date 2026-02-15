/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.util;

import it.unimi.dsi.fastutil.Pair;
import org.jetbrains.annotations.NotNull;
import survivalblock.atmosphere.atmospheric_api.not_mixin.funny.BadAtProgramming;
import survivalblock.atmosphere.atmospheric_api.not_mixin.funny.DangerousAndOrUnstable;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * A Pair* that stores two elements of the same type.
 * This class expects the programmer to know what they are doing
 * because this class does not support any of the {@link List#add(Object)}
 * and {@link List#remove(Object)} methods and will instead throw an exception.
 * This is probably not a very good design choice by the author, but I suppose
 * people will have to deal with it (who is even going to use this?).
 * <p>
 * *Why are there so many different Pair classes??
 * @param <E> the type of the elements of the {@link Duo}
 */
@BadAtProgramming
@DangerousAndOrUnstable
public class Duo<E> extends AbstractList<E> implements List<E>, Pair<E, E>, RandomAccess {

    public static final int SIZE = 2;

    private E first;
    private E second;

    public Duo(E first, E second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Creates a Duo from the first two elements of a {@link List}
     */
    public Duo(List<E> other) {
        //assert other.size() == Duo.SIZE;
        this.first = other.get(0);
        this.second = other.get(1);
    }

    @Override
    public E set(int index, E element) {
        Objects.checkIndex(index, Duo.SIZE);
        E previous;
        if (index == 1) {
            previous = this.second;
            this.second = element;
            return previous;
        }
        previous = this.first;
        this.first = element;
        return previous;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, Duo.SIZE);
        return index == 0 ? this.first : this.second;
    }

    @Override
    public boolean contains(Object object) {
        return Objects.equals(this.first, object) || Objects.equals(this.second, object);
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> collection) {
        if (collection.size() > Duo.SIZE) {
            return false;
        }
        return super.containsAll(collection);
    }

    @Override
    public E getFirst() {
        return this.first;
    }

    @SuppressWarnings("unused")
    public E getSecond() {
        return this.second;
    }

    @Override
    public E getLast() {
        return this.second;
    }

    @Override
    public int size() {
        return Duo.SIZE;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, @NotNull Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeIf(Predicate<? super E> predicate) {
        throw new UnsupportedOperationException();
    }

    @Override
    public @NotNull Iterator<E> iterator() {
        return new DuoItr();
    }

    @Override
    public @NotNull ListIterator<E> listIterator(int index) {
        return new DuoListItr(index);
    }

    @Override
    public Spliterator<E> spliterator() {
        return new DuoSpliterator<>(this);
    }

    @Override
    public @NotNull Single<E> subList(int fromIndex, int toIndex) {
        return new Single<>(this, fromIndex, toIndex);
    }

    @Override
    public E left() {
        return this.first;
    }

    @Override
    public E right() {
        return this.second;
    }

    public sealed class DuoItr implements Iterator<E> permits DuoListItr {
        int cursor = 0;
        int prevIndex = -1;

        @Override
        public boolean hasNext() {
            return cursor < Duo.SIZE;
        }

        @Override
        public E next() {
            try {
                // no need to check for comodification, because the remove operation is unsupported (I really hope programming works like this)
                E next = Duo.this.get(cursor);
                prevIndex = cursor;
                cursor++;
                return next;
            } catch (IndexOutOfBoundsException exception) {
                throw new NoSuchElementException(exception);
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public final class DuoListItr extends DuoItr implements ListIterator<E> {
        DuoListItr(int index) {
            cursor = index;
        }

        @Override
        public boolean hasPrevious() {
            return cursor > 0;
        }

        @Override
        public E previous() {
            try {
                cursor--;
                E previous = Duo.this.get(cursor);
                prevIndex = cursor;
                return previous;
            } catch (IndexOutOfBoundsException exception) {
                throw new NoSuchElementException(exception);
            }
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void set(E element) {
            Duo.this.set(prevIndex, element);
        }

        @Override
        public void add(E element) {
            throw new UnsupportedOperationException();
        }
    }

    /*
    What even is a Spliterator
    Mostly copied from AbstractList.RandomAccessSpliterator because I can't figure out what's going on
     */
    public static final class DuoSpliterator<E> implements Spliterator<E> {

        private final Duo<E> duo;
        private int index = 0;
        private int fence = -1;

        DuoSpliterator(@NotNull Duo<E> duo) {
            this.duo = duo;
        }

        private DuoSpliterator(DuoSpliterator<E> parent, int origin, int fence) {
            this.duo = parent.duo;
            this.index = origin;
            this.fence = fence;
        }

        private int getFence() {
            int hi;
            if ((hi = fence) < 0) {
                hi = fence = duo.size();
            }
            return hi;
        }

        @Override
        public Spliterator<E> trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid) ? null : new DuoSpliterator<>(this, lo, index = mid);
        }

        @Override
        public boolean tryAdvance(Consumer<? super E> consumer) {
            if (consumer == null)
                throw new NullPointerException();
            int hi = getFence(), i = index;
            if (i < hi) {
                index = i + 1;
                consumer.accept(get(duo, i));
                return true;
            }
            return false;
        }

        @Override
        public void forEachRemaining(Consumer<? super E> consumer) {
            Objects.requireNonNull(consumer);
            int hi = getFence();
            int i = index;
            index = hi;
            for (; i < hi; i++) {
                consumer.accept(get(duo, i));
            }
        }

        @Override
        public long estimateSize() {
            return getFence() - index;
        }

        @Override
        public int characteristics() {
            return Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED;
        }

        private static <E> E get(List<E> list, int i) {
            try {
                return list.get(i);
            } catch (IndexOutOfBoundsException exception) {
                throw new ConcurrentModificationException();
            }
        }
    }

    @SuppressWarnings("RedundantMethodOverride")
    public static final class Single<E> extends AbstractList<E> {
        public static final int SIZE = 1;
        private final Duo<E> duo;
        private final int offset;
        private final boolean empty;

        Single(Duo<E> duo, int fromIndex, int toIndex) {
            this.duo = duo;
            this.offset = getOffsetFromIndexes(fromIndex, toIndex);
            // this is a horrendous constructor
            this.empty = (this.offset == Short.MIN_VALUE);
        }

        Single(Single<E> parent, int fromIndex, int toIndex) {
            this(parent.duo, fromIndex, toIndex);
        }

        private int getOffsetFromIndexes(int fromIndex, int toIndex) {
            if ((toIndex - fromIndex) == 1) {
                Objects.checkIndex(fromIndex, Duo.SIZE);
                return fromIndex;
            }
            if (toIndex == fromIndex) {
                return Short.MIN_VALUE;
            }
            throw new RuntimeException("An exception occurred while attempting to create a Single! Did you check the index? fromIndex=" + fromIndex + " and toIndex =" + toIndex);
        }

        public E set(int index, E element) {
            Objects.checkIndex(index, size());
            return duo.set(offset + index, element);
        }

        public E get(int index) {
            Objects.checkIndex(index, size());
            return duo.get(offset + index);
        }

        public int size() {
            return empty ? 0 : Single.SIZE;
        }

        public void add(int index, E element) {
            throw new UnsupportedOperationException();
        }

        public E remove(int index) {
            throw new UnsupportedOperationException();
        }

        // mostly copied from AbstractList.SubList
        public @NotNull Iterator<E> iterator() {
            return listIterator();
        }

        public @NotNull ListIterator<E> listIterator(int index) {
            return new ListIterator<>() {
                private final ListIterator<E> delegate = duo.listIterator(offset + index);

                public void set(E e) {
                    delegate.set(e);
                }

                public boolean hasNext() {
                    return nextIndex() < Single.this.size();
                }

                public E next() {
                    if (hasNext()) {
                        return delegate.next();
                    }
                    throw new NoSuchElementException();
                }

                public boolean hasPrevious() {
                    return previousIndex() >= 0;
                }

                public E previous() {
                    if (hasPrevious()) {
                        return delegate.previous();
                    }
                    throw new NoSuchElementException();
                }

                public int nextIndex() {
                    return delegate.nextIndex() - offset;
                }

                public int previousIndex() {
                    return delegate.previousIndex() - offset;
                }

                public void add(E e) {
                    throw new UnsupportedOperationException();
                }

                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        public @NotNull Single<E> subList(int fromIndex, int toIndex) {
            return new Single<>(this, fromIndex, toIndex);
        }

        // end credit, I guess
    }
}
