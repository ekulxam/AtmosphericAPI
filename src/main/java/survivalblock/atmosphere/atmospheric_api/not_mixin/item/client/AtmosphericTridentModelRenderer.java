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
//~ if >=1.21.11 'model.TridentModel' -> 'model.object.projectile.TridentModel'
import net.minecraft.client.model.object.projectile.TridentModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
//? if <1.21.9
//import net.minecraft.client.renderer.MultiBufferSource;
//? if >=1.21.9
import net.minecraft.client.renderer.SubmitNodeCollector;
//? if <1.21.9
//import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemDisplayContext;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;
import survivalblock.atmosphere.atmospheric_api.not_mixin.datafixer.AtmosphericCodecs;

import java.util.Set;
import java.util.function.Consumer;

@SuppressWarnings("ClassCanBeRecord")
@Environment(EnvType.CLIENT)
public class AtmosphericTridentModelRenderer implements NoDataSpecialModelRenderer {
    public static final Identifier ID = AtmosphericAPI.id("trident");
    private final Model model;
    private final Identifier texture;

    public AtmosphericTridentModelRenderer(Model model, Identifier texture) {
        this.model = model;
        this.texture = texture;
    }

    @Override
    //~ if >=1.21.9 'render(' -> 'submit('
    public void submit(/*? <26 {*/ /*ItemDisplayContext displayContext, *//*?}*/ PoseStack matrices,/*? >=1.21.9 {*/ SubmitNodeCollector renderQueue /*?} else {*/ /*MultiBufferSource bufferSource *//*?}*/, int packedLight, int packedOverlay, boolean hasFoil/*? >=1.21.9 {*/ , int outlineColor /*?}*/) {
        matrices.pushPose();
        matrices.scale(1.0F, -1.0F, -1.0F);
        //? if <1.21.9 {
        /*VertexConsumer vertexConsumer = ItemRenderer.getFoilBuffer(bufferSource, this.model.renderType(this.texture), false, hasFoil);
        this.model.renderToBuffer(matrices, vertexConsumer, packedLight, packedOverlay);
        *///?} else {
        renderQueue.submitModelPart(this.model.root(), matrices, this.model.renderType(this.texture), packedLight, packedOverlay, null, false, hasFoil, -1, null, outlineColor);
        //?}
        matrices.popPose();
    }

    @Override
    //~ if >=1.21.11 'Set<Vector3f>' -> 'Consumer<Vector3fc>'
    public void getExtents(Consumer<Vector3fc> output) {
        PoseStack matrices = new PoseStack();
        matrices.scale(1.0F, -1.0F, -1.0F);
        this.model.root().getExtentsForGui(matrices, output);
    }

    @Environment(EnvType.CLIENT)
    public record Unbaked(Identifier texture, ModelLayerLocation entityModelLayer) implements SpecialModelRenderer.Unbaked {
        public static final Codec<ModelLayerLocation> MODEL_LAYER_CODEC = AtmosphericCodecs.RCB.tuple(
                Identifier.CODEC.fieldOf("model"), ModelLayerLocation::model,
                Codec.STRING.optionalFieldOf("layer", "main"), ModelLayerLocation::layer,
                ModelLayerLocation::new
        ).codec();
        public static final MapCodec<Unbaked> MAP_CODEC = AtmosphericCodecs.RCB.tuple(
                Identifier.CODEC.fieldOf("texture"), unbaked -> unbaked.texture,
                MODEL_LAYER_CODEC.optionalFieldOf("entityModelLayer", ModelLayers.TRIDENT), unbaked -> unbaked.entityModelLayer,
                Unbaked::new
        );

        public MapCodec<Unbaked> type() {
            return MAP_CODEC;
        }

        public SpecialModelRenderer<?> bake(/*? >=1.21.9 {*/ BakingContext context /*?} else {*/ /*EntityModelSet modelSet *//*?}*/) {
            return new AtmosphericTridentModelRenderer(new TridentModel(/*? >=1.21.9 {*/ context.entityModelSet() /*?} else {*/ /*modelSet *//*?}*/.bakeLayer(this.entityModelLayer)), this.texture);
        }
    }
}
//?}