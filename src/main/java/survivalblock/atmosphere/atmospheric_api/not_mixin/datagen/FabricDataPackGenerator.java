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
package survivalblock.atmosphere.atmospheric_api.not_mixin.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.resources.Identifier;
import survivalblock.atmosphere.atmospheric_api.access.AtmosphericDatapackGenerator;

public final class FabricDataPackGenerator {

    public static final String DATAPACK_PATH = "datapacks";

    @SuppressWarnings("unused")
    public static FabricDataGenerator.Pack createBuiltinDataPack(FabricDataGenerator fabricDataGenerator, Identifier id) {
        return ((AtmosphericDatapackGenerator) (Object) fabricDataGenerator).atmospheric_api$createBuiltinDataPack(id);
    }

    @SuppressWarnings("unused")
    public static FabricDataGenerator.Pack createBuiltinSomething(FabricDataGenerator fabricDataGenerator, String alternatePath, Identifier id) {
        return ((AtmosphericDatapackGenerator) (Object) fabricDataGenerator).atmospheric_api$generateSomethingUnderAlternatePath(alternatePath, id);
    }
}
