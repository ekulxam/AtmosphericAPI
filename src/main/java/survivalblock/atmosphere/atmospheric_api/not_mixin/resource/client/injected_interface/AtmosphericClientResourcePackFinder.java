package survivalblock.atmosphere.atmospheric_api.not_mixin.resource.client.injected_interface;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resource.ResourcePackProfile;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public interface AtmosphericClientResourcePackFinder {

    default boolean atmospheric_api$isResourcePackLoaded(String name) {
        return false;
    }

    default boolean atmospheric_api$doesResourcePackExist(String name) {
        return false;
    }

    @Nullable
    default ResourcePackProfile atmospheric_api$getResourcePack(String name) {
        return null;
    }

    @Nullable
    default ResourcePackProfile atmospheric_api$getActiveResourcePack(String name) {
        return null;
    }

    default boolean atmospheric_api$enableResourcePack(String name) {
        return false;
    }

    default boolean atmospheric_api$disableResourcePack(String name) {
        return false;
    }
}
