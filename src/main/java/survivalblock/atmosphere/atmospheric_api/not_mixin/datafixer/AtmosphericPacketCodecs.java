package survivalblock.atmosphere.atmospheric_api.not_mixin.datafixer;

import com.mojang.datafixers.util.*;
import io.netty.buffer.ByteBuf;
import survivalblock.atmosphere.atmospheric_api.not_mixin.funny.DangerousAndOrUnstable;
import survivalblock.atmosphere.atmospheric_api.not_mixin.util.Duo;
import survivalblock.atmosphere.atmospheric_api.not_mixin.util.FunctionUnlimited;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

@SuppressWarnings("unused")
public interface AtmosphericPacketCodecs {

    StreamCodec<ByteBuf, SoundEvent> SOUND_EVENT_BY_ID = viaId(BuiltInRegistries.SOUND_EVENT);

    StreamCodec<ByteBuf, Vec3> VEC3D = new StreamCodec<>() {
        public Vec3 decode(ByteBuf byteBuf) {
            return new Vec3(byteBuf.readDouble(), byteBuf.readDouble(), byteBuf.readDouble());
        }

        public void encode(ByteBuf byteBuf, Vec3 vec3d) {
            byteBuf.writeDouble(vec3d.x());
            byteBuf.writeDouble(vec3d.y());
            byteBuf.writeDouble(vec3d.z());
        }
    };

    StreamCodec<ByteBuf, AABB> BOX = VEC3D.apply(AtmosphericPacketCodecs::duo)
            .map(vec3ds -> new AABB(vec3ds.getFirst(), vec3ds.getSecond()),
                    box -> new Duo<>(box.getMinPosition(), box.getMaxPosition()));

    static <V> StreamCodec<ByteBuf, V> viaId(Registry<V> registry) {
        return ResourceLocation.STREAM_CODEC.map(registry::get, registry::getKey);
    }

    static <B extends ByteBuf, V> StreamCodec<B, Duo<V>> duo(StreamCodec<? super B, V> elementCodec) {
        return new StreamCodec<>() {
            public Duo<V> decode(B byteBuf) {
                ByteBufCodecs.readCount(byteBuf, 2);
                return new Duo<>(elementCodec.decode(byteBuf), elementCodec.decode(byteBuf));
            }

            public void encode(B byteBuf, Duo<V> duo) {
                // IsThisEvenNecessary(IsThisEvenNecessary.LevelsOfUnnecessity.PROBABLY_NOT)
                ByteBufCodecs.writeCount(byteBuf, duo.size(), 2); // ensures it's actually a duo of size 2
                elementCodec.encode(byteBuf, duo.getFirst());
                elementCodec.encode(byteBuf, duo.getSecond());
            }
        };
    }

    static <B, C, T1, T2, T3, T4, T5, T6, T7> StreamCodec<B, C> tuple(
            StreamCodec<? super B, T1> codec1,
            Function<C, T1> from1,
            StreamCodec<? super B, T2> codec2,
            Function<C, T2> from2,
            StreamCodec<? super B, T3> codec3,
            Function<C, T3> from3,
            StreamCodec<? super B, T4> codec4,
            Function<C, T4> from4,
            StreamCodec<? super B, T5> codec5,
            Function<C, T5> from5,
            StreamCodec<? super B, T6> codec6,
            Function<C, T6> from6,
            StreamCodec<? super B, T7> codec7,
            Function<C, T7> from7,
            Function7<T1, T2, T3, T4, T5, T6, T7, C> to
    ) {
        return new StreamCodec<>() {
            @Override
            public C decode(B object) {
                T1 object2 = codec1.decode(object);
                T2 object3 = codec2.decode(object);
                T3 object4 = codec3.decode(object);
                T4 object5 = codec4.decode(object);
                T5 object6 = codec5.decode(object);
                T6 object7 = codec6.decode(object);
                T7 object8 = codec7.decode(object);
                return to.apply(object2, object3, object4, object5, object6, object7, object8);
            }

            @Override
            public void encode(B object, C object2) {
                codec1.encode(object, from1.apply(object2));
                codec2.encode(object, from2.apply(object2));
                codec3.encode(object, from3.apply(object2));
                codec4.encode(object, from4.apply(object2));
                codec5.encode(object, from5.apply(object2));
                codec6.encode(object, from6.apply(object2));
                codec7.encode(object, from7.apply(object2));
            }
        };
    }

