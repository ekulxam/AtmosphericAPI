package survivalblock.atmosphere.atmospheric_api.not_mixin.screenshake;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;

@SuppressWarnings("unused")
public record ScreenShakeS2CPayload(float intensity, int duration) implements CustomPayload {
    public static final Id<ScreenShakeS2CPayload> ID = new Id<>(AtmosphericAPI.id("screen_shake_s2c"));
    public static final PacketCodec<RegistryByteBuf, ScreenShakeS2CPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.FLOAT, ScreenShakeS2CPayload::intensity,
            PacketCodecs.INTEGER, ScreenShakeS2CPayload::duration,
            ScreenShakeS2CPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}