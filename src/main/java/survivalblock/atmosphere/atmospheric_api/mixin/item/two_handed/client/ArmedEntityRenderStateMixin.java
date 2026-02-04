/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
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
    private static void updateHandStacks(LivingEntity entity, ArmedEntityRenderState state, ItemModelResolver itemModelResolver, CallbackInfo ci) {
        state.setData(AtmosphericSpecialItemRenderHandlerImpl.MAINHAND_STACK_KEY, entity.getMainHandItem());
        state.setData(AtmosphericSpecialItemRenderHandlerImpl.OFFHAND_STACK_KEY, entity.getOffhandItem());
    }
}
//?}