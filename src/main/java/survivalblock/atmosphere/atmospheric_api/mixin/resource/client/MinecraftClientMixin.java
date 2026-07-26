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
package survivalblock.atmosphere.atmospheric_api.mixin.resource.client;

import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackRepository;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import survivalblock.atmosphere.atmospheric_api.not_mixin.resource.client.injected_interface.AtmosphericClientResourcePackFinder;

import java.util.stream.Collectors;

@Mixin(Minecraft.class)
public abstract class MinecraftClientMixin implements AtmosphericClientResourcePackFinder {

    @Shadow public abstract PackRepository getResourcePackRepository();

    public boolean atmospheric_api$isResourcePackLoaded(String name) {
        return this.getResourcePackRepository().getSelectedIds().contains(name);
    }

    public boolean atmospheric_api$doesResourcePackExist(String name) {
        return this.getResourcePackRepository().isAvailable(name);
    }

    @Override
    public @Nullable Pack atmospheric_api$getResourcePack(String name) {
        return this.getResourcePackRepository().getPack(name);
    }

    @Override
    public @Nullable Pack atmospheric_api$getActiveResourcePack(String name) {
        return this.getResourcePackRepository().getSelectedPacks().stream().collect(Collectors.toMap(Pack::getId, (profile) -> profile)).get(name);
    }

    @Override
    public boolean atmospheric_api$enableResourcePack(String name) {
        return this.getResourcePackRepository().addPack(name);
    }

    @Override
    public boolean atmospheric_api$disableResourcePack(String name) {
        return this.getResourcePackRepository().removePack(name);
    }
}
