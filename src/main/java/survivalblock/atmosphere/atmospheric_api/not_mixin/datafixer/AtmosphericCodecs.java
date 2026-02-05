/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.datafixer;

import com.mojang.datafixers.util.Function3;
import com.mojang.datafixers.util.Function4;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.ApiStatus;
import survivalblock.atmosphere.atmospheric_api.not_mixin.funny.BadAtProgramming;
import survivalblock.atmosphere.atmospheric_api.not_mixin.util.Duo;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@SuppressWarnings("unused")
public interface AtmosphericCodecs {

    // a codec for boxes, but I don't know why you would need to use this
    // I feel like at this point I'm just making codecs for the sake of making codecs
    Codec<AABB> AABB = duo(Vec3.CODEC).xmap(duo -> new AABB(duo.getFirst(), duo.getLast()),
            box -> new Duo<>(box.getMinPosition(), box.getMaxPosition()));

    @BadAtProgramming
    static <E> Codec<Duo<E>> duo(Codec<E> codec) {
        return codec.listOf(2, 2).xmap(Duo::new, List::copyOf);
    }

    @ApiStatus.Experimental
    interface RCB {
        static <O, F1> MapCodec<O> tuple(MapCodec<F1> codec, Function<O, F1> from, Function<F1, O> to) {
            return RecordCodecBuilder.mapCodec(
                    instance -> instance.group(
                                    codec.forGetter(from)
                            ).apply(instance, to)
            );
        }

        static <O, F1, F2> MapCodec<O> tuple(
                MapCodec<F1> codec1, Function<O, F1> from1,
                MapCodec<F2> codec2, Function<O, F2> from2,
                BiFunction<F1, F2, O> to
        ) {
            return RecordCodecBuilder.mapCodec(
                    instance -> instance.group(
                                    codec1.forGetter(from1),
                                    codec2.forGetter(from2)
                            ).apply(instance, to)
            );
        }

        static <O, F1, F2, F3> MapCodec<O> tuple(
                MapCodec<F1> codec1, Function<O, F1> from1,
                MapCodec<F2> codec2, Function<O, F2> from2,
                MapCodec<F3> codec3, Function<O, F3> from3,
                Function3<F1, F2, F3, O> to
        ) {
            return RecordCodecBuilder.mapCodec(
                    instance -> instance.group(
                                    codec1.forGetter(from1),
                                    codec2.forGetter(from2),
                                    codec3.forGetter(from3)
                            ).apply(instance, to)
            );
        }

        static <O, F1, F2, F3, F4> MapCodec<O> tuple(
                MapCodec<F1> codec1, Function<O, F1> from1,
                MapCodec<F2> codec2, Function<O, F2> from2,
                MapCodec<F3> codec3, Function<O, F3> from3,
                MapCodec<F4> codec4, Function<O, F4> from4,
                Function4<F1, F2, F3, F4, O> to
        ) {
            return RecordCodecBuilder.mapCodec(
                    instance -> instance.group(
                            codec1.forGetter(from1),
                            codec2.forGetter(from2),
                            codec3.forGetter(from3),
                            codec4.forGetter(from4)
                    ).apply(instance, to)
            );
        }
    }
}
