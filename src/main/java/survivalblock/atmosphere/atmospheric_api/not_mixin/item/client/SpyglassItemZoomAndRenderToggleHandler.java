package survivalblock.atmosphere.atmospheric_api.not_mixin.item.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.IAmASpyglassItem;

import java.util.function.Function;

@Environment(EnvType.CLIENT)
public class SpyglassItemZoomAndRenderToggleHandler {

    public static void handleShouldZoomIn(Item item, Function<ItemStack, Boolean> function) {
        if (!(item instanceof IAmASpyglassItem spyglass)) {
            throw new IllegalArgumentException("The item must be an instance of IAmASpyglassItem!");
        }
        SpyglassItemZoomAndRenderToggleHandlerImpl.handleShouldZoomIn(spyglass, function);
    }

    public static void handleShouldRenderOverlay(Item item, Function<ItemStack, Boolean> function) {
        if (!(item instanceof IAmASpyglassItem spyglass)) {
            throw new IllegalArgumentException("The item must be an instance of IAmASpyglassItem!");
        }
        SpyglassItemZoomAndRenderToggleHandlerImpl.handleShouldRenderOverlay(spyglass, function);
    }
}
