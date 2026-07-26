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
package survivalblock.atmosphere.atmospheric_api.not_mixin.item.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
//? if >=1.21.8
import net.fabricmc.fabric.api.client.rendering.v1.RenderStateDataKey;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.IAmASpyglassItem;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.TwoHandedItem;

import java.util.HashMap;
import java.util.function.Function;

@ApiStatus.Internal
@Environment(EnvType.CLIENT)
public final class AtmosphericSpecialItemRenderHandlerImpl {

    //? if >=1.21.8 {
    public static final RenderStateDataKey<ItemStack> MAINHAND_STACK_KEY = RenderStateDataKey.create(() -> AtmosphericAPI.id("mainhand_stack").toString());
    public static final RenderStateDataKey<ItemStack> OFFHAND_STACK_KEY = RenderStateDataKey.create(() -> AtmosphericAPI.id("offhand_stack").toString());
    //?}

    private static final HashMap<IAmASpyglassItem, Function<ItemStack, Boolean>> shouldRenderZoomHandler = new HashMap<>();

    private static final HashMap<IAmASpyglassItem, Function<ItemStack, Boolean>> shouldRenderOverlayHandler = new HashMap<>();

    private static final HashMap<TwoHandedItem, Function<ItemStack, Boolean>> shouldRenderTwoHandedHandler = new HashMap<>();

    static void handleShouldZoomIn(IAmASpyglassItem item, Function<ItemStack, Boolean> function) {
        shouldRenderZoomHandler.put(item, function);
    }

    static void handleShouldRenderOverlay(IAmASpyglassItem item, Function<ItemStack, Boolean> function) {
        shouldRenderOverlayHandler.put(item, function);
    }

    static void handleShouldRenderTwoHanded(TwoHandedItem item, Function<ItemStack, Boolean> function) {
        shouldRenderTwoHandedHandler.put(item, function);
    }

    public static HashMap<IAmASpyglassItem, Function<ItemStack, Boolean>> getZoomHandler() {
        return shouldRenderZoomHandler;
    }

    public static HashMap<IAmASpyglassItem, Function<ItemStack, Boolean>> getOverlayHandler() {
        return shouldRenderOverlayHandler;
    }

    public static HashMap<TwoHandedItem, Function<ItemStack, Boolean>> getTwoHandedHandler() {
        return shouldRenderTwoHandedHandler;
    }
}
