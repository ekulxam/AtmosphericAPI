/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.render.overlay.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

@ApiStatus.Internal
@Environment(EnvType.CLIENT)
public final class AtmosphericOverlayRegistryImpl {

    private AtmosphericOverlayRegistryImpl() {
    }

    private static final List<OverlayHolder> OVERLAY_HOLDERS = new ArrayList<>();

    static void register(OverlayHolder overlayHolder) {
        OVERLAY_HOLDERS.add(overlayHolder);
        OVERLAY_HOLDERS.sort(null);
    }

    static void register(ResourceLocation texture, BiFunction<Minecraft, LocalPlayer, Float> opacitySupplier) {
        register(new OverlayHolder(texture, opacitySupplier));
    }

    static void register(ResourceLocation texture, BiFunction<Minecraft, LocalPlayer, Float> opacitySupplier, boolean bypassable) {
        register(new OverlayHolder(texture, opacitySupplier, bypassable));
    }

    static void register(ResourceLocation texture, BiFunction<Minecraft, LocalPlayer, Float> opacitySupplier, int priority) {
        register(new OverlayHolder(texture, opacitySupplier, priority));
    }

    static void register(ResourceLocation texture, BiFunction<Minecraft, LocalPlayer, Float> opacitySupplier, boolean bypassable, int priority) {
        register(new OverlayHolder(texture, opacitySupplier, bypassable, priority));
    }

    public static List<OverlayHolder> getOverlayHolders() {
        return OVERLAY_HOLDERS;
    }
}
