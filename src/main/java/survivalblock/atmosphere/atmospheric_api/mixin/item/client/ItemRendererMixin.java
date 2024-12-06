package survivalblock.atmosphere.atmospheric_api.mixin.item.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.AlternateModelItem;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.IAmASpyglassItem;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.client.AlternateItemModelRegistryImpl;

import java.util.Map;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {

    @Shadow @Final private ItemModels models;

    @ModifyVariable(method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 1),
            index = 8, argsOnly = true)
    private BakedModel getCustomSpyglassInventoryModel(BakedModel value, @Local(argsOnly = true) ItemStack stack) {
        if (stack.getItem() instanceof IAmASpyglassItem spyglass) {
            Map<AlternateModelItem, ModelIdentifier> models = AlternateItemModelRegistryImpl.getModels();
            return this.models.getModelManager().getModel(models.get(spyglass));
        }
        return value;
    }

    @ModifyVariable(method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V",
            at = @At(value = "HEAD"),
            index = 8, argsOnly = true)
    private BakedModel getAlternateInventoryModel(BakedModel value, @Local(argsOnly = true) ModelTransformationMode renderMode, @Local(argsOnly = true) ItemStack stack) {
        Item item = stack.getItem();
        if (item instanceof AlternateModelItem alternateModelItem && !(item instanceof IAmASpyglassItem) && renderMode.equals(ModelTransformationMode.GUI)) {
            Map<AlternateModelItem, ModelIdentifier> models = AlternateItemModelRegistryImpl.getModels();
            return this.models.getModelManager().getModel(models.get(alternateModelItem));
        }
        return value;
    }

    @ModifyExpressionValue(method = "getModel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/ItemModels;getModel(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/client/render/model/BakedModel;"))
    private BakedModel getCustomSpyglassHandheldModel(BakedModel original, @Local(argsOnly = true) ItemStack stack) {
        if (stack.getItem() instanceof IAmASpyglassItem spyglass) {
            Map<IAmASpyglassItem, ModelIdentifier> spyglassModels = AlternateItemModelRegistryImpl.getSpyglassModels();
            return this.models.getModelManager().getModel(spyglassModels.get(spyglass));
        }
        return original;
    }
}
