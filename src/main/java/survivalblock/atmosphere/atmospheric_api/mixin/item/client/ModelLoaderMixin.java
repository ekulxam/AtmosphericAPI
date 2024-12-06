package survivalblock.atmosphere.atmospheric_api.mixin.item.client;

import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.render.model.BlockStatesLoader;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
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

@Mixin(ModelLoader.class)
public abstract class ModelLoaderMixin {

    @Shadow protected abstract void loadItemModel(ModelIdentifier id);

    @Inject(method = "<init>", at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/item/ItemRenderer;SPYGLASS_IN_HAND:Lnet/minecraft/client/util/ModelIdentifier;", shift = At.Shift.AFTER))
    private void loadKaleidoscopeInHandModel(BlockColors blockColors, Profiler profiler, Map<Identifier, JsonUnbakedModel> jsonUnbakedModels, Map<Identifier, List<BlockStatesLoader.SourceTrackedData>> blockStates, CallbackInfo ci) {
        Map<IAmASpyglassItem, ModelIdentifier> spyglassModels = AlternateItemModelRegistryImpl.getSpyglassModels();
        for (Map.Entry<AlternateModelItem, ModelIdentifier> entry : AlternateItemModelRegistryImpl.getModels().entrySet()) {
            if (entry.getKey() instanceof IAmASpyglassItem spyglass) {
                this.loadItemModel(spyglassModels.get(spyglass));
            } else {
                this.loadItemModel(entry.getValue());
            }
        }
    }
}