    static <B, C, T1, T2, T3, T4, T5, T6, T7, T8> StreamCodec<B, C> tuple(
            StreamCodec<? super B, T1> codec1,
            Function<C, T1> from1,
            StreamCodec<? super B, T2> codec2,
            Function<C, T2> from2,
            StreamCodec<? super B, T3> codec3,
            Function<C, T3> from3,
            StreamCodec<? super B, T4> codec4,
            Function<C, T4> from4,
            StreamCodec<? super B, T5> codec5,
            Function<C, T5> from5,
            StreamCodec<? super B, T6> codec6,
            Function<C, T6> from6,
            StreamCodec<? super B, T7> codec7,
            Function<C, T7> from7,
            StreamCodec<? super B, T8> codec8,
            Function<C, T8> from8,
            Function8<T1, T2, T3, T4, T5, T6, T7, T8, C> to
    ) {
        return new StreamCodec<>() {
            @Override
            public C decode(B object) {
                T1 object2 = codec1.decode(object);
                T2 object3 = codec2.decode(object);
                T3 object4 = codec3.decode(object);
                T4 object5 = codec4.decode(object);
                T5 object6 = codec5.decode(object);
                T6 object7 = codec6.decode(object);
                T7 object8 = codec7.decode(object);
                T8 object9 = codec8.decode(object);
                return to.apply(object2, object3, object4, object5, object6, object7, object8, object9);
            }

            @Override
            public void encode(B object, C object2) {
                codec1.encode(object, from1.apply(object2));
                codec2.encode(object, from2.apply(object2));
                codec3.encode(object, from3.apply(object2));
                codec4.encode(object, from4.apply(object2));
                codec5.encode(object, from5.apply(object2));
                codec6.encode(object, from6.apply(object2));
                codec7.encode(object, from7.apply(object2));
                codec8.encode(object, from8.apply(object2));
            }
        };
    }

    static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9> StreamCodec<B, C> tuple(
            StreamCodec<? super B, T1> codec1,
            Function<C, T1> from1,
            StreamCodec<? super B, T2> codec2,
            Function<C, T2> from2,
            StreamCodec<? super B, T3> codec3,
            Function<C, T3> from3,
            StreamCodec<? super B, T4> codec4,
            Function<C, T4> from4,
            StreamCodec<? super B, T5> codec5,
            Function<C, T5> from5,
            StreamCodec<? super B, T6> codec6,
            Function<C, T6> from6,
            StreamCodec<? super B, T7> codec7,
            Function<C, T7> from7,
            StreamCodec<? super B, T8> codec8,
            Function<C, T8> from8,
            StreamCodec<? super B, T9> codec9,
            Function<C, T9> from9,
            Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, C> to
    ) {
        return new StreamCodec<>() {
            @Override
            public C decode(B object) {
                T1 object2 = codec1.decode(object);
                T2 object3 = codec2.decode(object);
                T3 object4 = codec3.decode(object);
                T4 object5 = codec4.decode(object);
                T5 object6 = codec5.decode(object);
                T6 object7 = codec6.decode(object);
                T7 object8 = codec7.decode(object);
                T8 object9 = codec8.decode(object);
                T9 object10 = codec9.decode(object);
                return to.apply(object2, object3, object4, object5, object6, object7, object8, object9, object10);
            }

            @Override
            public void encode(B object, C object2) {
                codec1.encode(object, from1.apply(object2));
                codec2.encode(object, from2.apply(object2));
                codec3.encode(object, from3.apply(object2));
                codec4.encode(object, from4.apply(object2));
                codec5.encode(object, from5.apply(object2));
                codec6.encode(object, from6.apply(object2));
                codec7.encode(object, from7.apply(object2));
                codec8.encode(object, from8.apply(object2));
                codec9.encode(object, from9.apply(object2));
            }
        };
    }

