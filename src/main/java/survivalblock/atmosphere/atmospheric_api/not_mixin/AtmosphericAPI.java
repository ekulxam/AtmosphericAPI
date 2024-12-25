package survivalblock.atmosphere.atmospheric_api.not_mixin;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import survivalblock.atmosphere.atmospheric_api.not_mixin.datagen.ExperimentalDamageTypeProvider;

public class AtmosphericAPI implements ModInitializer {
	public static final String MOD_ID = "atmospheric_api";

	public static final Logger LOGGER = LoggerFactory.getLogger("Atmospheric API");
	public static boolean isConnectorLoaded = false;

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
}