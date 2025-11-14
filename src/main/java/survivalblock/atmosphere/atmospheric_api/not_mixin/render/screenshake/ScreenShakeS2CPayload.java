package survivalblock.atmosphere.atmospheric_api.not_mixin.render.screenshake;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;

@SuppressWarnings({"unused"})
public record ScreenShakeS2CPayload(float intensity, int duration, String modId, String reason, boolean shouldAutoOverride, boolean shouldAddToQueue) implements CustomPacketPayload {
    public static final Type<ScreenShakeS2CPayload> ID = new Type<>(AtmosphericAPI.id("screen_shake_s2c"));
    public static final StreamCodec<RegistryFriendlyByteBuf, ScreenShakeS2CPayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT, ScreenShakeS2CPayload::intensity,
            ByteBufCodecs.VAR_INT, ScreenShakeS2CPayload::duration,
            ByteBufCodecs.STRING_UTF8, ScreenShakeS2CPayload::modId,
            ByteBufCodecs.STRING_UTF8, ScreenShakeS2CPayload::reason,
            ByteBufCodecs.BOOL, ScreenShakeS2CPayload::shouldAutoOverride,
            ByteBufCodecs.BOOL, ScreenShakeS2CPayload::shouldAddToQueue,
            ScreenShakeS2CPayload::new
    );

    public ScreenShakeS2CPayload(float intensity, int duration) {
        this(intensity, duration, false, false);
    }

    public ScreenShakeS2CPayload(float intensity, int duration, String modId) {
        this(intensity, duration, modId, "");
    }

    public ScreenShakeS2CPayload(float intensity, int duration, String modId, String reason) {
        this(intensity, duration, modId, reason, false, false);
    }

    public ScreenShakeS2CPayload(float intensity, int duration, boolean shouldAutoOverride, boolean shouldAddToQueue) {
        this(intensity, duration, AtmosphericAPI.MOD_ID, shouldAutoOverride, shouldAddToQueue);
    }

    public ScreenShakeS2CPayload(float intensity, int duration, String modId, boolean shouldAutoOverride, boolean shouldAddToQueue) {
        this(intensity, duration, modId, "", false, false);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}