    static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> StreamCodec<B, C> tuple(
            StreamCodec<? super B, T1> codec1,
            Function<C, T1> from1,
            StreamCodec<? super B, T2> codec2,
            Function<C, T2> from2,
            StreamCodec<? super B, T3> codec3,
            Function<C, T3> from3,
            StreamCodec<? super B, T4> codec4,
            Function<C, T4> from4,
            StreamCodec<? super B, T5> codec5,
            Function<C, T5> from5,
            StreamCodec<? super B, T6> codec6,
            Function<C, T6> from6,
            StreamCodec<? super B, T7> codec7,
            Function<C, T7> from7,
            StreamCodec<? super B, T8> codec8,
            Function<C, T8> from8,
            StreamCodec<? super B, T9> codec9,
            Function<C, T9> from9,
            StreamCodec<? super B, T10> codec10,
            Function<C, T10> from10,
            Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, C> to
    ) {
        return new StreamCodec<>() {
            @Override
            public C decode(B object) {
                T1 object2 = codec1.decode(object);
                T2 object3 = codec2.decode(object);
                T3 object4 = codec3.decode(object);
                T4 object5 = codec4.decode(object);
                T5 object6 = codec5.decode(object);
                T6 object7 = codec6.decode(object);
                T7 object8 = codec7.decode(object);
                T8 object9 = codec8.decode(object);
                T9 object10 = codec9.decode(object);
                T10 object11 = codec10.decode(object);
                return to.apply(object2, object3, object4, object5, object6, object7, object8, object9, object10, object11);
            }

            @Override
            public void encode(B object, C object2) {
                codec1.encode(object, from1.apply(object2));
                codec2.encode(object, from2.apply(object2));
                codec3.encode(object, from3.apply(object2));
                codec4.encode(object, from4.apply(object2));
                codec5.encode(object, from5.apply(object2));
                codec6.encode(object, from6.apply(object2));
                codec7.encode(object, from7.apply(object2));
                codec8.encode(object, from8.apply(object2));
                codec9.encode(object, from9.apply(object2));
                codec10.encode(object, from10.apply(object2));
            }
        };
    }

    static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> StreamCodec<B, C> tuple(
            StreamCodec<? super B, T1> codec1,
            Function<C, T1> from1,
            StreamCodec<? super B, T2> codec2,
            Function<C, T2> from2,
            StreamCodec<? super B, T3> codec3,
            Function<C, T3> from3,
            StreamCodec<? super B, T4> codec4,
            Function<C, T4> from4,
            StreamCodec<? super B, T5> codec5,
            Function<C, T5> from5,
            StreamCodec<? super B, T6> codec6,
            Function<C, T6> from6,
            StreamCodec<? super B, T7> codec7,
            Function<C, T7> from7,
            StreamCodec<? super B, T8> codec8,
            Function<C, T8> from8,
            StreamCodec<? super B, T9> codec9,
            Function<C, T9> from9,
            StreamCodec<? super B, T10> codec10,
            Function<C, T10> from10,
            StreamCodec<? super B, T11> codec11,
            Function<C, T11> from11,
            Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, C> to
    ) {
        return new StreamCodec<>() {
            @Override
            public C decode(B object) {
                T1 object2 = codec1.decode(object);
                T2 object3 = codec2.decode(object);
                T3 object4 = codec3.decode(object);
                T4 object5 = codec4.decode(object);
                T5 object6 = codec5.decode(object);
                T6 object7 = codec6.decode(object);
                T7 object8 = codec7.decode(object);
                T8 object9 = codec8.decode(object);
                T9 object10 = codec9.decode(object);
                T10 object11 = codec10.decode(object);
                T11 object12 = codec11.decode(object);
                return to.apply(object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12);
            }

            @Override
            public void encode(B object, C object2) {
                codec1.encode(object, from1.apply(object2));
                codec2.encode(object, from2.apply(object2));
                codec3.encode(object, from3.apply(object2));
                codec4.encode(object, from4.apply(object2));
                codec5.encode(object, from5.apply(object2));
                codec6.encode(object, from6.apply(object2));
                codec7.encode(object, from7.apply(object2));
                codec8.encode(object, from8.apply(object2));
                codec9.encode(object, from9.apply(object2));
                codec10.encode(object, from10.apply(object2));
                codec11.encode(object, from11.apply(object2));
            }
        };
    }

