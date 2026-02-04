/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.render.renderlayer.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public final class AtmosphericRenderLayers {

    public static RenderType getEndShader(){
        return RenderType.endPortal();
    }

    @Nullable
    public static RenderType getArtificialLifeRenderLayer(boolean showBody, boolean translucent, boolean showOutline, ResourceLocation texture, Model model) {
        if (showOutline) {
            return RenderType.outline(texture);
        }
        if (translucent) {
            return null;
        }
        if (showBody) {
            return model.renderType(texture);
        }
        return null;
    }
}
