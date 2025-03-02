package survivalblock.atmosphere.atmospheric_api.mixin.item.itemstack_of_undying.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
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
    private void handleItemStackOfUndyingS2CPayloadReceiving(CallbackInfo ci) {
        ClientPlayNetworking.registerGlobalReceiver(ItemStackOfUndyingS2CPayload.ID, (payload, context) -> {
            MinecraftClient client = Objects.requireNonNull(context.client());
            client.execute(() -> {
                ClientWorld world = client.world;
                if (world == null) {
                    return;
                }
                Entity entity = world.getEntityById(payload.entityId());
                if (entity == null) {
                    return;
                }
                ItemStackOfUndyingS2CPayload.ParticleEffectHolder particleEffectHolder = payload.particleEffectHolder();
                if (particleEffectHolder.shouldEmitParticles()) {
                    client.particleManager.addEmitter(entity,
                            particleEffectHolder.getParticleEffectValue(),
                            particleEffectHolder.maxAge());
                }
                ItemStackOfUndyingS2CPayload.SoundEventHolder soundEventHolder = payload.soundEventHolder();
                if (soundEventHolder.shouldPlaySound()) {
                    world.playSound(entity.getX(),
                            entity.getY(),
                            entity.getZ(),
                            soundEventHolder.getSoundEventValue(),
                            entity.getSoundCategory(),
                            soundEventHolder.volume(),
                            soundEventHolder.pitch(),
                            soundEventHolder.useDistance());
                }
                if (entity == client.player) {
                    client.gameRenderer.showFloatingItem(payload.stack());
                }
            });
        });
    }
}