    static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> StreamCodec<B, C> tuple(
            StreamCodec<? super B, T1> codec1,
            Function<C, T1> from1,
            StreamCodec<? super B, T2> codec2,
            Function<C, T2> from2,
            StreamCodec<? super B, T3> codec3,
            Function<C, T3> from3,
            StreamCodec<? super B, T4> codec4,
            Function<C, T4> from4,
            StreamCodec<? super B, T5> codec5,
            Function<C, T5> from5,
            StreamCodec<? super B, T6> codec6,
            Function<C, T6> from6,
            StreamCodec<? super B, T7> codec7,
            Function<C, T7> from7,
            StreamCodec<? super B, T8> codec8,
            Function<C, T8> from8,
            StreamCodec<? super B, T9> codec9,
            Function<C, T9> from9,
            StreamCodec<? super B, T10> codec10,
            Function<C, T10> from10,
            StreamCodec<? super B, T11> codec11,
            Function<C, T11> from11,
            StreamCodec<? super B, T12> codec12,
            Function<C, T12> from12,
            Function12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, C> to
    ) {
        return new StreamCodec<>() {
            @Override
            public C decode(B object) {
                T1 object2 = codec1.decode(object);
                T2 object3 = codec2.decode(object);
                T3 object4 = codec3.decode(object);
                T4 object5 = codec4.decode(object);
                T5 object6 = codec5.decode(object);
                T6 object7 = codec6.decode(object);
                T7 object8 = codec7.decode(object);
                T8 object9 = codec8.decode(object);
                T9 object10 = codec9.decode(object);
                T10 object11 = codec10.decode(object);
                T11 object12 = codec11.decode(object);
                T12 object13 = codec12.decode(object);
                return to.apply(object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13);
            }

            @Override
            public void encode(B object, C object2) {
                codec1.encode(object, from1.apply(object2));
                codec2.encode(object, from2.apply(object2));
                codec3.encode(object, from3.apply(object2));
                codec4.encode(object, from4.apply(object2));
                codec5.encode(object, from5.apply(object2));
                codec6.encode(object, from6.apply(object2));
                codec7.encode(object, from7.apply(object2));
                codec8.encode(object, from8.apply(object2));
                codec9.encode(object, from9.apply(object2));
                codec10.encode(object, from10.apply(object2));
                codec11.encode(object, from11.apply(object2));
                codec12.encode(object, from12.apply(object2));
            }
        };
    }

