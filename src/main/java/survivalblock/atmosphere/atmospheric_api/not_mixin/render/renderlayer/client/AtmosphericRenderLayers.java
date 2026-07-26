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
