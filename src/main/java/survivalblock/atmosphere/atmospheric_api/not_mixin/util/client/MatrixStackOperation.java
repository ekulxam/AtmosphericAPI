package survivalblock.atmosphere.atmospheric_api.not_mixin.util.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mojang.serialization.Codec;
import survivalblock.atmosphere.atmospheric_api.not_mixin.datafixer.AtmosphericCodecs;

import java.util.function.Consumer;

public abstract class MatrixStackOperation implements Consumer<PoseStack> {

    @Override
    public abstract void accept(PoseStack matrices);

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
        public static final Codec<Translate> CODEC = AtmosphericCodecs.RCB.tuple(
                Codec.FLOAT.fieldOf("x"), translate -> translate.x,
                Codec.FLOAT.fieldOf("y"), translate -> translate.y,
                Codec.FLOAT.fieldOf("z"), translate -> translate.z,
                Translate::new
        ).codec();

        public Translate(float x, float y, float z) {
            super(x, y, z);
        }

        @Override
        public void accept(PoseStack matrices) {
            matrices.translate(this.x, this.y, this.z);
        }
    }

    public static class Scale extends XYZ {
        public static final Codec<Scale> CODEC = AtmosphericCodecs.RCB.tuple(
                Codec.FLOAT.fieldOf("x"), scale -> scale.x,
                Codec.FLOAT.fieldOf("y"), scale -> scale.y,
                Codec.FLOAT.fieldOf("z"), scale -> scale.z,
                Scale::new
        ).codec();

        public Scale(float x, float y, float z) {
            super(x, y, z);
        }

        @Override
        public void accept(PoseStack matrices) {
            matrices.scale(this.x, this.y, this.z);
        }
    }

    public static class Multiply extends MatrixStackOperation {
        public static final Codec<Multiply> CODEC = AtmosphericCodecs.RCB.tuple(
                IdentifiableAxis.CODEC.flatComapMap(identifiableAxis -> identifiableAxis.axis, IdentifiableAxis::fromAxis).fieldOf("axis"), multiply -> multiply.axis,
                Codec.FLOAT.fieldOf("degrees"), multiply -> multiply.degrees,
                Multiply::new
        ).codec();

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
    }

    public static class Push extends MatrixStackOperation {
        public static final Push INSTANCE = new Push();
        public static final Codec<Push> CODEC = Codec.unit(INSTANCE);

        @Override
        public void accept(PoseStack matrices) {
            matrices.pushPose();
        }
    }

    public static class Pop extends MatrixStackOperation {
        public static final Pop INSTANCE = new Pop();
        public static final Codec<Pop> CODEC = Codec.unit(INSTANCE);

        @Override
        public void accept(PoseStack matrices) {
            matrices.popPose();
        }
    }
}
