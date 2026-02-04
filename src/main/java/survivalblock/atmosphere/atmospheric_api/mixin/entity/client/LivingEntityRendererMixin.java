/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.mixin.entity.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
//? if >=1.21.9
/*import net.minecraft.client.renderer.SubmitNodeCollector;*/
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
//? if >=1.21.2
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
//? if >=1.21.9
/*import net.minecraft.client.renderer.feature.ModelFeatureRenderer;*/
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.atmospheric_api.not_mixin.entity.client.EmptyModelRenderer;

@SuppressWarnings({"rawtypes", "unchecked"})
@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {
    //? if =1.21.1 {
    
    /*@WrapOperation(method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/EntityModel;renderToBuffer(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;III)V"))
    private <T extends LivingEntity> void renderEmpty(EntityModel<T> instance, PoseStack matrixStack, VertexConsumer vertexConsumer, int light, int overlay, int color, Operation<Void> original, @Local(argsOnly = true) T living, @Local(argsOnly = true)MultiBufferSource vertexConsumerProvider, @Local(argsOnly = true, ordinal = 1) float tickDelta) {
        if ((LivingEntityRenderer) (Object) this instanceof EmptyModelRenderer emptyModelRenderer) {
            emptyModelRenderer.renderWithEntityData(living, matrixStack, vertexConsumerProvider, tickDelta, light, overlay, color);
        } else {
            original.call(instance, matrixStack, vertexConsumer, light, overlay, color);
        }
    }
     *///?} elif =1.21.8 {
    @WrapOperation(method = "render(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/EntityModel;renderToBuffer(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;III)V"))
    private <T extends LivingEntityRenderState> void renderEmpty(EntityModel<T> instance, PoseStack matrixStack, VertexConsumer vertexConsumer, int light, int overlay, int color, Operation<Void> original, @Local(argsOnly = true) T state, @Local(argsOnly = true) MultiBufferSource vertexConsumerProvider) {
        if ((LivingEntityRenderer) (Object) this instanceof EmptyModelRenderer emptyModelRenderer) {
            emptyModelRenderer.renderWithEntityData(state, matrixStack, vertexConsumerProvider, light, overlay, color);
        } else {
            original.call(instance, matrixStack, vertexConsumer, light, overlay, color);
        }
    }
    //?} elif >=1.21.9 {
    /*@WrapOperation(method = "submit(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/CameraRenderState;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/SubmitNodeCollector;submitModel(Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/RenderType;IIILnet/minecraft/client/renderer/texture/TextureAtlasSprite;ILnet/minecraft/client/renderer/feature/ModelFeatureRenderer$CrumblingOverlay;)V"))
    private <T extends LivingEntityRenderState> void renderEmpty(SubmitNodeCollector instance, Model model, Object o, PoseStack matrixStack, RenderType renderType, int light, int overlay, int color, TextureAtlasSprite textureAtlasSprite, int outlineColor, ModelFeatureRenderer.CrumblingOverlay crumblingOverlay, Operation<Void> original, @Local(argsOnly = true) T state) {
        if ((LivingEntityRenderer) (Object) this instanceof EmptyModelRenderer emptyModelRenderer) {
            emptyModelRenderer.renderWithEntityData(state, matrixStack, instance, light, overlay, color, outlineColor);
        } else {
            original.call(instance, model, o, matrixStack, renderType, light, overlay, color, textureAtlasSprite, outlineColor, crumblingOverlay);
        }
    }
    *///?}
}