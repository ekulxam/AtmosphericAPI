package survivalblock.atmosphere.atmospheric_api.not_mixin.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import survivalblock.atmosphere.atmospheric_api.mixin.item.spyglass.client.InGameHudAccessor;

@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted"})
public interface IAmASpyglassItem extends AlternateModelItem {

    ResourceLocation SPYGLASS_OVERLAY = InGameHudAccessor.atmospheric_api$getSpyglassOverlay();

    default ResourceLocation getOverlay(ItemStack stack) {
        return SPYGLASS_OVERLAY;
    }
}
