package survivalblock.atmosphere.atmospheric_api.not_mixin.item;

import net.minecraft.client.gui.DrawContext;
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

/**
 * Originally written as the PantsOfUndyingPacket for Enchancement Unbound
 * Used as an S2C payload to tell the client to display a floating item, limit to an activated totem of undying
 * @see net.minecraft.client.render.GameRenderer#floatingItem
 * @see net.minecraft.client.render.GameRenderer#showFloatingItem(ItemStack)
 * @see net.minecraft.client.render.GameRenderer#renderFloatingItem(DrawContext, float)
 */
@SuppressWarnings({"unused", "JavadocReference"})
public record ItemStackOfUndyingS2CPayload(ItemStack stack, Optional<ParticleEffect> particleEffect, Optional<SoundEvent> soundEvent) implements CustomPayload {

    public static final CustomPayload.Id<ItemStackOfUndyingS2CPayload> ID = new Id<>(AtmosphericAPI.id("itemstack_of_undying_s2c"));
    public static final PacketCodec<RegistryByteBuf, ItemStackOfUndyingS2CPayload> CODEC = PacketCodec.tuple(
            ItemStack.PACKET_CODEC, (payload) -> payload.stack,
            ParticleTypes.PACKET_CODEC.collect(PacketCodecs::optional), (payload) -> payload.particleEffect,
            PacketCodecs.SOUND_EVENT.collect(PacketCodecs::optional), (payload) -> payload.soundEvent,
            ItemStackOfUndyingS2CPayload::new);

    public ItemStackOfUndyingS2CPayload(ItemStack stack) {
        this(stack, Optional.empty(), Optional.empty());
    }

    public ItemStackOfUndyingS2CPayload(ItemStack stack, ParticleEffect particleEffect) {
        this(stack, Optional.of(particleEffect), Optional.empty());
    }

    public ItemStackOfUndyingS2CPayload(ItemStack stack, SoundEvent soundEvent) {
        this(stack, Optional.empty(), Optional.of(soundEvent));
    }

    public ItemStackOfUndyingS2CPayload(ItemStack stack, ParticleEffect particleEffect, SoundEvent soundEvent) {
        this(stack, Optional.of(particleEffect), Optional.of(soundEvent));
    }

    public boolean shouldEmitParticles() {
        return this.particleEffect.isPresent();
    }

    public boolean shouldPlaySound() {
        return this.soundEvent.isPresent();
    }

    public ParticleEffect getParticleEffectValue() {
        return this.particleEffect.orElse(ParticleTypes.TOTEM_OF_UNDYING);
    }

    public SoundEvent getSoundEventValue() {
        return this.soundEvent.orElse(SoundEvents.ITEM_TOTEM_USE);
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
