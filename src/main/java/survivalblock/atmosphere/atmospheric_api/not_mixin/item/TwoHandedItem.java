/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.item;

import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.ApiStatus;

@SuppressWarnings("unused")
public interface TwoHandedItem {

	default TwoHandedRenderType getTwoHandedRenderType(ItemStack stack) {
		return TwoHandedRenderType.CROSSBOW;
	}

	default float angle(ItemStack stack) {
		return 0.41f;
	}

	@SuppressWarnings("UnnecessaryModifier")
    public enum TwoHandedRenderType {
		LONGSWORD,
		CROSSBOW;

		@ApiStatus.Internal
		public static boolean longswordPosing = false;
		@ApiStatus.Internal
		public static float angle = 0f;
	}
}