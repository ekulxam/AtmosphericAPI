/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.datafixer;

import com.mojang.datafixers.kinds.App;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.codec.StreamCodec;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

@SuppressWarnings("unused")
public class Extensions {

    public static final Logger LOGGER = AtmosphericAPI.LOGGER;

    public static <B, C, T1> StreamCodec<B, C> extend(StreamCodec<B, C> original, StreamCodec<B, T1> addon, Function<C, @Nullable T1> from, BiConsumer<C, T1> to) {
        return new StreamCodec<>() {
            @Override
            public C decode(B buf) {
                C c = original.decode(buf);
                try {
                    T1 object = addon.decode(buf);
                    to.accept(c, object);
                } catch (Throwable throwable) {
                    // implement error handling
                }
                return c;
            }

            @Override
            public void encode(B buf, C value) {
                original.encode(buf, value);
                T1 object = from.apply(value);
                if (object != null) {
                    addon.encode(buf, object);
                }
            }
        };
    }

    public static <V, T1> Codec<V> extend(Function<RecordCodecBuilder.Instance<V>, ? extends App<RecordCodecBuilder.Mu<V>, V>> group, RecordCodecBuilder<V, T1> addon, BiFunction<V, T1, V> to) {
        return extend(convertForExtension(group), addon, to);
    }

    public static <V, T1> Codec<V> extend(Codec<V> codec, RecordCodecBuilder<V, T1> addon, BiFunction<V, T1, V> to) {
        return extend(convertForExtension(codec), addon, to);
    }

    public static <V, T1> Codec<V> extend(MapCodec<V> group, RecordCodecBuilder<V, T1> addon, BiFunction<V, T1, V> to) {
        return RecordCodecBuilder.create(instance -> instance.group(
                convertForExtension(group),
                addon
        ).apply(instance, to));
    }

    public static <V> MapCodec<V> convertForExtension(Function<RecordCodecBuilder.Instance<V>, ? extends App<RecordCodecBuilder.Mu<V>, V>> group) {
        return RecordCodecBuilder.mapCodec(group);
    }

    public static <V> MapCodec<V> convertForExtension(Codec<V> codec) {
        if (codec instanceof MapCodec.MapCodecCodec<V>(MapCodec<V> mapCodec)) {
            return mapCodec;
        }
        return MapCodec.assumeMapUnsafe(codec);
    }

    public static <V> RecordCodecBuilder<V, V> convertForExtension(MapCodec<V> group) {
        return group.forGetter(v -> v);
    }
/*
    public static <T> void test(T value, Codec<T> original, Codec<T> codec) {

        List<Codec<T>> codecs = List.of(original, codec);
        String name = value.getClass().getSimpleName();

        for (Codec<T> codec1 : codecs) {
            DataResult<JsonElement> result = codec1.encodeStart(JsonOps.INSTANCE, value);
            JsonElement json = result.resultOrPartial(LOGGER::error).orElseThrow();
            LOGGER.info("Serialized {}: {}", name, json);
            for (Codec<T> codec2 : codecs) {
                DataResult<T> newResult = codec2.parse(JsonOps.INSTANCE, json);
                T newPos = newResult.resultOrPartial(LOGGER::error).orElseThrow();
                LOGGER.info("Deserialized {}: {}", name, newPos);
            }
        }
    }

    public static <T> void test(T value, Codec<T> original, Function<T, Integer> memory) {
        test(value, original, extend(original, Codec.INT.optionalFieldOf("testInt", 123).forGetter(memory), (t, integer) -> {
            LOGGER.info("It worked maybe? Integer is {}", integer);
            return t;
        }));
    }

    public static void load() {
        test(new BannerPattern(ResourceLocation.ofVanilla("test"), "minecraft"), BannerPattern.CODEC, b -> 3);
        test(new StatusEffectInstance(StatusEffects.POISON, 10, 0), StatusEffectInstance.CODEC, StatusEffectInstance::getDuration);
    }

 */
}
