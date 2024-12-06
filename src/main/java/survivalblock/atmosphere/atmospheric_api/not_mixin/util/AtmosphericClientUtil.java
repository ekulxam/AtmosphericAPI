package survivalblock.atmosphere.atmospheric_api.not_mixin.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public final class AtmosphericClientUtil {

    public static boolean isResourcePackLoaded(MinecraftClient client, String name) {
        return client.getResourcePackManager().getEnabledIds().contains(name);
    }

    public static boolean isResourcePackLoaded(String name) {
        return isResourcePackLoaded(MinecraftClient.getInstance(), name);
    }

    public static RenderLayer getEndShader(){
        return RenderLayer.getEndPortal();
    }

    @Nullable
    public static RenderLayer getArtificialLifeRenderLayer(boolean showBody, boolean translucent, boolean showOutline, Identifier texture, Model model) {
        if (showOutline) {
            return RenderLayer.getOutline(texture);
        }
        if (translucent) {
            return null;
        }
        if (showBody) {
            return model.getLayer(texture);
        }
        return null;
    }
}
