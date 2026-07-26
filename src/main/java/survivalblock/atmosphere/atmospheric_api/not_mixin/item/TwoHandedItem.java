/*
 * Copyright (c) 21:56 UTC 26 July 2026 - present ekulxam
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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