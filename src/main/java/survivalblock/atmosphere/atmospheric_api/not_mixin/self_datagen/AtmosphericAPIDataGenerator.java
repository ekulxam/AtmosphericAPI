package survivalblock.atmosphere.atmospheric_api.not_mixin.self_datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.jetbrains.annotations.ApiStatus;
import survivalblock.atmosphere.atmospheric_api.access.AtmosphericDatapackGenerator;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;

@ApiStatus.Internal
@SuppressWarnings("unused")
public class AtmosphericAPIDataGenerator implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		((AtmosphericDatapackGenerator) (Object) fabricDataGenerator).atmospheric_api$createBuiltinDataPack(AtmosphericAPI.id("test"));
	}
}
