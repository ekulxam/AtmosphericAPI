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
package survivalblock.atmosphere.atmospheric_api.not_mixin.self_datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import survivalblock.atmosphere.atmospheric_api.not_mixin.damage_type.AtmosphericDamageTypeGamerules;

import java.util.concurrent.CompletableFuture;

public class AtmosphericAPIEnUsLangGenerator extends FabricLanguageProvider {

    public AtmosphericAPIEnUsLangGenerator(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        // packs
        translationBuilder.add("resourcePack.atmospheric_api.disableallscreenshakers.name", "Disable All ScreenShakers from Atmospheric API");

        translationBuilder.add("resourcePack.atmospheric_api.configscreen", "Atmospheric API - Resource Pack Config Screen");

        // gamerules
        translationBuilder.add(AtmosphericDamageTypeGamerules.ALLOW_BYPASSING_CREATIVE.getDescriptionId(), "Atmospheric API - Damage Types Can Bypass Creative");
        translationBuilder.add(AtmosphericDamageTypeGamerules.ALLOW_BYPASSING_SPECTATOR.getDescriptionId(), "Atmospheric API - Damage Types Can Bypass Spectator");
    }
}
