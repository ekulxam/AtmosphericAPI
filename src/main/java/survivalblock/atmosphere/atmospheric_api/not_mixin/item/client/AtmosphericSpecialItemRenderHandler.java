package survivalblock.atmosphere.atmospheric_api.not_mixin.item.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.IAmASpyglassItem;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.TwoHandedItem;

import java.util.function.Function;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public class AtmosphericSpecialItemRenderHandler {

    public static void handleShouldZoomIn(Item item, Function<ItemStack, Boolean> function) {
        if (!(item instanceof IAmASpyglassItem spyglass)) {
            throw new IllegalArgumentException("The item must be an instance of IAmASpyglassItem!");
        }
        AtmosphericSpecialItemRenderHandlerImpl.handleShouldZoomIn(spyglass, function);
    }

    public static void handleShouldRenderOverlay(Item item, Function<ItemStack, Boolean> function) {
        if (!(item instanceof IAmASpyglassItem spyglass)) {
            throw new IllegalArgumentException("The item must be an instance of IAmASpyglassItem!");
        }
        AtmosphericSpecialItemRenderHandlerImpl.handleShouldRenderOverlay(spyglass, function);
    }

    public static void handleShouldRenderTwoHanded(Item item, Function<ItemStack, Boolean> function) {
        if (!(item instanceof TwoHandedItem twoHandedItem)) {
            throw new IllegalArgumentException("The item must be an instance of TwohandedItem!");
        }
        AtmosphericSpecialItemRenderHandlerImpl.handleShouldRenderTwoHanded(twoHandedItem, function);
    }
}
