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
package survivalblock.atmosphere.atmospheric_api.not_mixin.resource.client.injected_interface;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.repository.Pack;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public interface AtmosphericClientResourcePackFinder {

    default boolean atmospheric_api$isResourcePackLoaded(String name) {
        return false;
    }

    default boolean atmospheric_api$doesResourcePackExist(String name) {
        return false;
    }

    @Nullable
    default Pack atmospheric_api$getResourcePack(String name) {
        return null;
    }

    @Nullable
    default Pack atmospheric_api$getActiveResourcePack(String name) {
        return null;
    }

    default boolean atmospheric_api$enableResourcePack(String name) {
        return false;
    }

    default boolean atmospheric_api$disableResourcePack(String name) {
        return false;
    }

    default boolean atmospheric_api$isResourcePackLoaded(Identifier id) {
        return this.atmospheric_api$isResourcePackLoaded(id.toString());
    }

    default boolean atmospheric_api$doesResourcePackExist(Identifier id) {
        return this.atmospheric_api$doesResourcePackExist(id.toString());
    }

    @Nullable
    default Pack atmospheric_api$getResourcePack(Identifier id) {
        return this.atmospheric_api$getResourcePack(id.toString());
    }

    @Nullable
    default Pack atmospheric_api$getActiveResourcePack(Identifier id) {
        return this.atmospheric_api$getActiveResourcePack(id.toString());
    }

    default boolean atmospheric_api$enableResourcePack(Identifier id) {
        return this.atmospheric_api$enableResourcePack(id.toString());
    }

    default boolean atmospheric_api$disableResourcePack(Identifier id) {
        return this.atmospheric_api$disableResourcePack(id.toString());
    }
}
