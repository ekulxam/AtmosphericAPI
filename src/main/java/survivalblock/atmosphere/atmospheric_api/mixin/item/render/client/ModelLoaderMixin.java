/*
 * Copyright (c) 21:56 UTC 26 July 2026 - present ekulxam
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
//? if 1.21.1 {
/*package survivalblock.atmosphere.atmospheric_api.mixin.item.render.client;

import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.resources.model.BlockStateModelLoader;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelIdentifier;
import net.minecraft.resources.Identifier;
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

    @Shadow protected abstract void loadSpecialItemModelAndDependencies(ModelIdentifier id);

    @Inject(method = "<init>", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/entity/ItemRenderer;SPYGLASS_IN_HAND_MODEL:Lnet/minecraft/client/resources/model/ModelIdentifier;", shift = At.Shift.AFTER))
    private void loadKaleidoscopeInHandModel(BlockColors blockColors, ProfilerFiller profiler, Map<Identifier, BlockModel> jsonUnbakedModels, Map<Identifier, List<BlockStateModelLoader.LoadedJson>> blockStates, CallbackInfo ci) {
        Map<IAmASpyglassItem, ModelIdentifier> spyglassModels = AlternateItemModelRegistryImpl.getSpyglassModels();
        for (Map.Entry<AlternateModelItem, ModelIdentifier> entry : AlternateItemModelRegistryImpl.getModels().entrySet()) {
            if (entry.getKey() instanceof IAmASpyglassItem spyglass) {
                this.loadSpecialItemModelAndDependencies(spyglassModels.get(spyglass));
            } else {
                this.loadSpecialItemModelAndDependencies(entry.getValue());
            }
        }
    }
}
*///?}