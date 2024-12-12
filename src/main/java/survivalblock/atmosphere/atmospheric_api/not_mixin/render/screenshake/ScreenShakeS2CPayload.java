package survivalblock.atmosphere.atmospheric_api.not_mixin.render.screenshake;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;

@SuppressWarnings({"unused"})
public record ScreenShakeS2CPayload(float intensity, int duration, boolean shouldAutoOverride, boolean shouldAddToQueue) implements CustomPayload {
    public static final Id<ScreenShakeS2CPayload> ID = new Id<>(AtmosphericAPI.id("screen_shake_s2c"));
    public static final PacketCodec<RegistryByteBuf, ScreenShakeS2CPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.FLOAT, ScreenShakeS2CPayload::intensity,
            PacketCodecs.VAR_INT, ScreenShakeS2CPayload::duration,
            PacketCodecs.BOOL, ScreenShakeS2CPayload::shouldAutoOverride,
            PacketCodecs.BOOL, ScreenShakeS2CPayload::shouldAddToQueue,
            ScreenShakeS2CPayload::new
    );

    public ScreenShakeS2CPayload(float intensity, int duration) {
        this(intensity, duration, false, false);
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}