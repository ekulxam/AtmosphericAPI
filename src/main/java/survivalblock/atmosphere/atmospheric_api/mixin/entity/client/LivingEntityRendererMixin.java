package survivalblock.atmosphere.atmospheric_api.mixin.entity.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.atmospheric_api.not_mixin.entity.client.EmptyModelRenderer;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {
    //? if =1.21.1 {
    /*
    @SuppressWarnings({"rawtypes", "unchecked"})
    @WrapOperation(method = "render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/model/EntityModel;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;III)V"))
    private <T extends LivingEntity> void renderEmpty(EntityModel<T> instance, MatrixStack matrixStack, VertexConsumer vertexConsumer, int light, int overlay, int color, Operation<Void> original, @Local(argsOnly = true) T living, @Local(argsOnly = true)VertexConsumerProvider vertexConsumerProvider, @Local(argsOnly = true, ordinal = 1) float tickDelta) {
        if ((LivingEntityRenderer) (Object) this instanceof EmptyModelRenderer emptyModelRenderer) {
            emptyModelRenderer.renderWithEntityData(living, matrixStack, vertexConsumerProvider, tickDelta, light, overlay, color);
        } else {
            original.call(instance, matrixStack, vertexConsumer, light, overlay, color);
        }
    }
     *///?} elif =1.21.8 {
    @WrapOperation(method = "render(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/model/EntityModel;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;III)V"))
    private <T extends EntityRenderState> void renderEmpty(EntityModel<T> instance, MatrixStack matrixStack, VertexConsumer vertexConsumer, int light, int overlay, int color, Operation<Void> original, @Local(argsOnly = true) LivingEntityRenderState state, @Local(argsOnly = true) VertexConsumerProvider vertexConsumerProvider) {
        if ((LivingEntityRenderer) (Object) this instanceof EmptyModelRenderer emptyModelRenderer) {
            emptyModelRenderer.renderWithEntityData(state, matrixStack, vertexConsumerProvider, light, overlay, color);
        } else {
            original.call(instance, matrixStack, vertexConsumer, light, overlay, color);
        }
    }
    //?}
}
