package survivalblock.atmosphere.atmospheric_api.not_mixin.entity.networking;

import io.netty.buffer.ByteBuf;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;

@SuppressWarnings("unused")
public record RawVelocityUpdatePayload(int entityId, double velocityX, double velocityY, double velocityZ) implements CustomPacketPayload {

    public static final Type<RawVelocityUpdatePayload> ID = new Type<>(AtmosphericAPI.id("raw_velocity_update"));

    public static final StreamCodec<ByteBuf, RawVelocityUpdatePayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, payload -> payload.entityId,
            ByteBufCodecs.DOUBLE, payload -> payload.velocityX,
            ByteBufCodecs.DOUBLE, payload -> payload.velocityY,
            ByteBufCodecs.DOUBLE, payload -> payload.velocityZ,
            RawVelocityUpdatePayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }

    public RawVelocityUpdatePayload(Entity entity, Vec3 velocity) {
        this(entity.getId(), velocity);
    }

    public RawVelocityUpdatePayload(int entityId, Vec3 velocity) {
        this(entityId, velocity.x, velocity.y, velocity.z);
    }

    public static class ClientReceiver<T extends RawVelocityUpdatePayload> implements ClientPlayNetworking.PlayPayloadHandler<T> {

        public static final ClientReceiver<RawVelocityUpdatePayload> INSTANCE = new ClientReceiver<>();

        protected ClientReceiver() {

        }

        @SuppressWarnings("resource")
        @Override
        public void receive(T payload, ClientPlayNetworking.Context context) {
            Level world = context.client().level;
            if (world == null) {
                return;
            }
            Entity entity = world.getEntity(payload.entityId());
            if (entity == null) {
                return;
            }
            entity.lerpMotion(payload.velocityX(), payload.velocityY(), payload.velocityZ());
        }
    }
}
