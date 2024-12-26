package survivalblock.atmosphere.atmospheric_api.not_mixin.resource;

import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.fabric.impl.resource.loader.ResourceManagerHelperImpl;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@SuppressWarnings({"UnstableApiUsage", "unused"})
public class AtmosphericResourceManagerHelper {

    public static boolean registerBuiltinDataPack(Identifier id, ModContainer container, ResourcePackActivationType activationType) {
        return ResourceManagerHelperImpl.registerBuiltinResourcePack(id, "datapacks/" + id.getPath(), container, activationType);
    }

    public static boolean registerBuiltinDataPack(Identifier id, ModContainer container, Text displayName, ResourcePackActivationType activationType) {
        return ResourceManagerHelperImpl.registerBuiltinResourcePack(id, "datapacks/" + id.getPath(), container, displayName, activationType);
    }

    public static boolean registerBuiltinSomething(Identifier id, ModContainer container, String subPath, ResourcePackActivationType activationType) {
        return ResourceManagerHelperImpl.registerBuiltinResourcePack(id, subPath + id.getPath(), container, activationType);
    }

    public static boolean registerBuiltinSomething(Identifier id, ModContainer container, String subPath, Text displayName, ResourcePackActivationType activationType) {
        return ResourceManagerHelperImpl.registerBuiltinResourcePack(id, subPath + id.getPath(), container, displayName, activationType);
    }
}