    static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> StreamCodec<B, C> tuple(
            StreamCodec<? super B, T1> codec1,
            Function<C, T1> from1,
            StreamCodec<? super B, T2> codec2,
            Function<C, T2> from2,
            StreamCodec<? super B, T3> codec3,
            Function<C, T3> from3,
            StreamCodec<? super B, T4> codec4,
            Function<C, T4> from4,
            StreamCodec<? super B, T5> codec5,
            Function<C, T5> from5,
            StreamCodec<? super B, T6> codec6,
            Function<C, T6> from6,
            StreamCodec<? super B, T7> codec7,
            Function<C, T7> from7,
            StreamCodec<? super B, T8> codec8,
            Function<C, T8> from8,
            StreamCodec<? super B, T9> codec9,
            Function<C, T9> from9,
            StreamCodec<? super B, T10> codec10,
            Function<C, T10> from10,
            StreamCodec<? super B, T11> codec11,
            Function<C, T11> from11,
            StreamCodec<? super B, T12> codec12,
            Function<C, T12> from12,
            StreamCodec<? super B, T13> codec13,
            Function<C, T13> from13,
            Function13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, C> to
    ) {
        return new StreamCodec<>() {
            @Override
            public C decode(B object) {
                T1 object2 = codec1.decode(object);
                T2 object3 = codec2.decode(object);
                T3 object4 = codec3.decode(object);
                T4 object5 = codec4.decode(object);
                T5 object6 = codec5.decode(object);
                T6 object7 = codec6.decode(object);
                T7 object8 = codec7.decode(object);
                T8 object9 = codec8.decode(object);
                T9 object10 = codec9.decode(object);
                T10 object11 = codec10.decode(object);
                T11 object12 = codec11.decode(object);
                T12 object13 = codec12.decode(object);
                T13 object14 = codec13.decode(object);
                return to.apply(object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13, object14);
            }

            @Override
            public void encode(B object, C object2) {
                codec1.encode(object, from1.apply(object2));
                codec2.encode(object, from2.apply(object2));
                codec3.encode(object, from3.apply(object2));
                codec4.encode(object, from4.apply(object2));
                codec5.encode(object, from5.apply(object2));
                codec6.encode(object, from6.apply(object2));
                codec7.encode(object, from7.apply(object2));
                codec8.encode(object, from8.apply(object2));
                codec9.encode(object, from9.apply(object2));
                codec10.encode(object, from10.apply(object2));
                codec11.encode(object, from11.apply(object2));
                codec12.encode(object, from12.apply(object2));
                codec13.encode(object, from13.apply(object2));
            }
        };
    }

    static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> StreamCodec<B, C> tuple(
            StreamCodec<? super B, T1> codec1,
            Function<C, T1> from1,
            StreamCodec<? super B, T2> codec2,
            Function<C, T2> from2,
            StreamCodec<? super B, T3> codec3,
            Function<C, T3> from3,
            StreamCodec<? super B, T4> codec4,
            Function<C, T4> from4,
            StreamCodec<? super B, T5> codec5,
            Function<C, T5> from5,
            StreamCodec<? super B, T6> codec6,
            Function<C, T6> from6,
            StreamCodec<? super B, T7> codec7,
            Function<C, T7> from7,
            StreamCodec<? super B, T8> codec8,
            Function<C, T8> from8,
            StreamCodec<? super B, T9> codec9,
            Function<C, T9> from9,
            StreamCodec<? super B, T10> codec10,
            Function<C, T10> from10,
            StreamCodec<? super B, T11> codec11,
            Function<C, T11> from11,
            StreamCodec<? super B, T12> codec12,
            Function<C, T12> from12,
            StreamCodec<? super B, T13> codec13,
            Function<C, T13> from13,
            StreamCodec<? super B, T14> codec14,
            Function<C, T14> from14,
            Function14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, C> to
    ) {
        return new StreamCodec<>() {
            @Override
            public C decode(B object) {
                T1 object2 = codec1.decode(object);
                T2 object3 = codec2.decode(object);
                T3 object4 = codec3.decode(object);
                T4 object5 = codec4.decode(object);
                T5 object6 = codec5.decode(object);
                T6 object7 = codec6.decode(object);
                T7 object8 = codec7.decode(object);
                T8 object9 = codec8.decode(object);
                T9 object10 = codec9.decode(object);
                T10 object11 = codec10.decode(object);
                T11 object12 = codec11.decode(object);
                T12 object13 = codec12.decode(object);
                T13 object14 = codec13.decode(object);
                T14 object15 = codec14.decode(object);
                return to.apply(object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13, object14, object15);
            }

            @Override
            public void encode(B object, C object2) {
                codec1.encode(object, from1.apply(object2));
                codec2.encode(object, from2.apply(object2));
                codec3.encode(object, from3.apply(object2));
                codec4.encode(object, from4.apply(object2));
                codec5.encode(object, from5.apply(object2));
                codec6.encode(object, from6.apply(object2));
                codec7.encode(object, from7.apply(object2));
                codec8.encode(object, from8.apply(object2));
                codec9.encode(object, from9.apply(object2));
                codec10.encode(object, from10.apply(object2));
                codec11.encode(object, from11.apply(object2));
                codec12.encode(object, from12.apply(object2));
                codec13.encode(object, from13.apply(object2));
                codec14.encode(object, from14.apply(object2));
            }
        };
    }

