package survivalblock.atmosphere.atmospheric_api.not_mixin.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
/*? =1.21.1 {*/  /*?} else {*/ import net.minecraft.client.render.entity.state.LivingEntityRenderState; /*?}*/
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;

public interface EmptyModelRenderer
//? if =1.21.1 {
    /*<T extends LivingEntity> { // please excuse this horrible formatting
        void renderWithEntityData(T entity, MatrixStack matrices, VertexConsumerProvider vertices, float tickDelta, int light, int overlay, int color);

*///?} elif =1.21.8 {
    
    <S extends LivingEntityRenderState>{
        void renderWithEntityData(S state, MatrixStack matrices, VertexConsumerProvider vertices, int light, int overlay, int color);
//?}
}