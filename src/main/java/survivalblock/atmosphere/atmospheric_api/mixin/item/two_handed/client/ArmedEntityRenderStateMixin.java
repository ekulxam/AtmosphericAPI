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
//? if >=1.21.8 {
package survivalblock.atmosphere.atmospheric_api.mixin.item.two_handed.client;

import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.client.AtmosphericSpecialItemRenderHandlerImpl;

@SuppressWarnings("UnusedMixin")
@Mixin(ArmedEntityRenderState.class)
public class ArmedEntityRenderStateMixin {

    @Inject(method = "extractArmedEntityRenderState", at = @At(value = "HEAD"))
    private static void updateHandStacks(LivingEntity entity, ArmedEntityRenderState state, ItemModelResolver itemModelResolver, /*? >=1.21.11 {*/ float tickProgress, /*?}*/ CallbackInfo ci) {
        state.setData(AtmosphericSpecialItemRenderHandlerImpl.MAINHAND_STACK_KEY, entity.getMainHandItem());
        state.setData(AtmosphericSpecialItemRenderHandlerImpl.OFFHAND_STACK_KEY, entity.getOffhandItem());
    }
}
//?}