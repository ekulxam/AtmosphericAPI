package survivalblock.atmosphere.atmospheric_api.not_mixin.resourcepack.client.injected_interface;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resource.ResourcePackProfile;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public interface AtmosphericClientResourcePackFinder {

    boolean atmospheric_api$isResourcePackLoaded(String name);

    boolean atmospheric_api$doesResourcePackExist(String name);

    @Nullable
    ResourcePackProfile atmospheric_api$getResourcePack(String name);

    @Nullable
    ResourcePackProfile atmospheric_api$getActiveResourcePack(String name);

    boolean atmospheric_api$enableResourcePack(String name);

    boolean atmospheric_api$disableResourcePack(String name);
}
