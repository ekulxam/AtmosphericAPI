package survivalblock.atmosphere.atmospheric_api.not_mixin.item.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.IAmASpyglassItem;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.TwoHandedItem;

import java.util.HashMap;
import java.util.function.Function;

@Environment(EnvType.CLIENT)
public final class AtmosphericSpecialItemRenderHandlerImpl {

    private static final HashMap<IAmASpyglassItem, Function<ItemStack, Boolean>> shouldRenderZoomHandler = new HashMap<>();

    private static final HashMap<IAmASpyglassItem, Function<ItemStack, Boolean>> shouldRenderOverlayHandler = new HashMap<>();

    private static final HashMap<TwoHandedItem, Function<ItemStack, Boolean>> shouldRenderTwoHandedHandler = new HashMap<>();

    @ApiStatus.Internal
    static void handleShouldZoomIn(IAmASpyglassItem item, Function<ItemStack, Boolean> function) {
        shouldRenderZoomHandler.put(item, function);
    }

    @ApiStatus.Internal
    static void handleShouldRenderOverlay(IAmASpyglassItem item, Function<ItemStack, Boolean> function) {
        shouldRenderOverlayHandler.put(item, function);
    }

    @ApiStatus.Internal
    static void handleShouldRenderTwoHanded(TwoHandedItem item, Function<ItemStack, Boolean> function) {
        shouldRenderTwoHandedHandler.put(item, function);
    }

    public static HashMap<IAmASpyglassItem, Function<ItemStack, Boolean>> getZoomHandler() {
        return shouldRenderZoomHandler;
    }

    public static HashMap<IAmASpyglassItem, Function<ItemStack, Boolean>> getOverlayHandler() {
        return shouldRenderOverlayHandler;
    }

    public static HashMap<TwoHandedItem, Function<ItemStack, Boolean>> getTwoHandedHandler() {
        return shouldRenderTwoHandedHandler;
    }
}
