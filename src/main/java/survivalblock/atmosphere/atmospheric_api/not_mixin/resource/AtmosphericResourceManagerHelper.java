package survivalblock.atmosphere.atmospheric_api.not_mixin.resource;

import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.fabric.impl.resource.loader.ResourceManagerHelperImpl;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

@SuppressWarnings({"UnstableApiUsage", "unused"})
public final class AtmosphericResourceManagerHelper {

    public static boolean registerBuiltinDataPack(ResourceLocation id, ModContainer container, ResourcePackActivationType activationType) {
        return ResourceManagerHelperImpl.registerBuiltinResourcePack(id, "datapacks/" + id.getPath(), container, activationType);
    }

    public static boolean registerBuiltinDataPack(ResourceLocation id, ModContainer container, Component displayName, ResourcePackActivationType activationType) {
        return ResourceManagerHelperImpl.registerBuiltinResourcePack(id, "datapacks/" + id.getPath(), container, displayName, activationType);
    }

    public static boolean registerBuiltinSomething(ResourceLocation id, ModContainer container, String subPath, ResourcePackActivationType activationType) {
        return ResourceManagerHelperImpl.registerBuiltinResourcePack(id, subPath + id.getPath(), container, activationType);
    }

    public static boolean registerBuiltinSomething(ResourceLocation id, ModContainer container, String subPath, Component displayName, ResourcePackActivationType activationType) {
        return ResourceManagerHelperImpl.registerBuiltinResourcePack(id, subPath + id.getPath(), container, displayName, activationType);
    }
}
