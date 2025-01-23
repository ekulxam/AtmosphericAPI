package survivalblock.atmosphere.atmospheric_api.not_mixin.compat.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import static survivalblock.atmosphere.atmospheric_api.not_mixin.render.screenshake.ScreenShaker.DISABLE_ALL_SCREENSHAKERS_RESOURCE_PACK;

public class AtmosphericAPIModMenuCompat implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        // I do configs in resourcepacks :sungalses:
        return parent -> ConfigPackScreen.fromParent(parent,
                DISABLE_ALL_SCREENSHAKERS_RESOURCE_PACK.toString());
    }
}
