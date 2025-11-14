//? if >1.21.1 {
package survivalblock.atmosphere.atmospheric_api.not_mixin.render.shader.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.ApiStatus;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public final class AtmosphericShaderRegistry {

    private AtmosphericShaderRegistry() {
    }

    @ApiStatus.Experimental
    public static void register(ShaderApplier shaderApplier) {
        AtmosphericShaderRegistryImpl.register(shaderApplier);
    }

    public static void register(ResourceLocation id, ShaderApplier.PostEffectCondition condition) {
        AtmosphericShaderRegistryImpl.register(id, condition);
    }

    public static void register(ResourceLocation id, ShaderApplier.PostEffectCondition condition, int priority) {
        AtmosphericShaderRegistryImpl.register(id, condition, priority);
    }
}
//?}