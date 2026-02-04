/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
//? if >=1.21.4 {
package survivalblock.atmosphere.atmospheric_api.mixin.item.trident.client;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.client.AtmosphericTridentModelRenderer;

@Mixin(SpecialModelRenderers.class)
public class SpecialModelTypesMixin {
    @Shadow
    @Final
    public static ExtraCodecs.LateBoundIdMapper<ResourceLocation, MapCodec<? extends SpecialModelRenderer.Unbaked>> ID_MAPPER;

    @Inject(method = "bootstrap", at = @At("RETURN"))
    private static void addAtmosphericTrident(CallbackInfo ci) {
        ID_MAPPER.put(AtmosphericTridentModelRenderer.ID, AtmosphericTridentModelRenderer.Unbaked.MAP_CODEC);
    }
}
//?}