/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.item;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted"})
public interface IAmASpyglassItem extends AlternateModelItem {

    Identifier SPYGLASS_OVERLAY = Identifier.withDefaultNamespace("textures/misc/spyglass_scope.png");

    default Identifier getOverlay(ItemStack stack) {
        return SPYGLASS_OVERLAY;
    }
}
