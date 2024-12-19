package survivalblock.atmosphere.atmospheric_api.not_mixin.item;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import survivalblock.atmosphere.atmospheric_api.mixin.item.spyglass.client.InGameHudAccessor;

@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted"})
public interface IAmASpyglassItem extends AlternateModelItem {

    default Identifier getOverlay(ItemStack stack) {
        return InGameHudAccessor.atmospheric_api$getSpyglassOverlay();
    }
}
