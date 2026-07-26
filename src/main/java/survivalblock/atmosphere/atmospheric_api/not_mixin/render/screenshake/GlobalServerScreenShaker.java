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
package survivalblock.atmosphere.atmospheric_api.not_mixin.render.screenshake;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;

@Environment(EnvType.SERVER)
@SuppressWarnings("unused")
public class GlobalServerScreenShaker extends BasicScreenShaker implements ActiveScreenShaker {

    public GlobalServerScreenShaker(float intensity, int duration) {
        super(intensity, duration);
    }

    @Override
    public void activate(Level world) {
        if (!(world instanceof ServerLevel serverWorld)) {
            throw new IllegalStateException("Cannot activate a GlobalScreenShaker when on the client! How did we even get here?");
        }
        serverWorld.players().forEach(serverPlayer -> ServerPlayNetworking.send(serverPlayer, createPayload()));
    }

    protected ScreenShakeS2CPayload createPayload() {
        return new ScreenShakeS2CPayload(this.intensity, this.duration);
    }
}
