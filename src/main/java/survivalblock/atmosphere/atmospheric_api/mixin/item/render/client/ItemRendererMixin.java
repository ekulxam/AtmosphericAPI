/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
//? if 1.21.1 {
/*package survivalblock.atmosphere.atmospheric_api.mixin.item.render.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.AlternateModelItem;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.IAmASpyglassItem;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.client.AlternateItemModelRegistryImpl;

import java.util.Map;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

@SuppressWarnings("UnusedMixin")
@Mixin(ItemRenderer.class)
public class ItemRendererMixin {

    @Shadow @Final private ItemModelShaper itemModelShaper;

    @ModifyVariable(method = "render(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;ZLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;IILnet/minecraft/client/resources/model/BakedModel;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z", ordinal = 1),
            index = 8, argsOnly = true)
    private BakedModel getCustomSpyglassInventoryModel(BakedModel value, @Local(argsOnly = true) ItemStack stack) {
        if (stack.getItem() instanceof IAmASpyglassItem spyglass) {
            Map<AlternateModelItem, ModelResourceLocation> models = AlternateItemModelRegistryImpl.getModels();
            return this.itemModelShaper.getModelManager().getModel(models.get(spyglass));
        }
        return value;
    }

    @ModifyVariable(method = "render(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;ZLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;IILnet/minecraft/client/resources/model/BakedModel;)V",
            at = @At(value = "HEAD"),
            index = 8, argsOnly = true)
    private BakedModel getAlternateInventoryModel(BakedModel value, @Local(argsOnly = true) ItemDisplayContext renderMode, @Local(argsOnly = true) ItemStack stack) {
        Item item = stack.getItem();
        if (item instanceof AlternateModelItem alternateModelItem && !(item instanceof IAmASpyglassItem) && renderMode.equals(ItemDisplayContext.GUI)) {
            Map<AlternateModelItem, ModelResourceLocation> models = AlternateItemModelRegistryImpl.getModels();
            return this.itemModelShaper.getModelManager().getModel(models.get(alternateModelItem));
        }
        return value;
    }

    @ModifyExpressionValue(method = "getModel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ItemModelShaper;getItemModel(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/client/resources/model/BakedModel;"))
    private BakedModel getCustomSpyglassHandheldModel(BakedModel original, @Local(argsOnly = true) ItemStack stack) {
        if (stack.getItem() instanceof IAmASpyglassItem spyglass) {
            Map<IAmASpyglassItem, ModelResourceLocation> spyglassModels = AlternateItemModelRegistryImpl.getSpyglassModels();
            return this.itemModelShaper.getModelManager().getModel(spyglassModels.get(spyglass));
        }
        return original;
    }
}
*///?}