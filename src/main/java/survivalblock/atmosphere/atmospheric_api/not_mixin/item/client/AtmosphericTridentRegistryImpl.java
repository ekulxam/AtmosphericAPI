/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
//? if =1.21.1 {
/*package survivalblock.atmosphere.atmospheric_api.not_mixin.item.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.TridentModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.ApiStatus;

import java.util.HashMap;
import java.util.Map;

@ApiStatus.Internal
@Environment(EnvType.CLIENT)
public class AtmosphericTridentRegistryImpl {

    public static final Map<Item, Renderer> TRIDENTS = new HashMap<>();

    static void register(Item item, ResourceLocation texture) {
        register(item, texture, ModelResourceLocation.inventory(BuiltInRegistries.ITEM.getKey(item)));
    }

    public static void register(Item item, ResourceLocation texture, ModelResourceLocation modelResourceLocation) {
        register(item, texture, modelResourceLocation, new ModelLayerLocation(BuiltInRegistries.ITEM.getKey(item), "main"));
    }

    public static void register(Item item, ResourceLocation texture, ModelLayerLocation modelLayerLocation) {
        register(item, texture, ModelResourceLocation.inventory(BuiltInRegistries.ITEM.getKey(item)), modelLayerLocation);
    }

    public static void register(Item item, ResourceLocation texture, ModelResourceLocation modelResourceLocation, ModelLayerLocation modelLayerLocation) {
        Renderer renderer = new Renderer(texture, modelResourceLocation, modelLayerLocation);

        BuiltinItemRendererRegistry.INSTANCE.register(item, renderer);
        ResourceManagerHelper.get(PackType.CLIENT_RESOURCES).registerReloadListener(renderer);

        TRIDENTS.put(item, renderer);
    }

    public static class Renderer implements BuiltinItemRendererRegistry.DynamicItemRenderer, SimpleSynchronousResourceReloadListener {
        private Model modelTrident;
        private final ResourceLocation texture;
        public final ModelResourceLocation modelResourceLocation;
        public final ModelLayerLocation modelLayerLocation;
        private final ResourceLocation id;

        public Renderer(ResourceLocation texture, ModelResourceLocation modelResourceLocation, ModelLayerLocation modelLayerLocation) {
            this.texture = texture;
            this.modelResourceLocation = modelResourceLocation;
            this.modelLayerLocation = modelLayerLocation;
            this.id = modelLayerLocation.getModel().withPath(path -> path + "_item_renderer");
        }

        @Override
        public void render(ItemStack stack, ItemDisplayContext displayContext, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay) {
            matrices.pushPose();
            matrices.scale(1.0F, -1.0F, -1.0F);
            VertexConsumer vertexConsumer2 = ItemRenderer.getFoilBufferDirect(
                    vertexConsumers, this.modelTrident.renderType(this.texture), false, stack.hasFoil()
            );
            this.modelTrident.renderToBuffer(matrices, vertexConsumer2, light, overlay);
            matrices.popPose();
        }

        @Override
        public ResourceLocation getFabricId() {
            return this.id;
        }

        @Override
        public void onResourceManagerReload(ResourceManager resourceManager) {
            EntityModelSet entityModelLoader = Minecraft.getInstance().getEntityModels();
            this.modelTrident = new TridentModel(entityModelLoader.bakeLayer(this.modelLayerLocation));
        }
    }
}
*///?}