package survivalblock.atmosphere.atmospheric_api.not_mixin.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;

public interface EmptyModelRenderer<T extends LivingEntity> {

    void renderWithEntityData(T entity, MatrixStack matrices, VertexConsumerProvider vertices, float tickDelta, int light, int overlay, int color);
}
