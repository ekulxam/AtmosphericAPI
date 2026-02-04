/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.mixin.damage_type;

import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;
import survivalblock.atmosphere.atmospheric_api.not_mixin.damage_type.AtmosphericDamageTypeGamerules;
import survivalblock.atmosphere.atmospheric_api.not_mixin.funny.ThisIsABadIdea;

@ApiStatus.Internal
@ThisIsABadIdea(ThisIsABadIdea.LevelsOfHorrendousness.PROBABLY)
@Mixin(value = AtmosphericAPI.class, remap = false)
public class AtmosphericAPIMixin {

    @Inject(method = "onInitialize", at = @At("RETURN"))
    private void registerDamageTypes(CallbackInfo ci) {
        AtmosphericDamageTypeGamerules.init();
    }
}