    static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> StreamCodec<B, C> tuple(
            StreamCodec<? super B, T1> codec1,
            Function<C, T1> from1,
            StreamCodec<? super B, T2> codec2,
            Function<C, T2> from2,
            StreamCodec<? super B, T3> codec3,
            Function<C, T3> from3,
            StreamCodec<? super B, T4> codec4,
            Function<C, T4> from4,
            StreamCodec<? super B, T5> codec5,
            Function<C, T5> from5,
            StreamCodec<? super B, T6> codec6,
            Function<C, T6> from6,
            StreamCodec<? super B, T7> codec7,
            Function<C, T7> from7,
            StreamCodec<? super B, T8> codec8,
            Function<C, T8> from8,
            StreamCodec<? super B, T9> codec9,
            Function<C, T9> from9,
            StreamCodec<? super B, T10> codec10,
            Function<C, T10> from10,
            StreamCodec<? super B, T11> codec11,
            Function<C, T11> from11,
            StreamCodec<? super B, T12> codec12,
            Function<C, T12> from12,
            StreamCodec<? super B, T13> codec13,
            Function<C, T13> from13,
            StreamCodec<? super B, T14> codec14,
            Function<C, T14> from14,
            StreamCodec<? super B, T15> codec15,
            Function<C, T15> from15,
            Function15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, C> to
    ) {
        return new StreamCodec<>() {
            @Override
            public C decode(B object) {
                T1 object2 = codec1.decode(object);
                T2 object3 = codec2.decode(object);
                T3 object4 = codec3.decode(object);
                T4 object5 = codec4.decode(object);
                T5 object6 = codec5.decode(object);
                T6 object7 = codec6.decode(object);
                T7 object8 = codec7.decode(object);
                T8 object9 = codec8.decode(object);
                T9 object10 = codec9.decode(object);
                T10 object11 = codec10.decode(object);
                T11 object12 = codec11.decode(object);
                T12 object13 = codec12.decode(object);
                T13 object14 = codec13.decode(object);
                T14 object15 = codec14.decode(object);
                T15 object16 = codec15.decode(object);
                return to.apply(object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13, object14, object15, object16);
            }

            @Override
            public void encode(B object, C object2) {
                codec1.encode(object, from1.apply(object2));
                codec2.encode(object, from2.apply(object2));
                codec3.encode(object, from3.apply(object2));
                codec4.encode(object, from4.apply(object2));
                codec5.encode(object, from5.apply(object2));
                codec6.encode(object, from6.apply(object2));
                codec7.encode(object, from7.apply(object2));
                codec8.encode(object, from8.apply(object2));
                codec9.encode(object, from9.apply(object2));
                codec10.encode(object, from10.apply(object2));
                codec11.encode(object, from11.apply(object2));
                codec12.encode(object, from12.apply(object2));
                codec13.encode(object, from13.apply(object2));
                codec14.encode(object, from14.apply(object2));
                codec15.encode(object, from15.apply(object2));
            }
        };
    }

