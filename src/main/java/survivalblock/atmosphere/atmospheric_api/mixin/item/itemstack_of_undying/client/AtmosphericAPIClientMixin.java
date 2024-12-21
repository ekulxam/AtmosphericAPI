package survivalblock.atmosphere.atmospheric_api.mixin.item.itemstack_of_undying.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPIClient;
import survivalblock.atmosphere.atmospheric_api.not_mixin.funny.ThisIsABadIdea;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.ItemStackOfUndyingS2CPayload;

import java.util.Objects;

@ApiStatus.Internal
@ThisIsABadIdea(ThisIsABadIdea.LevelsOfHorrendousness.PROBABLY)
@Environment(EnvType.CLIENT)
@Mixin(value = AtmosphericAPIClient.class, remap = false)
public class AtmosphericAPIClientMixin {

    @Inject(method = "onInitializeClient", at = @At("HEAD"))
    private void tickActiveScreenShaker(CallbackInfo ci) {
        ClientPlayNetworking.registerGlobalReceiver(ItemStackOfUndyingS2CPayload.ID, (payload, context) -> {
            ItemStack stack = payload.stack();
            MinecraftClient client = Objects.requireNonNull(context.client());
            PlayerEntity player = context.player();
            client.execute(() -> {
                ItemStackOfUndyingS2CPayload.ParticleEffectHolder particleEffectHolder = payload.particleEffectHolder();
                if (particleEffectHolder.shouldEmitParticles()) {
                    client.particleManager.addEmitter(player,
                            particleEffectHolder.getParticleEffectValue(),
                            particleEffectHolder.maxAge());
                }
                ItemStackOfUndyingS2CPayload.SoundEventHolder soundEventHolder = payload.soundEventHolder();
                if (soundEventHolder.shouldPlaySound()) {
                    Objects.requireNonNull(client.world).playSound(player.getX(),
                            player.getY(),
                            player.getZ(),
                            soundEventHolder.getSoundEventValue(),
                            player.getSoundCategory(),
                            soundEventHolder.volume(),
                            soundEventHolder.pitch(),
                            soundEventHolder.useDistance());
                }
                client.gameRenderer.showFloatingItem(stack);
            });
        });
    }
}
