package survivalblock.atmosphere.atmospheric_api.not_mixin.item;

import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;

import java.util.Optional;

@SuppressWarnings({"unused", "OptionalUsedAsFieldOrParameterType"})
public class ItemStackOfUndyingS2CPayload implements CustomPayload {

    private final ItemStack stack;
    private final boolean shouldEmitParticles;
    private final Optional<ParticleEffect> particleEffect;
    private final boolean shouldPlaySound;
    private final Optional<SoundEvent> soundEvent;

    public static final CustomPayload.Id<ItemStackOfUndyingS2CPayload> ID = new Id<>(AtmosphericAPI.id("itemstack_of_undying_s2c"));
    public static final PacketCodec<RegistryByteBuf, ItemStackOfUndyingS2CPayload> CODEC = PacketCodec.tuple(
            ItemStack.PACKET_CODEC, (payload) -> payload.stack,
            PacketCodecs.BOOL, (payload) -> payload.shouldEmitParticles,
            ParticleTypes.PACKET_CODEC.collect(PacketCodecs::optional), (payload) -> payload.particleEffect,
            PacketCodecs.BOOL, (payload) -> payload.shouldPlaySound,
            PacketCodecs.SOUND_EVENT.collect(PacketCodecs::optional), (payload) -> payload.soundEvent,
            ItemStackOfUndyingS2CPayload::new);

    public ItemStackOfUndyingS2CPayload(ItemStack stack) {
        this(stack, false, Optional.empty(), false, Optional.empty());
    }

    public ItemStackOfUndyingS2CPayload(ItemStack stack, boolean shouldEmitParticles, boolean shouldPlaySound) {
        this(stack, shouldEmitParticles, Optional.empty(), shouldPlaySound, Optional.empty());
    }

    public ItemStackOfUndyingS2CPayload(ItemStack stack, boolean shouldEmitParticles, Optional<ParticleEffect> particleEffect, boolean shouldPlaySound, Optional<SoundEvent> soundEvent) {
        this.stack = stack;
        this.shouldEmitParticles = shouldEmitParticles;
        this.particleEffect = particleEffect;
        this.shouldPlaySound = shouldPlaySound;
        this.soundEvent = soundEvent;
    }

    public ItemStack stack() {
        return this.stack;
    }

    /**
    At this point, I can probably just remove this in favor of {@link Optional#isPresent()}
     */
    @Deprecated
    public boolean shouldEmitParticles() {
        return this.shouldEmitParticles;
    }

    @Deprecated
    public boolean shouldPlaySound() {
        return this.shouldPlaySound;
    }

    public Optional<ParticleEffect> getOptionalParticleEffect() {
        return this.particleEffect;
    }

    public ParticleEffect particleEffect() {
        return this.particleEffect.orElse(ParticleTypes.TOTEM_OF_UNDYING);
    }

    public Optional<SoundEvent> getOptionalSoundEvent() {
        return this.soundEvent;
    }

    public SoundEvent soundEvent() {
        return this.soundEvent.orElse(SoundEvents.ITEM_TOTEM_USE);
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
