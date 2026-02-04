/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
//? if >=1.21.4 {
package survivalblock.atmosphere.atmospheric_api.not_mixin.item.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.TridentModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
//? if <1.21.9
import net.minecraft.client.renderer.MultiBufferSource;
//? if >=1.21.9
/*import net.minecraft.client.renderer.SubmitNodeCollector;*/
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import org.joml.Vector3f;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;

import java.util.Set;

@SuppressWarnings("ClassCanBeRecord")
@Environment(EnvType.CLIENT)
public class AtmosphericTridentModelRenderer implements NoDataSpecialModelRenderer {
    public static final ResourceLocation ID = AtmosphericAPI.id("trident");
    private final Model model;
    private final ResourceLocation texture;

    public AtmosphericTridentModelRenderer(Model model, ResourceLocation texture) {
        this.model = model;
        this.texture = texture;
    }

    @Override
    public void /*? >=1.21.9 {*/ /*submit *//*?} else {*/ render /*?}*/(ItemDisplayContext displayContext, PoseStack matrices,/*? >=1.21.9 {*/ /*SubmitNodeCollector renderQueue *//*?} else {*/ MultiBufferSource bufferSource /*?}*/, int packedLight, int packedOverlay, boolean hasFoil/*? >=1.21.9 {*/ /*, int outlineColor *//*?}*/) {
        matrices.pushPose();
        matrices.scale(1.0F, -1.0F, -1.0F);
        //? if <1.21.9 {
        VertexConsumer vertexConsumer = ItemRenderer.getFoilBuffer(bufferSource, this.model.renderType(this.texture), false, hasFoil);
        this.model.renderToBuffer(matrices, vertexConsumer, packedLight, packedOverlay);
        //?} else {
        /*renderQueue.submitModelPart(this.model.root(), matrices, this.model.renderType(this.texture), packedLight, packedOverlay, null, false, hasFoil, -1, null, outlineColor);
        *///?}
        matrices.popPose();
    }

    @Override
    public void getExtents(Set<Vector3f> output) {
        PoseStack matrices = new PoseStack();
        matrices.scale(1.0F, -1.0F, -1.0F);
        this.model.root().getExtentsForGui(matrices, output);
    }

    @Environment(EnvType.CLIENT)
    public record Unbaked(ResourceLocation texture, ModelLayerLocation entityModelLayer) implements SpecialModelRenderer.Unbaked {
        public static final Codec<ModelLayerLocation> MODEL_LAYER_CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                        ResourceLocation.CODEC.fieldOf("model").forGetter(ModelLayerLocation::model),
                        Codec.STRING.optionalFieldOf("layer", "main").forGetter(ModelLayerLocation::layer)
                    )
                    .apply(instance, ModelLayerLocation::new)
        );
        public static final MapCodec<Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                        ResourceLocation.CODEC.fieldOf("texture").forGetter(unbaked -> unbaked.texture),
						MODEL_LAYER_CODEC.optionalFieldOf("entityModelLayer", ModelLayers.TRIDENT).forGetter(unbaked -> unbaked.entityModelLayer)
					)
                    .apply(instance, Unbaked::new)
        );

        public MapCodec<Unbaked> type() {
            return MAP_CODEC;
        }

        public SpecialModelRenderer<?> bake(/*? >=1.21.9 {*/ /*BakingContext context *//*?} else {*/ EntityModelSet modelSet /*?}*/) {
            return new AtmosphericTridentModelRenderer(new TridentModel(/*? >=1.21.9 {*/ /*context.entityModelSet() *//*?} else {*/ modelSet /*?}*/.bakeLayer(this.entityModelLayer)), this.texture);
        }
    }
}
//?}