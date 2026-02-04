/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
//? if >=1.21.9
/*import net.minecraft.client.renderer.SubmitNodeCollector;*/
//? if >=1.21.2
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.LivingEntity;

public interface EmptyModelRenderer
//? if =1.21.1 {
    /*<T extends LivingEntity> { // please excuse this horrible formatting
        void renderWithEntityData(T entity, PoseStack matrices, MultiBufferSource vertices, float tickDelta, int light, int overlay, int color);

*///?} elif =1.21.8 {
    
    <S extends LivingEntityRenderState>{
        void renderWithEntityData(S state, PoseStack matrices, MultiBufferSource vertices, int light, int overlay, int color);
//?} elif >=1.21.9 {
    /*<S extends LivingEntityRenderState>{
        void renderWithEntityData(S state, PoseStack matrices, SubmitNodeCollector renderQueue, int light, int overlay, int color, int outlineColor);
*///?}
}