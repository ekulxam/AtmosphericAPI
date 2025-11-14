package survivalblock.atmosphere.atmospheric_api.not_mixin.render.overlay.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.BiFunction;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public final class AtmosphericOverlayRegistry {

    private AtmosphericOverlayRegistry() {
    }

    @ApiStatus.Experimental
    public static void register(OverlayHolder overlayHolder) {
        AtmosphericOverlayRegistryImpl.register(overlayHolder);
    }

    public static void register(ResourceLocation texture, BiFunction<Minecraft, LocalPlayer, Float> opacitySupplier) {
        AtmosphericOverlayRegistryImpl.register(texture, opacitySupplier);
    }

    public static void register(ResourceLocation texture, BiFunction<Minecraft, LocalPlayer, Float> opacitySupplier, boolean bypassable) {
        AtmosphericOverlayRegistryImpl.register(texture, opacitySupplier, bypassable);
    }

    public static void register(ResourceLocation texture, BiFunction<Minecraft, LocalPlayer, Float> opacitySupplier, int priority) {
        AtmosphericOverlayRegistryImpl.register(texture, opacitySupplier, priority);
    }

    public static void register(ResourceLocation texture, BiFunction<Minecraft, LocalPlayer, Float> opacitySupplier, boolean bypassable, int priority) {
        AtmosphericOverlayRegistryImpl.register(texture, opacitySupplier, bypassable, priority);
    }
}
