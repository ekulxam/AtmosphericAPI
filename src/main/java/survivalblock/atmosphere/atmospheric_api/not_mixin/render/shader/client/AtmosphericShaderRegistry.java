//? if >1.21.1 {
package survivalblock.atmosphere.atmospheric_api.not_mixin.render.shader.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public final class AtmosphericShaderRegistry {

    private AtmosphericShaderRegistry() {
    }

    public static void registerOverlay(ShaderApplier shaderApplier) {
        AtmosphericShaderRegistryImpl.register(shaderApplier);
    }

    public static void registerOverlay(Identifier id, ShaderApplier.PostEffectCondition condition) {
        registerOverlay(new ShaderApplier(id, condition));
    }

    public static void registerOverlay(Identifier texture, ShaderApplier.PostEffectCondition condition, int priority) {
        registerOverlay(new ShaderApplier(texture, condition, priority));
    }
}
//?}