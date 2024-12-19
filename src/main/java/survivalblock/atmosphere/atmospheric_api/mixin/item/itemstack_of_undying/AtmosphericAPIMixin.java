package survivalblock.atmosphere.atmospheric_api.mixin.item.itemstack_of_undying;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;
import survivalblock.atmosphere.atmospheric_api.not_mixin.funny.ThisIsABadIdea;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.ItemStackOfUndyingS2CPayload;

@ApiStatus.Internal
@ThisIsABadIdea(ThisIsABadIdea.LevelsOfHorrendousness.PROBABLY)
@Mixin(value = AtmosphericAPI.class, remap = false)
public class AtmosphericAPIMixin {

    @Inject(method = "onInitialize", at = @At("RETURN"))
    private void registerScreenShakePayload(CallbackInfo ci) {
        PayloadTypeRegistry.playS2C().register(ItemStackOfUndyingS2CPayload.ID, ItemStackOfUndyingS2CPayload.CODEC);
    }
}
