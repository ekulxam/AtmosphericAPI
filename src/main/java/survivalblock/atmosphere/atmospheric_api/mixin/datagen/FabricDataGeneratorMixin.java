package survivalblock.atmosphere.atmospheric_api.mixin.datagen;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.GameVersion;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.atmospheric_api.access.AtmosphericDatapackGenerator;
import survivalblock.atmosphere.atmospheric_api.not_mixin.datagen.FabricDataPackGenerator;

import java.nio.file.Path;

@Mixin(value = FabricDataGenerator.class, remap = false)
public abstract class FabricDataGeneratorMixin extends DataGenerator implements AtmosphericDatapackGenerator {

    @Shadow public abstract FabricDataGenerator.Pack createBuiltinResourcePack(Identifier id);

    public FabricDataGeneratorMixin(Path outputPath, GameVersion gameVersion, boolean ignoreCache) {
        super(outputPath, gameVersion, ignoreCache);
    }

    // https://discord.com/channels/507304429255393322/507982478276034570/1316872891383681145
    @WrapOperation(method = "createBuiltinResourcePack", at = @At(value = "INVOKE", target = "Ljava/nio/file/Path;resolve(Ljava/lang/String;)Ljava/nio/file/Path;"))
    private Path replaceWithAlternateString(Path instance, String other, Operation<Path> original) {
        if (FabricDataPackGenerator.isGeneratingDatapack) {
            FabricDataPackGenerator.isGeneratingDatapack = false; // for some reason, this seems to be running twice? so I have to set it to false here
            return original.call(instance, FabricDataPackGenerator.DATAPACK_PATH);
        } else if (FabricDataPackGenerator.isUsingAlternatePath) {
            FabricDataPackGenerator.isUsingAlternatePath = false;
            return original.call(instance, FabricDataPackGenerator.alternatePath);
        }
        return original.call(instance, other);
    }

    @Override
    public FabricDataGenerator.Pack atmospheric_api$createBuiltinDataPack(Identifier id) {
        FabricDataPackGenerator.isGeneratingDatapack = true;
        return createBuiltinResourcePack(id);
    }

    @Override
    public FabricDataGenerator.Pack atmospheric_api$generateSomethingUnderAlternatePath(String path, Identifier id) {
        FabricDataPackGenerator.isUsingAlternatePath = true;
        FabricDataPackGenerator.alternatePath = path;
        return createBuiltinResourcePack(id);
    }
}
