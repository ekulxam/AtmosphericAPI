//? if 1.21.1 {
/*package survivalblock.atmosphere.atmospheric_api.not_mixin.item;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;
import survivalblock.atmosphere.atmospheric_api.not_mixin.datafixer.AtmosphericPacketCodecs;

import java.util.Optional;

/^*
 * Originally written as the PantsOfUndyingPacket for Enchancement Unbound
 * Used as an S2C payload to tell the client to display a floating item, limit to an activated totem of undying
 * @see net.minecraft.client.renderer.GameRenderer#floatingItem
 * @see net.minecraft.client.renderer.GameRenderer#displayItemActivation(ItemStack)
 * @see net.minecraft.client.renderer.GameRenderer#renderItemActivationAnimation(GuiGraphics, float)
 * @param stack the ItemStack to be displayed
 * @param entityId the id of the entity
 * @param particleEffectHolder
 * @param soundEventHolder
 ^/
@SuppressWarnings({"unused", "JavadocReference"})
public record ItemStackOfUndyingS2CPayload(ItemStack stack, int entityId, ParticleEffectHolder particleEffectHolder, SoundEventHolder soundEventHolder) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<ItemStackOfUndyingS2CPayload> ID = new Type<>(AtmosphericAPI.id("itemstack_of_undying_s2c"));
    public static final StreamCodec<RegistryFriendlyByteBuf, ItemStackOfUndyingS2CPayload> CODEC = StreamCodec.composite(
            ItemStack.STREAM_CODEC, (payload) -> payload.stack,
            ByteBufCodecs.VAR_INT, (payload) -> payload.entityId,
            ParticleEffectHolder.PACKET_CODEC, (payload) -> payload.particleEffectHolder,
            SoundEventHolder.PACKET_CODEC, (payload) -> payload.soundEventHolder,
            ItemStackOfUndyingS2CPayload::new);

    public ItemStackOfUndyingS2CPayload(ItemStack stack, int entityId) {
        this(stack, entityId, ParticleEffectHolder.INSTANCE, SoundEventHolder.INSTANCE);
    }

    public void sendToPlayer(ServerPlayer player) {
        ServerPlayNetworking.send(player, this);
    }

    public void sendGlobal(ServerLevel serverWorld) {
        serverWorld.getChunkSource().broadcastAndSend(serverWorld.getEntity(this.entityId), ServerPlayNetworking.createS2CPacket(this));
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }

    public record ParticleEffectHolder(boolean shouldEmitParticles, Optional<ParticleOptions> particleEffect, int maxAge) {

        public static final StreamCodec<RegistryFriendlyByteBuf, ParticleEffectHolder> PACKET_CODEC = StreamCodec.composite(
                ByteBufCodecs.BOOL, (holder) -> holder.shouldEmitParticles,
                ParticleTypes.STREAM_CODEC.apply(ByteBufCodecs::optional), (holder) -> holder.particleEffect,
                ByteBufCodecs.VAR_INT, (holder) -> holder.maxAge,
                ParticleEffectHolder::new);

        public static final ParticleEffectHolder INSTANCE = new ParticleEffectHolder(false);

        public ParticleEffectHolder(boolean shouldEmitParticles) {
            this(shouldEmitParticles, Optional.empty(), 30);
        }

        public ParticleEffectHolder(ParticleOptions particleEffect) {
            this(particleEffect, 30);
        }

        public ParticleEffectHolder(ParticleOptions particleEffect, int maxAge) {
            this(true, Optional.of(particleEffect), maxAge);
        }

        public ParticleOptions getParticleEffectValue() {
            return this.particleEffect.orElse(ParticleTypes.TOTEM_OF_UNDYING);
        }
    }

    public record SoundEventHolder(boolean shouldPlaySound, Optional<SoundEvent> soundEvent, float volume, float pitch, boolean useDistance) {

        public static final StreamCodec<RegistryFriendlyByteBuf, SoundEventHolder> PACKET_CODEC = StreamCodec.composite(
                ByteBufCodecs.BOOL, (holder) -> holder.shouldPlaySound,
                SoundEvent.DIRECT_STREAM_CODEC.apply(ByteBufCodecs::optional), (holder) -> holder.soundEvent, // alternatively, use the PacketCodec in the SoundEvent class
                ByteBufCodecs.FLOAT, (holder) -> holder.volume,
                ByteBufCodecs.FLOAT, (holder) -> holder.pitch,
                ByteBufCodecs.BOOL, (holder) -> holder.useDistance,
                SoundEventHolder::new);

        public static final SoundEventHolder INSTANCE = new SoundEventHolder(false);

        public SoundEventHolder(boolean shouldPlaySound) {
            this(shouldPlaySound, Optional.empty(), 1.0F, 1.0F, false);
        }

        public SoundEventHolder(SoundEvent soundEvent) {
            this(true, Optional.of(soundEvent), 1.0F, 1.0F, false);
        }

        public SoundEvent getSoundEventValue() {
            return this.soundEvent.orElse(SoundEvents.TOTEM_USE);
        }
    }
}
*///?}