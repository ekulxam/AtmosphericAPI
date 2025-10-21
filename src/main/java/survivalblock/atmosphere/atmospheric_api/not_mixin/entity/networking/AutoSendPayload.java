package survivalblock.atmosphere.atmospheric_api.not_mixin.entity.networking;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.CustomPayload;

@SuppressWarnings("unused")
public interface AutoSendPayload extends CustomPayload {

    default void send(Entity entity) {
        PlayerLookup.tracking(entity).forEach(player -> ServerPlayNetworking.send(player, this));
    }
}
