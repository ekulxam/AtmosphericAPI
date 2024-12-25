package survivalblock.atmosphere.atmospheric_api.access;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.util.Identifier;

// well, I can't use interface injection for things that are not mixin, so I guess we're back to duck-ing
@SuppressWarnings("unused")
public interface AtmosphericDatapackGenerator {

    default FabricDataGenerator.Pack atmospheric_api$createBuiltinDataPack(Identifier id) {
        throw new UnsupportedOperationException();
    }
}
