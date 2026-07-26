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
package survivalblock.atmosphere.atmospheric_api.not_mixin.util;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import org.jetbrains.annotations.ApiStatus;

/**
 * A {@linkplain ModInitializer} that allows access to the mod's {@linkplain ModContainer} and the {@linkplain FabricLoader} instance.
 */
@SuppressWarnings("unused")
@ApiStatus.Experimental
public interface AtmosphericModInitializer extends ModInitializer {
    @Override
    default void onInitialize() {
        String modId = this.getModId();

        if (modId == null) {
            try {
                modId = (String) this.getClass().getField("MOD_ID").get(null);
            } catch (NoSuchFieldException e) {
                try {
                    modId = (String) this.getClass().getField("MODID").get(null);
                } catch (NoSuchFieldException | IllegalAccessException | ClassCastException e1) {
                    e1.addSuppressed(e);
                    throw new RuntimeException(e1);
                }
            } catch (IllegalAccessException | ClassCastException e) {
                throw new RuntimeException(e);
            }
        }

        FabricLoader floader = FabricLoader.getInstance();
        this.onInitialize(floader.getModContainer(modId).orElseThrow(), floader);
    }

    void onInitialize(ModContainer modContainer, FabricLoader floader);

    default String getModId() {
        return null;
    }
}
