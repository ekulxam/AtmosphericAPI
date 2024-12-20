package survivalblock.atmosphere.atmospheric_api.not_mixin.item;

import net.minecraft.item.ItemStack;

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

		public static boolean longswordPosing = false;
		public static float angle = 0f;
	}
}