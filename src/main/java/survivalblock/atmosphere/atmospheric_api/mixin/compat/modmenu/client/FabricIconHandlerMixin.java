/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
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
