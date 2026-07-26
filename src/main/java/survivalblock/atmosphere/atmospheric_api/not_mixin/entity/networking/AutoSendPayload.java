/*
 * Copyright (c) 21:56 UTC 26 July 2026 - present ekulxam
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.entity.networking;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.function.Predicate;

@SuppressWarnings("unused")
public interface AutoSendPayload extends CustomPacketPayload {

    default void send(Entity entity) {
        PlayerLookup.tracking(entity).forEach(player -> ServerPlayNetworking.send(player, this));
    }

    default void send(Entity entity, Predicate<ServerPlayer> syncPredicate) {
        PlayerLookup.tracking(entity).forEach(player -> {
            if (syncPredicate.test(player)) {
                ServerPlayNetworking.send(player, this);
            }
        });
    }
}