    static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> StreamCodec<B, C> tuple(
            StreamCodec<? super B, T1> codec1,
            Function<C, T1> from1,
            StreamCodec<? super B, T2> codec2,
            Function<C, T2> from2,
            StreamCodec<? super B, T3> codec3,
            Function<C, T3> from3,
            StreamCodec<? super B, T4> codec4,
            Function<C, T4> from4,
            StreamCodec<? super B, T5> codec5,
            Function<C, T5> from5,
            StreamCodec<? super B, T6> codec6,
            Function<C, T6> from6,
            StreamCodec<? super B, T7> codec7,
            Function<C, T7> from7,
            StreamCodec<? super B, T8> codec8,
            Function<C, T8> from8,
            StreamCodec<? super B, T9> codec9,
            Function<C, T9> from9,
            StreamCodec<? super B, T10> codec10,
            Function<C, T10> from10,
            StreamCodec<? super B, T11> codec11,
            Function<C, T11> from11,
            StreamCodec<? super B, T12> codec12,
            Function<C, T12> from12,
            StreamCodec<? super B, T13> codec13,
            Function<C, T13> from13,
            StreamCodec<? super B, T14> codec14,
            Function<C, T14> from14,
            StreamCodec<? super B, T15> codec15,
            Function<C, T15> from15,
            StreamCodec<? super B, T16> codec16,
            Function<C, T16> from16,
            Function16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, C> to
    ) {
        return new StreamCodec<>() {
            @Override
            public C decode(B object) {
                T1 object2 = codec1.decode(object);
                T2 object3 = codec2.decode(object);
                T3 object4 = codec3.decode(object);
                T4 object5 = codec4.decode(object);
                T5 object6 = codec5.decode(object);
                T6 object7 = codec6.decode(object);
                T7 object8 = codec7.decode(object);
                T8 object9 = codec8.decode(object);
                T9 object10 = codec9.decode(object);
                T10 object11 = codec10.decode(object);
                T11 object12 = codec11.decode(object);
                T12 object13 = codec12.decode(object);
                T13 object14 = codec13.decode(object);
                T14 object15 = codec14.decode(object);
                T15 object16 = codec15.decode(object);
                T16 object17 = codec16.decode(object);
                return to.apply(object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13, object14, object15, object16, object17);
            }

            @Override
            public void encode(B object, C object2) {
                codec1.encode(object, from1.apply(object2));
                codec2.encode(object, from2.apply(object2));
                codec3.encode(object, from3.apply(object2));
                codec4.encode(object, from4.apply(object2));
                codec5.encode(object, from5.apply(object2));
                codec6.encode(object, from6.apply(object2));
                codec7.encode(object, from7.apply(object2));
                codec8.encode(object, from8.apply(object2));
                codec9.encode(object, from9.apply(object2));
                codec10.encode(object, from10.apply(object2));
                codec11.encode(object, from11.apply(object2));
                codec12.encode(object, from12.apply(object2));
                codec13.encode(object, from13.apply(object2));
                codec14.encode(object, from14.apply(object2));
                codec15.encode(object, from15.apply(object2));
                codec16.encode(object, from16.apply(object2));
            }
        };
    }

    // you really need to know what you are doing
    // this should be unlimited in theory, but there are always realistic constraints
    @DangerousAndOrUnstable
    static <B, C> StreamCodec<B, C> unlimitedTuple(FunctionUnlimited<C> operation, PacketCodecAndValueGetterContainer<B, C, ?>... containers) {
        return new StreamCodec<>() {
            @Override
            public C decode(B object) {
                List<Object> objects = new ArrayList<>();
                for (var container : containers) {
                    objects.add(container.decode(object));
                }
                return operation.apply(objects.toArray());
            }

            @Override
            public void encode(B object, C object2) {
                for (var container : containers) {
                    container.encode(object, object2);
                }
            }
        };
    }

    record PacketCodecAndValueGetterContainer<B, C, V>(StreamCodec<? super B, V> packetCodec, Function<C, V> function) {

        public void encode(B object, C object2) {
            this.packetCodec.encode(object, this.function.apply(object2));
        }

        public V decode(B object) {
            return this.packetCodec.decode(object);
        }

        // what is this class name lol
        public ArrayList<PacketCodecAndValueGetterContainer<B, C, V>> createList() {
            ArrayList<PacketCodecAndValueGetterContainer<B, C, V>> list = new ArrayList<>();
            list.add(this);
            return list;
        }
    }
}
