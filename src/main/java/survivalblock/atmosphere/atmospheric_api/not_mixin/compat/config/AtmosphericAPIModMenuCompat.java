package survivalblock.atmosphere.atmospheric_api.not_mixin.compat.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class AtmosphericAPIModMenuCompat implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        // I do configs in resourcepacks :sungalses:
        return ConfigPackScreen::fromParent;
    }
}
