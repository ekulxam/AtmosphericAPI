package survivalblock.atmosphere.atmospheric_api.not_mixin.item;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;
import survivalblock.atmosphere.atmospheric_api.not_mixin.datafixer.AtmosphericPacketCodecs;

import java.util.Optional;

/**
 * Originally written as the PantsOfUndyingPacket for Enchancement Unbound
 * Used as an S2C payload to tell the client to display a floating item, limit to an activated totem of undying
 * @see net.minecraft.client.render.GameRenderer#floatingItem
 * @see net.minecraft.client.render.GameRenderer#showFloatingItem(ItemStack)
 * @see net.minecraft.client.render.GameRenderer#renderFloatingItem(DrawContext, float)
 */
@SuppressWarnings({"unused", "JavadocReference"})
public record ItemStackOfUndyingS2CPayload(ItemStack stack, int entityId, ParticleEffectHolder particleEffectHolder, SoundEventHolder soundEventHolder) implements CustomPayload {
    public static final CustomPayload.Id<ItemStackOfUndyingS2CPayload> ID = new Id<>(AtmosphericAPI.id("itemstack_of_undying_s2c"));
    public static final PacketCodec<RegistryByteBuf, ItemStackOfUndyingS2CPayload> CODEC = PacketCodec.tuple(
            ItemStack.PACKET_CODEC, (payload) -> payload.stack,
            PacketCodecs.VAR_INT, (payload) -> payload.entityId,
            ParticleEffectHolder.PACKET_CODEC, (payload) -> payload.particleEffectHolder,
            SoundEventHolder.PACKET_CODEC, (payload) -> payload.soundEventHolder,
            ItemStackOfUndyingS2CPayload::new);

    public ItemStackOfUndyingS2CPayload(ItemStack stack, int entityId) {
        this(stack, entityId, ParticleEffectHolder.INSTANCE, SoundEventHolder.INSTANCE);
    }

    public void sendToPlayer(ServerPlayerEntity player) {
        ServerPlayNetworking.send(player, this);
    }

    public void sendGlobal(ServerWorld serverWorld) {
        serverWorld.getChunkManager().sendToNearbyPlayers(serverWorld.getEntityById(this.entityId), ServerPlayNetworking.createS2CPacket(this));
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public record ParticleEffectHolder(boolean shouldEmitParticles, Optional<ParticleEffect> particleEffect, int maxAge) {

        public static final PacketCodec<RegistryByteBuf, ParticleEffectHolder> PACKET_CODEC = PacketCodec.tuple(
                PacketCodecs.BOOL, (holder) -> holder.shouldEmitParticles,
                ParticleTypes.PACKET_CODEC.collect(PacketCodecs::optional), (holder) -> holder.particleEffect,
                PacketCodecs.VAR_INT, (holder) -> holder.maxAge,
                ParticleEffectHolder::new);

        public static final ParticleEffectHolder INSTANCE = new ParticleEffectHolder(false);

        public ParticleEffectHolder(boolean shouldEmitParticles) {
            this(shouldEmitParticles, Optional.empty(), 30);
        }

        public ParticleEffectHolder(ParticleEffect particleEffect) {
            this(particleEffect, 30);
        }

        public ParticleEffectHolder(ParticleEffect particleEffect, int maxAge) {
            this(true, Optional.of(particleEffect), maxAge);
        }

        public ParticleEffect getParticleEffectValue() {
            return this.particleEffect.orElse(ParticleTypes.TOTEM_OF_UNDYING);
        }
    }

    public record SoundEventHolder(boolean shouldPlaySound, Optional<SoundEvent> soundEvent, float volume, float pitch, boolean useDistance) {

        public static final PacketCodec<RegistryByteBuf, SoundEventHolder> PACKET_CODEC = PacketCodec.tuple(
                PacketCodecs.BOOL, (holder) -> holder.shouldPlaySound,
                AtmosphericPacketCodecs.SOUND_EVENT_BY_ID.collect(PacketCodecs::optional), (holder) -> holder.soundEvent, // alternatively, use the PacketCodec in the SoundEvent class
                PacketCodecs.FLOAT, (holder) -> holder.volume,
                PacketCodecs.FLOAT, (holder) -> holder.pitch,
                PacketCodecs.BOOL, (holder) -> holder.useDistance,
                SoundEventHolder::new);

        public static final SoundEventHolder INSTANCE = new SoundEventHolder(false);

        public SoundEventHolder(boolean shouldPlaySound) {
            this(shouldPlaySound, Optional.empty(), 1.0F, 1.0F, false);
        }

        public SoundEventHolder(SoundEvent soundEvent) {
            this(true, Optional.of(soundEvent), 1.0F, 1.0F, false);
        }

        public SoundEvent getSoundEventValue() {
            return this.soundEvent.orElse(SoundEvents.ITEM_TOTEM_USE);
        }
    }
}
