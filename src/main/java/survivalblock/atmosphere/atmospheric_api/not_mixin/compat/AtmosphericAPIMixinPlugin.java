package survivalblock.atmosphere.atmospheric_api.not_mixin.compat;

import net.fabricmc.loader.api.FabricLoader;
import survivalblock.atmosphere.atmospheric_api.mixin.compat.AtmosphericMixinConfigPlugin;

public class AtmosphericAPIMixinPlugin implements AtmosphericMixinConfigPlugin {

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (mixinClassName.contains("compat")) {
            if (mixinClassName.contains("modmenu")) {
                return FabricLoader.getInstance().isModLoaded("modmenu");
            }
        }
        return true;
    }
}
