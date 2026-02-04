/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
//? if =1.21.1 {
/*package survivalblock.atmosphere.atmospheric_api.not_mixin.item.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public class AtmosphericTridentRegistry {

    public static void register(Item item, ResourceLocation texture) {
        AtmosphericTridentRegistryImpl.register(item, texture);
    }

    public static void register(Item item, ResourceLocation texture, ModelResourceLocation modelResourceLocation) {
        AtmosphericTridentRegistryImpl.register(item, texture, modelResourceLocation);
    }

    public static void register(Item item, ResourceLocation texture, ModelLayerLocation modelLayerLocation) {
        AtmosphericTridentRegistryImpl.register(item, texture, modelLayerLocation);
    }

    public static void register(Item item, ResourceLocation texture, ModelResourceLocation modelResourceLocation, ModelLayerLocation modelLayerLocation) {
        AtmosphericTridentRegistryImpl.register(item, texture, modelResourceLocation, modelLayerLocation);
    }
}
*///?}