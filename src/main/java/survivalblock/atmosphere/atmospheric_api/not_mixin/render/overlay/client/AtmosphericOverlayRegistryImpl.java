package survivalblock.atmosphere.atmospheric_api.not_mixin.render.overlay.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import survivalblock.atmosphere.atmospheric_api.not_mixin.funny.ReadOnly;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public final class AtmosphericOverlayRegistryImpl {

    @ReadOnly
    public static final List<OverlayHolder> OVERLAY_HOLDERS = new ArrayList<>();

    public static void register(OverlayHolder overlayHolder) {
        OVERLAY_HOLDERS.add(overlayHolder);
        OVERLAY_HOLDERS.sort(null);
    }
}
