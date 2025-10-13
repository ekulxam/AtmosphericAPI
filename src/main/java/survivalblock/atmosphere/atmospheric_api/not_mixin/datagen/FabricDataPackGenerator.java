package survivalblock.atmosphere.atmospheric_api.not_mixin.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.util.Identifier;
import survivalblock.atmosphere.atmospheric_api.access.AtmosphericDatapackGenerator;

public final class FabricDataPackGenerator {

    public static final String DATAPACK_PATH = "datapacks";

    @SuppressWarnings("unused")
    public static FabricDataGenerator.Pack createBuiltinDataPack(FabricDataGenerator fabricDataGenerator, Identifier id) {
        return ((AtmosphericDatapackGenerator) (Object) fabricDataGenerator).atmospheric_api$createBuiltinDataPack(id);
    }

    @SuppressWarnings("unused")
    public static FabricDataGenerator.Pack createBuiltinSomething(FabricDataGenerator fabricDataGenerator, String alternatePath, Identifier id) {
        return ((AtmosphericDatapackGenerator) (Object) fabricDataGenerator).atmospheric_api$generateSomethingUnderAlternatePath(alternatePath, id);
    }
}
