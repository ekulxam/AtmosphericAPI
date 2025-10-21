package survivalblock.atmosphere.atmospheric_api.not_mixin.render.overlay.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.List;

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

    public static List<OverlayHolder> getOverlayHolders() {
        return OVERLAY_HOLDERS;
    }
}
