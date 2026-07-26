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
//? if >1.21.1 {
package survivalblock.atmosphere.atmospheric_api.not_mixin.render.shader.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.List;

@ApiStatus.Internal
@Environment(EnvType.CLIENT)
public final class AtmosphericShaderRegistryImpl {

    private AtmosphericShaderRegistryImpl() {
    }

    private static final List<ShaderApplier> SHADER_APPLIERS = new ArrayList<>();

    static void register(ShaderApplier shaderApplier) {
        SHADER_APPLIERS.add(shaderApplier);
        SHADER_APPLIERS.sort(null);
    }

    static void register(Identifier id, ShaderApplier.PostEffectCondition condition) {
        register(new ShaderApplier(id, condition));
    }

    static void register(Identifier texture, ShaderApplier.PostEffectCondition condition, int priority) {
        register(new ShaderApplier(texture, condition, priority));
    }

    public static List<ShaderApplier> getShaderAppliers() {
        return SHADER_APPLIERS;
    }
}
//?}