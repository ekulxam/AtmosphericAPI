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
//? if 1.21.1 {
/*package survivalblock.atmosphere.atmospheric_api.not_mixin.item.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.resources.model.ModelIdentifier;
import net.minecraft.world.item.Item;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.AlternateModelItem;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.IAmASpyglassItem;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public final class AlternateItemModelRegistry {

    private AlternateItemModelRegistry() {
    }

    public static void register(Item item, ModelIdentifier modelIdentifier) {
        if (!(item instanceof AlternateModelItem alternateModelItem)) {
            throw new IllegalArgumentException("The item must be an instance of AlternateModelItem!");
        }
        AlternateItemModelRegistryImpl.register(alternateModelItem, modelIdentifier);
    }

    public static void registerSpyglass(Item item, ModelIdentifier modelIdentifier) {
        if (!(item instanceof IAmASpyglassItem spyglass)) {
            throw new IllegalArgumentException("The item must be an instance of IAmASpyglassItem!");
        }
        AlternateItemModelRegistryImpl.registerSpyglass(spyglass, modelIdentifier);
    }
}
*///?}