package survivalblock.atmosphere.atmospheric_api.not_mixin.render.overlay.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.function.BiFunction;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public final class AtmosphericOverlayRegistry {

    private AtmosphericOverlayRegistry() {
    }

    public static void registerOverlay(OverlayHolder overlayHolder) {
        AtmosphericOverlayRegistryImpl.register(overlayHolder);
    }

    public static void registerOverlay(Identifier texture, BiFunction<MinecraftClient, ClientPlayerEntity, Float> opacitySupplier, boolean bypassable) {
        registerOverlay(new OverlayHolder(texture, opacitySupplier, bypassable));
    }

    public static void registerOverlay(Identifier texture, BiFunction<MinecraftClient, ClientPlayerEntity, Float> opacitySupplier) {
        registerOverlay(texture, opacitySupplier, true);
    }
}
