package survivalblock.atmosphere.atmospheric_api.not_mixin.item.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.IAmASpyglassItem;

import java.util.HashMap;
import java.util.function.Function;

@Environment(EnvType.CLIENT)
public final class SpyglassItemZoomAndRenderToggleHandlerImpl {

    private static final HashMap<IAmASpyglassItem, Function<ItemStack, Boolean>> zoomHandler = new HashMap<>();

    private static final HashMap<IAmASpyglassItem, Function<ItemStack, Boolean>> overlayHandler = new HashMap<>();

    @ApiStatus.Internal
    static void handleShouldZoomIn(IAmASpyglassItem item, Function<ItemStack, Boolean> function) {
        zoomHandler.put(item, function);
    }

    @ApiStatus.Internal
    static void handleShouldRenderOverlay(IAmASpyglassItem item, Function<ItemStack, Boolean> function) {
        overlayHandler.put(item, function);
    }

    public static HashMap<IAmASpyglassItem, Function<ItemStack, Boolean>> getZoomHandler() {
        return zoomHandler;
    }

    public static HashMap<IAmASpyglassItem, Function<ItemStack, Boolean>> getOverlayHandler() {
        return overlayHandler;
    }

}
