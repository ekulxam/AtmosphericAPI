package survivalblock.atmosphere.atmospheric_api.mixin.datagen;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.GameVersion;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.atmospheric_api.access.AtmosphericDatapackGenerator;

import java.nio.file.Path;

@Mixin(FabricDataGenerator.class)
public abstract class FabricDataGeneratorMixin extends DataGenerator implements AtmosphericDatapackGenerator {

    @Unique
    private static final String atmospheric_api$DATAPACK_PATH = "datapacks";

    @Unique
    private boolean atmospheric_api$isGeneratingDatapack = false;

    @Shadow public abstract FabricDataGenerator.Pack createBuiltinResourcePack(Identifier id);

    public FabricDataGeneratorMixin(Path outputPath, GameVersion gameVersion, boolean ignoreCache) {
        super(outputPath, gameVersion, ignoreCache);
    }

    // https://discord.com/channels/507304429255393322/507982478276034570/1316872891383681145
    @WrapOperation(method = "createBuiltinResourcePack", at = @At(value = "INVOKE", target = "Ljava/nio/file/Path;resolve(Ljava/lang/String;)Ljava/nio/file/Path;"))
    private Path replaceWithDataPack(Path instance, String other, Operation<Path> original) {
        if (atmospheric_api$isGeneratingDatapack) {
            return original.call(instance, atmospheric_api$DATAPACK_PATH);
        }
        return original.call(instance, other);
    }

    @Override
    public FabricDataGenerator.Pack atmospheric_api$createBuiltinDataPack(Identifier id) {
        atmospheric_api$isGeneratingDatapack = true;
        FabricDataGenerator.Pack pack = createBuiltinResourcePack(id);
        atmospheric_api$isGeneratingDatapack = false;
        return pack;
    }
}
