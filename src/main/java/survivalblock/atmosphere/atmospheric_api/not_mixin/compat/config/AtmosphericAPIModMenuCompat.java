package survivalblock.atmosphere.atmospheric_api.not_mixin.compat.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.minecraft.network.chat.Component;

import static survivalblock.atmosphere.atmospheric_api.not_mixin.render.screenshake.ScreenShaker.DISABLE_ALL_SCREENSHAKERS_RESOURCE_PACK;

public class AtmosphericAPIModMenuCompat implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        // I do configs in resourcepacks :sungalses:
        return parent -> ConfigPackScreen.fromParent(parent,
                Component.translatable("resourcePack.atmospheric_api.configscreen"),
                DISABLE_ALL_SCREENSHAKERS_RESOURCE_PACK.toString());
    }
}
