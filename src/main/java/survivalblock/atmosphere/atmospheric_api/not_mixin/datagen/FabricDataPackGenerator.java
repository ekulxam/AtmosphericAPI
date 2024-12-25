package survivalblock.atmosphere.atmospheric_api.not_mixin.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;
import survivalblock.atmosphere.atmospheric_api.access.AtmosphericDatapackGenerator;

@SuppressWarnings("unused")
public final class FabricDataPackGenerator {

    public static final String DATAPACK_PATH = "datapacks";

    @ApiStatus.Internal
    public static boolean isGeneratingDatapack = false;

    @ApiStatus.Internal
    public static boolean isUsingAlternatePath = false;
    @ApiStatus.Internal
    public static String alternatePath = "";

    public static FabricDataGenerator.Pack createBuiltinDataPack(FabricDataGenerator fabricDataGenerator, Identifier id) {
        return ((AtmosphericDatapackGenerator) (Object) fabricDataGenerator).atmospheric_api$createBuiltinDataPack(id);
    }

    public static FabricDataGenerator.Pack createBuiltinSomething(FabricDataGenerator fabricDataGenerator, String alternatePath, Identifier id) {
        return ((AtmosphericDatapackGenerator) (Object) fabricDataGenerator).atmospheric_api$generateSomethingUnderAlternatePath(alternatePath, id);
    }
}
