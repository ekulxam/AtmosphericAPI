package survivalblock.atmosphere.atmospheric_api.mixin.resourcepack.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.resource.ResourcePackProfile;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import survivalblock.atmosphere.atmospheric_api.not_mixin.resourcepack.client.injected_interface.AtmosphericClientResourcePackFinder;

import java.util.stream.Collectors;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin implements AtmosphericClientResourcePackFinder {

    @Shadow public abstract ResourcePackManager getResourcePackManager();

    public boolean atmospheric_api$isResourcePackLoaded(String name) {
        return this.getResourcePackManager().getEnabledIds().contains(name);
    }

    public boolean atmospheric_api$doesResourcePackExist(String name) {
        return this.getResourcePackManager().hasProfile(name);
    }

    @Override
    public @Nullable ResourcePackProfile atmospheric_api$getResourcePack(String name) {
        return this.getResourcePackManager().getProfile(name);
    }

    @Override
    public @Nullable ResourcePackProfile atmospheric_api$getActiveResourcePack(String name) {
        return this.getResourcePackManager().getEnabledProfiles().stream().collect(Collectors.toMap(ResourcePackProfile::getId, (profile) -> profile)).get(name);
    }

    @Override
    public boolean atmospheric_api$enableResourcePack(String name) {
        return this.getResourcePackManager().enable(name);
    }

    @Override
    public boolean atmospheric_api$disableResourcePack(String name) {
        return this.getResourcePackManager().disable(name);
    }
}
