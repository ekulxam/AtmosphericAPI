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
package survivalblock.atmosphere.atmospheric_api.not_mixin.render.overlay.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

@ApiStatus.Internal
@Environment(EnvType.CLIENT)
public final class AtmosphericOverlayRegistryImpl {

    private AtmosphericOverlayRegistryImpl() {
    }

    private static final List<OverlayHolder> OVERLAY_HOLDERS = new ArrayList<>();

    static void register(OverlayHolder overlayHolder) {
        OVERLAY_HOLDERS.add(overlayHolder);
        OVERLAY_HOLDERS.sort(null);
    }

    static void register(Identifier texture, BiFunction<Minecraft, LocalPlayer, Float> opacitySupplier) {
        register(new OverlayHolder(texture, opacitySupplier));
    }

    static void register(Identifier texture, BiFunction<Minecraft, LocalPlayer, Float> opacitySupplier, boolean bypassable) {
        register(new OverlayHolder(texture, opacitySupplier, bypassable));
    }

    static void register(Identifier texture, BiFunction<Minecraft, LocalPlayer, Float> opacitySupplier, int priority) {
        register(new OverlayHolder(texture, opacitySupplier, priority));
    }

    static void register(Identifier texture, BiFunction<Minecraft, LocalPlayer, Float> opacitySupplier, boolean bypassable, int priority) {
        register(new OverlayHolder(texture, opacitySupplier, bypassable, priority));
    }

    public static List<OverlayHolder> getOverlayHolders() {
        return OVERLAY_HOLDERS;
    }
}
