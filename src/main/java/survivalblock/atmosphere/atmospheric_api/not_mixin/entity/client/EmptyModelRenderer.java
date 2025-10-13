package survivalblock.atmosphere.atmospheric_api.not_mixin.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;

public interface EmptyModelRenderer/*
    <T extends LivingEntity> {

    void renderWithEntityData(T entity, MatrixStack matrices, VertexConsumerProvider vertices, float tickDelta, int light, int overlay, int color);
}

     *///?} elif =1.21.8 {
{
    void renderWithEntityData(LivingEntityRenderState state, MatrixStack matrices, VertexConsumerProvider vertices, int light, int overlay, int color);
}
//?}