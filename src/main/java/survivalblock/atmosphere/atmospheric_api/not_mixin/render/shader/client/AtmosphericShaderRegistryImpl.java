//? if >1.21.1 {
package survivalblock.atmosphere.atmospheric_api.not_mixin.render.shader.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
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

    public static List<ShaderApplier> getShaderAppliers() {
        return SHADER_APPLIERS;
    }
}
//?}