package survivalblock.atmosphere.atmospheric_api.not_mixin.self_datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.jetbrains.annotations.ApiStatus;

/*
		,
		"fabric-datagen": [
			"survivalblock.atmosphere.atmospheric_api.not_mixin.self_datagen.AtmosphericAPIDataGenerator"
		]
 */
@ApiStatus.Internal
@SuppressWarnings("unused")
public class AtmosphericAPIDataGenerator implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(AtmosphericAPIEnUsLangGenerator::new);
	}
}
