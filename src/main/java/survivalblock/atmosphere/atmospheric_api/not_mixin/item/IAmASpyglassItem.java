package survivalblock.atmosphere.atmospheric_api.not_mixin.item;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import survivalblock.atmosphere.atmospheric_api.mixin.item.spyglass.client.InGameHudAccessor;

@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted"})
public interface IAmASpyglassItem extends AlternateModelItem {

    default boolean shouldZoomIn(ItemStack stack) {
        return true;
    }
    default boolean shouldRenderOverlay(ItemStack stack) {
        return true;
    }
    default Identifier getOverlay(ItemStack stack) {
        return InGameHudAccessor.atmospheric_api$getSpyglassOverlay();
    }
}
