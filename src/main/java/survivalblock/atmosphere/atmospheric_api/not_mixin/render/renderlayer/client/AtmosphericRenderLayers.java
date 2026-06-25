/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.render.renderlayer.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Model;
//~ if >=1.21.11 'renderer.RenderType' -> 'renderer.rendertype.RenderType'
import net.minecraft.client.renderer.rendertype.RenderType;
//? if >=1.21.11
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public final class AtmosphericRenderLayers {

    public static RenderType getEndShader(){
        //? if <1.21.11 {
        /*return RenderType.endPortal();
        *///?} else {
        return RenderTypes.endPortal();
        //?}
    }

    @Nullable
    public static RenderType getArtificialLifeRenderLayer(boolean showBody, boolean translucent, boolean showOutline, Identifier texture, Model model) {
        if (showOutline) {
            //? if <1.21.11 {
            /*return RenderType.outline(texture);
             *///?} else {
            return RenderTypes.outline(texture);
            //?}
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
