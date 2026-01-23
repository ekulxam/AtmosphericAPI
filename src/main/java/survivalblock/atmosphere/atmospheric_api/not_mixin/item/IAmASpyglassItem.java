package survivalblock.atmosphere.atmospheric_api.not_mixin.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted"})
public interface IAmASpyglassItem extends AlternateModelItem {

    ResourceLocation SPYGLASS_OVERLAY = ResourceLocation.withDefaultNamespace("textures/misc/spyglass_scope.png");

    default ResourceLocation getOverlay(ItemStack stack) {
        return SPYGLASS_OVERLAY;
    }
}
