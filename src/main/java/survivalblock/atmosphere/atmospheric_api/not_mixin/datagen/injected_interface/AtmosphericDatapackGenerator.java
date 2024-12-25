package survivalblock.atmosphere.atmospheric_api.not_mixin.datagen.injected_interface;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.util.Identifier;

public interface AtmosphericDatapackGenerator {

    default FabricDataGenerator.Pack atmospheric_api$createBuiltinDataPack(Identifier id) {
        throw new UnsupportedOperationException();
    }
}
