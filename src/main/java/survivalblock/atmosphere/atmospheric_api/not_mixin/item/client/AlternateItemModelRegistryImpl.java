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

import java.util.HashMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.resources.model.ModelIdentifier;
import org.jetbrains.annotations.ApiStatus;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.AlternateModelItem;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.IAmASpyglassItem;

@Environment(EnvType.CLIENT)
public final class AlternateItemModelRegistryImpl {

    private AlternateItemModelRegistryImpl() {
    }

    private static final HashMap<AlternateModelItem, ModelIdentifier> models = new HashMap<>();

    private static final HashMap<IAmASpyglassItem, ModelIdentifier> spyglassModels = new HashMap<>();

    @ApiStatus.Internal
    static void register(AlternateModelItem item, ModelIdentifier modelIdentifier) {
        models.put(item, modelIdentifier);
    }

    @ApiStatus.Internal
    static void registerSpyglass(IAmASpyglassItem item, ModelIdentifier modelIdentifier) {
        spyglassModels.put(item, modelIdentifier);
    }

    public static HashMap<AlternateModelItem, ModelIdentifier> getModels() {
        return models;
    }

    public static HashMap<IAmASpyglassItem, ModelIdentifier> getSpyglassModels() {
        return spyglassModels;
    }
}
*///?}