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
package survivalblock.atmosphere.atmospheric_api.access;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.resources.Identifier;

// well, I can't use interface injection for things that are not mixin, so I guess we're back to duck-ing
@SuppressWarnings("unused")
public interface AtmosphericDatapackGenerator {

    default FabricDataGenerator.Pack atmospheric_api$createBuiltinDataPack(Identifier id) {
        throw new UnsupportedOperationException();
    }

    default FabricDataGenerator.Pack atmospheric_api$generateSomethingUnderAlternatePath(String path, Identifier id) {
        throw new UnsupportedOperationException();
    }
}
