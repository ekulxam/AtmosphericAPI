/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
//? if 1.21.1 {
/*package survivalblock.atmosphere.atmospheric_api.mixin.item.render.client;

import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.resources.model.BlockStateModelLoader;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.profiling.ProfilerFiller;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.AlternateModelItem;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.IAmASpyglassItem;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.client.AlternateItemModelRegistryImpl;

import java.util.List;
import java.util.Map;

@SuppressWarnings("UnusedMixin")
@Mixin(ModelBakery.class)
public abstract class ModelLoaderMixin {

    @Shadow protected abstract void loadSpecialItemModelAndDependencies(ModelResourceLocation id);

    @Inject(method = "<init>", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/entity/ItemRenderer;SPYGLASS_IN_HAND_MODEL:Lnet/minecraft/client/resources/model/ModelResourceLocation;", shift = At.Shift.AFTER))
    private void loadKaleidoscopeInHandModel(BlockColors blockColors, ProfilerFiller profiler, Map<ResourceLocation, BlockModel> jsonUnbakedModels, Map<ResourceLocation, List<BlockStateModelLoader.LoadedJson>> blockStates, CallbackInfo ci) {
        Map<IAmASpyglassItem, ModelResourceLocation> spyglassModels = AlternateItemModelRegistryImpl.getSpyglassModels();
        for (Map.Entry<AlternateModelItem, ModelResourceLocation> entry : AlternateItemModelRegistryImpl.getModels().entrySet()) {
            if (entry.getKey() instanceof IAmASpyglassItem spyglass) {
                this.loadSpecialItemModelAndDependencies(spyglassModels.get(spyglass));
            } else {
                this.loadSpecialItemModelAndDependencies(entry.getValue());
            }
        }
    }
}
*///?}