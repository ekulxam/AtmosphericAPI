package survivalblock.atmosphere.atmospheric_api.not_mixin.entity.networking;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.Entity;

@SuppressWarnings("unused")
public interface AutoSendPayload extends CustomPacketPayload {

    default void send(Entity entity) {
        PlayerLookup.tracking(entity).forEach(player -> ServerPlayNetworking.send(player, this));
    }
}
