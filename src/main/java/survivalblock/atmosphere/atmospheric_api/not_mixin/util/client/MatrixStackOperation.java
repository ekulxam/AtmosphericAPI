/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.util.client;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.ApiStatus;
import survivalblock.atmosphere.atmospheric_api.not_mixin.datafixer.AtmosphericCodecs;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@SuppressWarnings("unused")
public abstract class MatrixStackOperation implements Consumer<PoseStack> {
    private static final BiMap<Identifier, MapCodec<? extends MatrixStackOperation>> REGISTRY = HashBiMap.create(5);
    private static final Codec<MapCodec<? extends MatrixStackOperation>> NESTED_CODEC = Identifier.CODEC
            .flatXmap(
                    id -> {
                        MapCodec<? extends MatrixStackOperation> codec = REGISTRY.get(id);
                        if (codec == null) {
                            return DataResult.error(() -> "No such operation exists!");
                        }
                        return DataResult.success(codec);
                        },
                    codec -> {
                        var inverse = REGISTRY.inverse();
                        if (inverse.containsKey(codec)) {
                            return DataResult.success(inverse.get(codec));
                        }
                        return DataResult.error(() -> "The operation with codec " + codec + " was not registered!");
                    });
    // why do I need to separate these (I love generics)
    public static final Codec<MatrixStackOperation> CODEC = NESTED_CODEC.dispatch(MatrixStackOperation::getCodec, codec -> codec);
    public static final Codec<List<MatrixStackOperation>> LIST_CODEC = CODEC.listOf();
    public static final Codec<List<MatrixStackOperation>> SAFE_LIST_CODEC = constructSafeListCodec(16);

    public static Codec<List<MatrixStackOperation>> constructSafeListCodec(int pushPopLimit) {
        return LIST_CODEC.validate(
                list -> {
                    int pushes = 0;
                    int pops = 0;
                    for (var operation : list) {
                        if (operation instanceof Push) {
                            pushes++;
                        } else if (operation instanceof Pop) {
                            pops++;
                        }

                        if (pops > pushes) {
                            String aboveAllowed = "Popped too many times (pushes was " + pushes + ", while pops was " + pops + ")!";
                            return DataResult.error(() -> aboveAllowed);
                        }

                        if (pushes > pushPopLimit) {
                            String tooManyPushes = "Too many push calls were found (" + pushes + " > " + pushPopLimit + ")!";
                            return DataResult.error(() -> tooManyPushes);
                        } else if (pops > pushPopLimit) {
                            String tooManyPops = "Too many pop calls were found (" + pops + " > " + pushPopLimit + ")!";
                            return DataResult.error(() -> tooManyPops);
                        }
                    }

                    if (pushes == pops) {
                        return DataResult.success(list);
                    }

                    String somehowYouFailed = "You must have an equal number of push and pop calls (pushes was " + pushes + " and pops was " + pops + ")!";

                    return DataResult.error(() -> somehowYouFailed);
                }
        );
    }

    @ApiStatus.Internal
    public static void register(String name, MapCodec<? extends MatrixStackOperation> codec) {
        register(Identifier.withDefaultNamespace(name), codec);
    }

    public static void register(Identifier id, MapCodec<? extends MatrixStackOperation> codec) {
        REGISTRY.put(id, codec);
    }

    @Override
    public abstract void accept(PoseStack matrices);

    public abstract MapCodec<? extends MatrixStackOperation> getCodec();

    public abstract static class XYZ extends MatrixStackOperation {
        protected final float x;
        protected final float y;
        protected final float z;

        public XYZ(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static class Translate extends XYZ {
        public static final MapCodec<Translate> CODEC = AtmosphericCodecs.RCB.tuple(
                Codec.FLOAT.fieldOf("x"), translate -> translate.x,
                Codec.FLOAT.fieldOf("y"), translate -> translate.y,
                Codec.FLOAT.fieldOf("z"), translate -> translate.z,
                Translate::new
        );

        public Translate(float x, float y, float z) {
            super(x, y, z);
        }

        @Override
        public void accept(PoseStack matrices) {
            matrices.translate(this.x, this.y, this.z);
        }

        @Override
        public MapCodec<? extends MatrixStackOperation> getCodec() {
            return CODEC;
        }

        static {
            register("translate", CODEC);
        }
    }

    public static class Scale extends XYZ {
        public static final MapCodec<Scale> CODEC = AtmosphericCodecs.RCB.tuple(
                Codec.FLOAT.fieldOf("x"), scale -> scale.x,
                Codec.FLOAT.fieldOf("y"), scale -> scale.y,
                Codec.FLOAT.fieldOf("z"), scale -> scale.z,
                Scale::new
        );

        public Scale(float x, float y, float z) {
            super(x, y, z);
        }

        @Override
        public void accept(PoseStack matrices) {
            matrices.scale(this.x, this.y, this.z);
        }

        @Override
        public MapCodec<? extends MatrixStackOperation> getCodec() {
            return CODEC;
        }

        static {
            register("scale", CODEC);
        }
    }

    public static class Multiply extends MatrixStackOperation {
        public static final MapCodec<Multiply> CODEC = AtmosphericCodecs.RCB.tuple(
                IdentifiableAxis.CODEC.flatComapMap(identifiableAxis -> identifiableAxis.axis, IdentifiableAxis::fromAxis).fieldOf("axis"), multiply -> multiply.axis,
                Codec.FLOAT.fieldOf("degrees"), multiply -> multiply.degrees,
                Multiply::new
        );

        protected final Axis axis;
        protected final float degrees;

        public Multiply(Axis axis, float degrees) {
            this.axis = axis;
            this.degrees = degrees;
        }

        @Override
        public void accept(PoseStack matrices) {
            matrices.mulPose(this.axis.rotationDegrees(this.degrees));
        }

        @Override
        public MapCodec<? extends MatrixStackOperation> getCodec() {
            return CODEC;
        }

        static {
            register("multiply", CODEC);
        }
    }

    public static class Push extends MatrixStackOperation {
        public static final Push INSTANCE = new Push();
        public static final MapCodec<Push> CODEC = MapCodec.unit(INSTANCE);

        @Override
        public void accept(PoseStack matrices) {
            matrices.pushPose();
        }

        @Override
        public MapCodec<? extends MatrixStackOperation> getCodec() {
            return CODEC;
        }

        static {
            register("push", CODEC);
        }
    }

    public static class Pop extends MatrixStackOperation {
        public static final Pop INSTANCE = new Pop();
        public static final MapCodec<Pop> CODEC = MapCodec.unit(INSTANCE);

        @Override
        public void accept(PoseStack matrices) {
            matrices.popPose();
        }

        @Override
        public MapCodec<? extends MatrixStackOperation> getCodec() {
            return CODEC;
        }

        static {
            register("pop", CODEC);
        }
    }
}
