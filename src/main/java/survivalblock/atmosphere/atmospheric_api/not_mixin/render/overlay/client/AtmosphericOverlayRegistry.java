package survivalblock.atmosphere.atmospheric_api.not_mixin.render.overlay.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Identifier;
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

    public static void register(Identifier texture, BiFunction<MinecraftClient, ClientPlayerEntity, Float> opacitySupplier) {
        AtmosphericOverlayRegistryImpl.register(texture, opacitySupplier);
    }

    public static void register(Identifier texture, BiFunction<MinecraftClient, ClientPlayerEntity, Float> opacitySupplier, boolean bypassable) {
        AtmosphericOverlayRegistryImpl.register(texture, opacitySupplier, bypassable);
    }

    public static void register(Identifier texture, BiFunction<MinecraftClient, ClientPlayerEntity, Float> opacitySupplier, int priority) {
        AtmosphericOverlayRegistryImpl.register(texture, opacitySupplier, priority);
    }

    public static void register(Identifier texture, BiFunction<MinecraftClient, ClientPlayerEntity, Float> opacitySupplier, boolean bypassable, int priority) {
        AtmosphericOverlayRegistryImpl.register(texture, opacitySupplier, bypassable, priority);
    }
}
