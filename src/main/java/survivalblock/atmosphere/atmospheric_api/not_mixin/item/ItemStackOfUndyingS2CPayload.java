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
import survivalblock.atmosphere.atmospheric_api.not_mixin.datafixer.AtmosphericPacketCodecUtil;

@SuppressWarnings("unused")
public record ItemStackOfUndyingS2CPayload(ItemStack stack, boolean shouldEmitParticles, ParticleEffect particleEffect, boolean shouldPlaySound, SoundEvent soundEvent) implements CustomPayload {

    public static final CustomPayload.Id<ItemStackOfUndyingS2CPayload> ID = new Id<>(AtmosphericAPI.id("itemstack_of_undying_s2c"));
    public static final PacketCodec<RegistryByteBuf, ItemStackOfUndyingS2CPayload> CODEC = PacketCodec.tuple(
            ItemStack.PACKET_CODEC, ItemStackOfUndyingS2CPayload::stack,
            PacketCodecs.BOOL, ItemStackOfUndyingS2CPayload::shouldEmitParticles,
            ParticleTypes.PACKET_CODEC, ItemStackOfUndyingS2CPayload::particleEffect,
            PacketCodecs.BOOL, ItemStackOfUndyingS2CPayload::shouldPlaySound,
            AtmosphericPacketCodecUtil.SOUND_EVENT, ItemStackOfUndyingS2CPayload::soundEvent, ItemStackOfUndyingS2CPayload::new);

    public ItemStackOfUndyingS2CPayload(ItemStack stack) {
        this(stack, false, ParticleTypes.TOTEM_OF_UNDYING, false, SoundEvents.ITEM_TOTEM_USE);
    }

    public ItemStackOfUndyingS2CPayload(ItemStack stack, boolean shouldEmitParticles, boolean shouldPlaySound) {
        this(stack, shouldEmitParticles, ParticleTypes.TOTEM_OF_UNDYING, shouldPlaySound, SoundEvents.ITEM_TOTEM_USE);
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
