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
//? if =1.21.1 {
/*package survivalblock.atmosphere.atmospheric_api.not_mixin.item.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.resources.model.ModelIdentifier;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public class AtmosphericTridentRegistry {

    public static void register(Item item, Identifier texture) {
        AtmosphericTridentRegistryImpl.register(item, texture);
    }

    public static void register(Item item, Identifier texture, ModelIdentifier modelIdentifier) {
        AtmosphericTridentRegistryImpl.register(item, texture, modelIdentifier);
    }

    public static void register(Item item, Identifier texture, ModelLayerLocation modelLayerLocation) {
        AtmosphericTridentRegistryImpl.register(item, texture, modelLayerLocation);
    }

    public static void register(Item item, Identifier texture, ModelIdentifier modelIdentifier, ModelLayerLocation modelLayerLocation) {
        AtmosphericTridentRegistryImpl.register(item, texture, modelIdentifier, modelLayerLocation);
    }
}
*///?}