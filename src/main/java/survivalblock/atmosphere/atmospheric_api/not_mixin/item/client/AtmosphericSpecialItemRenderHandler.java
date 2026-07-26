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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.IAmASpyglassItem;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.TwoHandedItem;

import java.util.function.Function;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public class AtmosphericSpecialItemRenderHandler {

    public static void handleShouldZoomIn(Item item, Function<ItemStack, Boolean> function) {
        if (!(item instanceof IAmASpyglassItem spyglass)) {
            throw new IllegalArgumentException("The item must be an instance of IAmASpyglassItem!");
        }
        AtmosphericSpecialItemRenderHandlerImpl.handleShouldZoomIn(spyglass, function);
    }

    public static void handleShouldRenderOverlay(Item item, Function<ItemStack, Boolean> function) {
        if (!(item instanceof IAmASpyglassItem spyglass)) {
            throw new IllegalArgumentException("The item must be an instance of IAmASpyglassItem!");
        }
        AtmosphericSpecialItemRenderHandlerImpl.handleShouldRenderOverlay(spyglass, function);
    }

    public static void handleShouldRenderTwoHanded(Item item, Function<ItemStack, Boolean> function) {
        if (!(item instanceof TwoHandedItem twoHandedItem)) {
            throw new IllegalArgumentException("The item must be an instance of TwohandedItem!");
        }
        AtmosphericSpecialItemRenderHandlerImpl.handleShouldRenderTwoHanded(twoHandedItem, function);
    }
}
