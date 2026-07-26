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
package survivalblock.atmosphere.atmospheric_api.not_mixin;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.ApiStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import survivalblock.atmosphere.atmospheric_api.not_mixin.funny.ReadOnly;

@SuppressWarnings("unused")
public class AtmosphericAPI implements ModInitializer {

	public static final String MOD_ID = "atmospheric_api";

	public static final Logger LOGGER = LoggerFactory.getLogger("Atmospheric API");

	@ReadOnly
	@ApiStatus.Internal
	public static boolean isConnectorLoaded = false;

	public static final boolean development = FabricLoader.getInstance().isDevelopmentEnvironment();

	@Override
	public void onInitialize() {
		resetIsConnectorLoaded();
		//Extensions.load();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

	@SuppressWarnings("UnusedReturnValue")
	public static boolean resetIsConnectorLoaded() {
		isConnectorLoaded = FabricLoader.getInstance().isModLoaded("connector");
		return isConnectorLoaded;
	}

	public static boolean isConnectorLoaded() {
		return isConnectorLoaded;
	}
}