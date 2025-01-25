package survivalblock.atmosphere.atmospheric_api.not_mixin;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
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
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
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