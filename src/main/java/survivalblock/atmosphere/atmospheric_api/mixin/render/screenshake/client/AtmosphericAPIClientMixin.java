package survivalblock.atmosphere.atmospheric_api.mixin.render.screenshake.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPIClient;
import survivalblock.atmosphere.atmospheric_api.not_mixin.funny.ThisIsABadIdea;
import survivalblock.atmosphere.atmospheric_api.not_mixin.render.screenshake.ScreenShakePreventerRegistry;
import survivalblock.atmosphere.atmospheric_api.not_mixin.render.screenshake.client.ClientScreenShaker;

import static survivalblock.atmosphere.atmospheric_api.not_mixin.render.screenshake.ScreenShaker.DISABLE_ALL_SCREENSHAKERS_RESOURCE_PACK;

@ApiStatus.Internal
@ThisIsABadIdea(ThisIsABadIdea.LevelsOfHorrendousness.PROBABLY)
@Environment(EnvType.CLIENT)
@Mixin(value = AtmosphericAPIClient.class, remap = false)
public class AtmosphericAPIClientMixin {

    @Inject(method = "onInitializeClient", at = @At("HEAD"))
    private void invokeInitializeScreenShakers(CallbackInfo ci) {
        FabricLoader.getInstance().getModContainer(AtmosphericAPI.MOD_ID).ifPresent(modContainer -> {
            ResourceManagerHelper.registerBuiltinResourcePack(DISABLE_ALL_SCREENSHAKERS_RESOURCE_PACK, modContainer, Text.translatable("resourcePack.atmospheric_api.disableallscreenshakers.name"), ResourcePackActivationType.DEFAULT_ENABLED);
        });
        ScreenShakePreventerRegistry.ALLOW_SHAKING.register((screenShaker -> !MinecraftClient.getInstance().atmospheric_api$isResourcePackLoaded(DISABLE_ALL_SCREENSHAKERS_RESOURCE_PACK)));
        ClientScreenShaker.initialize();
    }
}
