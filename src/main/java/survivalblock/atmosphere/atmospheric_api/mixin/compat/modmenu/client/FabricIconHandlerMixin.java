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
package survivalblock.atmosphere.atmospheric_api.mixin.compat.modmenu.client;

import com.terraformersmc.modmenu.util.mod.fabric.FabricIconHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;
import survivalblock.atmosphere.atmospheric_api.not_mixin.funny.ThisIsABadIdea;
import survivalblock.atmosphere.atmospheric_api.not_mixin.render.screenshake.ScreenShaker;

import java.util.Optional;

@ApiStatus.Internal
@ThisIsABadIdea(ThisIsABadIdea.LevelsOfHorrendousness.PROBABLY)
@Environment(EnvType.CLIENT)
@Mixin(value = FabricIconHandler.class, remap = false)
public class FabricIconHandlerMixin {

    @ModifyVariable(method = "createIcon", at = @At(value = "HEAD"), index = 2, argsOnly = true)
    private String getAlternateIcon(String originalPath, ModContainer modContainer) {
        Optional<ModContainer> atmosphere = FabricLoader.getInstance().getModContainer(AtmosphericAPI.MOD_ID);
        if (atmosphere.isEmpty()) {
            return originalPath;
        }
        if (!modContainer.equals(atmosphere.get())) {
            return originalPath;
        }
        return Minecraft.getInstance().atmospheric_api$isResourcePackLoaded(ScreenShaker.DISABLE_ALL_SCREENSHAKERS_RESOURCE_PACK) ? "assets/atmospheric_api/giver_of_light.png" : originalPath;
    }
}
