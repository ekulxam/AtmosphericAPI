/*
 * Copyright (c) 21:56 UTC 26 July 2026 - present ekulxam
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
//? if >=1.21.9
import net.minecraft.client.renderer.SubmitNodeCollector;
//? if >=1.21.2
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.LivingEntity;

public interface EmptyModelRenderer
//? if =1.21.1 {
    /*<T extends LivingEntity> { // please excuse this horrible formatting
        void renderWithEntityData(T entity, PoseStack matrices, MultiBufferSource vertices, float tickDelta, int light, int overlay, int color);

*///?} elif =1.21.8 {
    
    /*<S extends LivingEntityRenderState>{
        void renderWithEntityData(S state, PoseStack matrices, MultiBufferSource vertices, int light, int overlay, int color);
*///?} elif >=1.21.9 {
    <S extends LivingEntityRenderState>{
        void renderWithEntityData(S state, PoseStack matrices, SubmitNodeCollector renderQueue, int light, int overlay, int color, int outlineColor);
//?}
}