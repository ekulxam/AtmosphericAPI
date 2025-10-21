package survivalblock.atmosphere.atmospheric_api.not_mixin.entity.networking;

import io.netty.buffer.ByteBuf;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;

@SuppressWarnings("unused")
public record RawVelocityUpdatePayload(int entityId, double velocityX, double velocityY, double velocityZ) implements CustomPayload {

    public static final Id<RawVelocityUpdatePayload> ID = new Id<>(AtmosphericAPI.id("raw_velocity_update"));

    public static final PacketCodec<ByteBuf, RawVelocityUpdatePayload> CODEC = PacketCodec.tuple(
            PacketCodecs.VAR_INT, payload -> payload.entityId,
            PacketCodecs.DOUBLE, payload -> payload.velocityX,
            PacketCodecs.DOUBLE, payload -> payload.velocityY,
            PacketCodecs.DOUBLE, payload -> payload.velocityZ,
            RawVelocityUpdatePayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public RawVelocityUpdatePayload(Entity entity, Vec3d velocity) {
        this(entity.getId(), velocity);
    }

    public RawVelocityUpdatePayload(int entityId, Vec3d velocity) {
        this(entityId, velocity.x, velocity.y, velocity.z);
    }

    public static class ClientReceiver<T extends RawVelocityUpdatePayload> implements ClientPlayNetworking.PlayPayloadHandler<T> {

        public static final ClientReceiver<RawVelocityUpdatePayload> INSTANCE = new ClientReceiver<>();

        protected ClientReceiver() {

        }

        @SuppressWarnings("resource")
        @Override
        public void receive(T payload, ClientPlayNetworking.Context context) {
            World world = context.client().world;
            if (world == null) {
                return;
            }
            Entity entity = world.getEntityById(payload.entityId());
            if (entity == null) {
                return;
            }
            entity.setVelocityClient(payload.velocityX(), payload.velocityY(), payload.velocityZ());
        }
    }
}
