/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.datafixer;

import com.mojang.datafixers.util.*;
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

        static <O, F1, F2, F3, F4, F5> MapCodec<O> tuple(
                MapCodec<F1> codec1, Function<O, F1> from1,
                MapCodec<F2> codec2, Function<O, F2> from2,
                MapCodec<F3> codec3, Function<O, F3> from3,
                MapCodec<F4> codec4, Function<O, F4> from4,
                MapCodec<F5> codec5, Function<O, F5> from5,
                Function5<F1, F2, F3, F4, F5, O> to
        ) {
            return RecordCodecBuilder.mapCodec(
                    instance -> instance.group(
                            codec1.forGetter(from1),
                            codec2.forGetter(from2),
                            codec3.forGetter(from3),
                            codec4.forGetter(from4),
                            codec5.forGetter(from5)
                    ).apply(instance, to)
            );
        }

        static <O, F1, F2, F3, F4, F5, F6> MapCodec<O> tuple(
                MapCodec<F1> codec1, Function<O, F1> from1,
                MapCodec<F2> codec2, Function<O, F2> from2,
                MapCodec<F3> codec3, Function<O, F3> from3,
                MapCodec<F4> codec4, Function<O, F4> from4,
                MapCodec<F5> codec5, Function<O, F5> from5,
                MapCodec<F6> codec6, Function<O, F6> from6,
                Function6<F1, F2, F3, F4, F5, F6, O> to
        ) {
            return RecordCodecBuilder.mapCodec(
                    instance -> instance.group(
                            codec1.forGetter(from1),
                            codec2.forGetter(from2),
                            codec3.forGetter(from3),
                            codec4.forGetter(from4),
                            codec5.forGetter(from5),
                            codec6.forGetter(from6)
                    ).apply(instance, to)
            );
        }

        static <O, F1, F2, F3, F4, F5, F6, F7> MapCodec<O> tuple(
                MapCodec<F1> codec1, Function<O, F1> from1,
                MapCodec<F2> codec2, Function<O, F2> from2,
                MapCodec<F3> codec3, Function<O, F3> from3,
                MapCodec<F4> codec4, Function<O, F4> from4,
                MapCodec<F5> codec5, Function<O, F5> from5,
                MapCodec<F6> codec6, Function<O, F6> from6,
                MapCodec<F7> codec7, Function<O, F7> from7,
                Function7<F1, F2, F3, F4, F5, F6, F7, O> to
        ) {
            return RecordCodecBuilder.mapCodec(
                    instance -> instance.group(
                            codec1.forGetter(from1),
                            codec2.forGetter(from2),
                            codec3.forGetter(from3),
                            codec4.forGetter(from4),
                            codec5.forGetter(from5),
                            codec6.forGetter(from6),
                            codec7.forGetter(from7)
                    ).apply(instance, to)
            );
        }

        static <O, F1, F2, F3, F4, F5, F6, F7, F8> MapCodec<O> tuple(
                MapCodec<F1> codec1, Function<O, F1> from1,
                MapCodec<F2> codec2, Function<O, F2> from2,
                MapCodec<F3> codec3, Function<O, F3> from3,
                MapCodec<F4> codec4, Function<O, F4> from4,
                MapCodec<F5> codec5, Function<O, F5> from5,
                MapCodec<F6> codec6, Function<O, F6> from6,
                MapCodec<F7> codec7, Function<O, F7> from7,
                MapCodec<F8> codec8, Function<O, F8> from8,
                Function8<F1, F2, F3, F4, F5, F6, F7, F8, O> to
        ) {
            return RecordCodecBuilder.mapCodec(
                    instance -> instance.group(
                            codec1.forGetter(from1),
                            codec2.forGetter(from2),
                            codec3.forGetter(from3),
                            codec4.forGetter(from4),
                            codec5.forGetter(from5),
                            codec6.forGetter(from6),
                            codec7.forGetter(from7),
                            codec8.forGetter(from8)
                    ).apply(instance, to)
            );
        }

        static <O, F1, F2, F3, F4, F5, F6, F7, F8, F9> MapCodec<O> tuple(
                MapCodec<F1> codec1, Function<O, F1> from1,
                MapCodec<F2> codec2, Function<O, F2> from2,
                MapCodec<F3> codec3, Function<O, F3> from3,
                MapCodec<F4> codec4, Function<O, F4> from4,
                MapCodec<F5> codec5, Function<O, F5> from5,
                MapCodec<F6> codec6, Function<O, F6> from6,
                MapCodec<F7> codec7, Function<O, F7> from7,
                MapCodec<F8> codec8, Function<O, F8> from8,
                MapCodec<F9> codec9, Function<O, F9> from9,
                Function9<F1, F2, F3, F4, F5, F6, F7, F8, F9, O> to
        ) {
            return RecordCodecBuilder.mapCodec(
                    instance -> instance.group(
                            codec1.forGetter(from1),
                            codec2.forGetter(from2),
                            codec3.forGetter(from3),
                            codec4.forGetter(from4),
                            codec5.forGetter(from5),
                            codec6.forGetter(from6),
                            codec7.forGetter(from7),
                            codec8.forGetter(from8),
                            codec9.forGetter(from9)
                    ).apply(instance, to)
            );
        }

        static <O, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10> MapCodec<O> tuple(
                MapCodec<F1> codec1, Function<O, F1> from1,
                MapCodec<F2> codec2, Function<O, F2> from2,
                MapCodec<F3> codec3, Function<O, F3> from3,
                MapCodec<F4> codec4, Function<O, F4> from4,
                MapCodec<F5> codec5, Function<O, F5> from5,
                MapCodec<F6> codec6, Function<O, F6> from6,
                MapCodec<F7> codec7, Function<O, F7> from7,
                MapCodec<F8> codec8, Function<O, F8> from8,
                MapCodec<F9> codec9, Function<O, F9> from9,
                MapCodec<F10> codec10, Function<O, F10> from10,
                Function10<F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, O> to
        ) {
            return RecordCodecBuilder.mapCodec(
                    instance -> instance.group(
                            codec1.forGetter(from1),
                            codec2.forGetter(from2),
                            codec3.forGetter(from3),
                            codec4.forGetter(from4),
                            codec5.forGetter(from5),
                            codec6.forGetter(from6),
                            codec7.forGetter(from7),
                            codec8.forGetter(from8),
                            codec9.forGetter(from9),
                            codec10.forGetter(from10)
                    ).apply(instance, to)
            );
        }

        static <O, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11> MapCodec<O> tuple(
                MapCodec<F1> codec1, Function<O, F1> from1,
                MapCodec<F2> codec2, Function<O, F2> from2,
                MapCodec<F3> codec3, Function<O, F3> from3,
                MapCodec<F4> codec4, Function<O, F4> from4,
                MapCodec<F5> codec5, Function<O, F5> from5,
                MapCodec<F6> codec6, Function<O, F6> from6,
                MapCodec<F7> codec7, Function<O, F7> from7,
                MapCodec<F8> codec8, Function<O, F8> from8,
                MapCodec<F9> codec9, Function<O, F9> from9,
                MapCodec<F10> codec10, Function<O, F10> from10,
                MapCodec<F11> codec11, Function<O, F11> from11,
                Function11<F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, O> to
        ) {
            return RecordCodecBuilder.mapCodec(
                    instance -> instance.group(
                            codec1.forGetter(from1),
                            codec2.forGetter(from2),
                            codec3.forGetter(from3),
                            codec4.forGetter(from4),
                            codec5.forGetter(from5),
                            codec6.forGetter(from6),
                            codec7.forGetter(from7),
                            codec8.forGetter(from8),
                            codec9.forGetter(from9),
                            codec10.forGetter(from10),
                            codec11.forGetter(from11)
                    ).apply(instance, to)
            );
        }

        static <O, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12> MapCodec<O> tuple(
                MapCodec<F1> codec1, Function<O, F1> from1,
                MapCodec<F2> codec2, Function<O, F2> from2,
                MapCodec<F3> codec3, Function<O, F3> from3,
                MapCodec<F4> codec4, Function<O, F4> from4,
                MapCodec<F5> codec5, Function<O, F5> from5,
                MapCodec<F6> codec6, Function<O, F6> from6,
                MapCodec<F7> codec7, Function<O, F7> from7,
                MapCodec<F8> codec8, Function<O, F8> from8,
                MapCodec<F9> codec9, Function<O, F9> from9,
                MapCodec<F10> codec10, Function<O, F10> from10,
                MapCodec<F11> codec11, Function<O, F11> from11,
                MapCodec<F12> codec12, Function<O, F12> from12,
                Function12<F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, O> to
        ) {
            return RecordCodecBuilder.mapCodec(
                    instance -> instance.group(
                            codec1.forGetter(from1),
                            codec2.forGetter(from2),
                            codec3.forGetter(from3),
                            codec4.forGetter(from4),
                            codec5.forGetter(from5),
                            codec6.forGetter(from6),
                            codec7.forGetter(from7),
                            codec8.forGetter(from8),
                            codec9.forGetter(from9),
                            codec10.forGetter(from10),
                            codec11.forGetter(from11),
                            codec12.forGetter(from12)
                    ).apply(instance, to)
            );
        }

        static <O, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13> MapCodec<O> tuple(
                MapCodec<F1> codec1, Function<O, F1> from1,
                MapCodec<F2> codec2, Function<O, F2> from2,
                MapCodec<F3> codec3, Function<O, F3> from3,
                MapCodec<F4> codec4, Function<O, F4> from4,
                MapCodec<F5> codec5, Function<O, F5> from5,
                MapCodec<F6> codec6, Function<O, F6> from6,
                MapCodec<F7> codec7, Function<O, F7> from7,
                MapCodec<F8> codec8, Function<O, F8> from8,
                MapCodec<F9> codec9, Function<O, F9> from9,
                MapCodec<F10> codec10, Function<O, F10> from10,
                MapCodec<F11> codec11, Function<O, F11> from11,
                MapCodec<F12> codec12, Function<O, F12> from12,
                MapCodec<F13> codec13, Function<O, F13> from13,
                Function13<F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13, O> to
        ) {
            return RecordCodecBuilder.mapCodec(
                    instance -> instance.group(
                            codec1.forGetter(from1),
                            codec2.forGetter(from2),
                            codec3.forGetter(from3),
                            codec4.forGetter(from4),
                            codec5.forGetter(from5),
                            codec6.forGetter(from6),
                            codec7.forGetter(from7),
                            codec8.forGetter(from8),
                            codec9.forGetter(from9),
                            codec10.forGetter(from10),
                            codec11.forGetter(from11),
                            codec12.forGetter(from12),
                            codec13.forGetter(from13)
                    ).apply(instance, to)
            );
        }

        static <O, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13, F14> MapCodec<O> tuple(
                MapCodec<F1> codec1, Function<O, F1> from1,
                MapCodec<F2> codec2, Function<O, F2> from2,
                MapCodec<F3> codec3, Function<O, F3> from3,
                MapCodec<F4> codec4, Function<O, F4> from4,
                MapCodec<F5> codec5, Function<O, F5> from5,
                MapCodec<F6> codec6, Function<O, F6> from6,
                MapCodec<F7> codec7, Function<O, F7> from7,
                MapCodec<F8> codec8, Function<O, F8> from8,
                MapCodec<F9> codec9, Function<O, F9> from9,
                MapCodec<F10> codec10, Function<O, F10> from10,
                MapCodec<F11> codec11, Function<O, F11> from11,
                MapCodec<F12> codec12, Function<O, F12> from12,
                MapCodec<F13> codec13, Function<O, F13> from13,
                MapCodec<F14> codec14, Function<O, F14> from14,
                Function14<F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13, F14, O> to
        ) {
            return RecordCodecBuilder.mapCodec(
                    instance -> instance.group(
                            codec1.forGetter(from1),
                            codec2.forGetter(from2),
                            codec3.forGetter(from3),
                            codec4.forGetter(from4),
                            codec5.forGetter(from5),
                            codec6.forGetter(from6),
                            codec7.forGetter(from7),
                            codec8.forGetter(from8),
                            codec9.forGetter(from9),
                            codec10.forGetter(from10),
                            codec11.forGetter(from11),
                            codec12.forGetter(from12),
                            codec13.forGetter(from13),
                            codec14.forGetter(from14)
                    ).apply(instance, to)
            );
        }

        static <O, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13, F14, F15> MapCodec<O> tuple(
                MapCodec<F1> codec1, Function<O, F1> from1,
                MapCodec<F2> codec2, Function<O, F2> from2,
                MapCodec<F3> codec3, Function<O, F3> from3,
                MapCodec<F4> codec4, Function<O, F4> from4,
                MapCodec<F5> codec5, Function<O, F5> from5,
                MapCodec<F6> codec6, Function<O, F6> from6,
                MapCodec<F7> codec7, Function<O, F7> from7,
                MapCodec<F8> codec8, Function<O, F8> from8,
                MapCodec<F9> codec9, Function<O, F9> from9,
                MapCodec<F10> codec10, Function<O, F10> from10,
                MapCodec<F11> codec11, Function<O, F11> from11,
                MapCodec<F12> codec12, Function<O, F12> from12,
                MapCodec<F13> codec13, Function<O, F13> from13,
                MapCodec<F14> codec14, Function<O, F14> from14,
                MapCodec<F15> codec15, Function<O, F15> from15,
                Function15<F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13, F14, F15, O> to
        ) {
            return RecordCodecBuilder.mapCodec(
                    instance -> instance.group(
                            codec1.forGetter(from1),
                            codec2.forGetter(from2),
                            codec3.forGetter(from3),
                            codec4.forGetter(from4),
                            codec5.forGetter(from5),
                            codec6.forGetter(from6),
                            codec7.forGetter(from7),
                            codec8.forGetter(from8),
                            codec9.forGetter(from9),
                            codec10.forGetter(from10),
                            codec11.forGetter(from11),
                            codec12.forGetter(from12),
                            codec13.forGetter(from13),
                            codec14.forGetter(from14),
                            codec15.forGetter(from15)
                    ).apply(instance, to)
            );
        }

        static <O, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13, F14, F15, F16> MapCodec<O> tuple(
                MapCodec<F1> codec1, Function<O, F1> from1,
                MapCodec<F2> codec2, Function<O, F2> from2,
                MapCodec<F3> codec3, Function<O, F3> from3,
                MapCodec<F4> codec4, Function<O, F4> from4,
                MapCodec<F5> codec5, Function<O, F5> from5,
                MapCodec<F6> codec6, Function<O, F6> from6,
                MapCodec<F7> codec7, Function<O, F7> from7,
                MapCodec<F8> codec8, Function<O, F8> from8,
                MapCodec<F9> codec9, Function<O, F9> from9,
                MapCodec<F10> codec10, Function<O, F10> from10,
                MapCodec<F11> codec11, Function<O, F11> from11,
                MapCodec<F12> codec12, Function<O, F12> from12,
                MapCodec<F13> codec13, Function<O, F13> from13,
                MapCodec<F14> codec14, Function<O, F14> from14,
                MapCodec<F15> codec15, Function<O, F15> from15,
                MapCodec<F16> codec16, Function<O, F16> from16,
                Function16<F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13, F14, F15, F16, O> to
        ) {
            return RecordCodecBuilder.mapCodec(
                    instance -> instance.group(
                            codec1.forGetter(from1),
                            codec2.forGetter(from2),
                            codec3.forGetter(from3),
                            codec4.forGetter(from4),
                            codec5.forGetter(from5),
                            codec6.forGetter(from6),
                            codec7.forGetter(from7),
                            codec8.forGetter(from8),
                            codec9.forGetter(from9),
                            codec10.forGetter(from10),
                            codec11.forGetter(from11),
                            codec12.forGetter(from12),
                            codec13.forGetter(from13),
                            codec14.forGetter(from14),
                            codec15.forGetter(from15),
                            codec16.forGetter(from16)
                    ).apply(instance, to)
            );
        }
    }
}
