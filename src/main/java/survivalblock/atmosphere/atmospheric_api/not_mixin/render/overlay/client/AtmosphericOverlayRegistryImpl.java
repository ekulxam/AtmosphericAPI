package survivalblock.atmosphere.atmospheric_api.not_mixin.render.overlay.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.AlternateModelItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public final class AtmosphericOverlayRegistryImpl {

    public static final List<OverlayHolder> OVERLAY_HOLDERS = new ArrayList<>();

    public static void register(OverlayHolder overlayHolder) {
        OVERLAY_HOLDERS.add(overlayHolder);
        OVERLAY_HOLDERS.sort(null);
    }
}
