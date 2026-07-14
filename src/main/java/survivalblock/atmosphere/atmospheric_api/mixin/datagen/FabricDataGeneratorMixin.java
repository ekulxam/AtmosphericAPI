/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.mixin.datagen;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.atmospheric_api.access.AtmosphericDatapackGenerator;
import survivalblock.atmosphere.atmospheric_api.not_mixin.datagen.FabricDataPackGenerator;

import java.nio.file.Path;
import java.util.function.Supplier;

@Mixin(value = FabricDataGenerator.class, remap = false)
public abstract class FabricDataGeneratorMixin implements AtmosphericDatapackGenerator {

    @Unique
    protected boolean atmospheric_api$isGeneratingDatapack = false;

    @Unique
    protected boolean atmospheric_api$isUsingAlternatePath = false;

    @Unique
    protected String atmospheric_api$alternatePath = "";

    @Shadow public abstract FabricDataGenerator.Pack createBuiltinResourcePack(Identifier id);

    // https://discord.com/channels/507304429255393322/507982478276034570/1316872891383681145
    @WrapOperation(method = "createBuiltinResourcePack", at = @At(value = "INVOKE", target = "Ljava/nio/file/Path;resolve(Ljava/lang/String;)Ljava/nio/file/Path;"))
    private Path replaceWithAlternateString(Path instance, String other, Operation<Path> original) {
        if (this.atmospheric_api$isGeneratingDatapack) {
            return original.call(instance, FabricDataPackGenerator.DATAPACK_PATH);
        } else if (this.atmospheric_api$isUsingAlternatePath) {
            return original.call(instance, this.atmospheric_api$alternatePath);
        }
        return original.call(instance, other);
    }

    @Unique
    private <T> T atmospheric_api$wrapDatapack(Supplier<T> supplier) {
        this.atmospheric_api$isGeneratingDatapack = true;
        T obj = supplier.get();
        this.atmospheric_api$isGeneratingDatapack = false;
        return obj;
    }

    @Unique
    private <T> T atmospheric_api$wrapAlternatePath(String path, Supplier<T> supplier) {
        this.atmospheric_api$isUsingAlternatePath = true;
        this.atmospheric_api$alternatePath = path == null ? "" : path;
        T obj = supplier.get();
        this.atmospheric_api$isUsingAlternatePath = false;
        this.atmospheric_api$alternatePath = "";
        return obj;
    }

    @Override
    public FabricDataGenerator.Pack atmospheric_api$createBuiltinDataPack(Identifier id) {
        return this.atmospheric_api$wrapDatapack(() -> createBuiltinResourcePack(id));
    }

    @Override
    public FabricDataGenerator.Pack atmospheric_api$generateSomethingUnderAlternatePath(String path, Identifier id) {
        return this.atmospheric_api$wrapAlternatePath(path, () -> createBuiltinResourcePack(id));
    }
}
