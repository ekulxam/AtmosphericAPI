package survivalblock.atmosphere.atmospheric_api.not_mixin.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.LivingEntity;

public interface EmptyModelRenderer
//? if =1.21.1 {
    <T extends LivingEntity> { // please excuse this horrible formatting
        void renderWithEntityData(T entity, PoseStack matrices, MultiBufferSource vertices, float tickDelta, int light, int overlay, int color);

//?} elif =1.21.8 {
    
    /*<S extends LivingEntityRenderState>{
        void renderWithEntityData(S state, MatrixStack matrices, VertexConsumerProvider vertices, int light, int overlay, int color);
*///?}
}