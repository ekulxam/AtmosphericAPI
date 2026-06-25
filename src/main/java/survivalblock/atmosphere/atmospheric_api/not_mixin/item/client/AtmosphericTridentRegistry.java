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
import net.minecraft.client.resources.model.ModelIdentifier;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public class AtmosphericTridentRegistry {

    public static void register(Item item, Identifier texture) {
        AtmosphericTridentRegistryImpl.register(item, texture);
    }

    public static void register(Item item, Identifier texture, ModelIdentifier modelIdentifier) {
        AtmosphericTridentRegistryImpl.register(item, texture, modelIdentifier);
    }

    public static void register(Item item, Identifier texture, ModelLayerLocation modelLayerLocation) {
        AtmosphericTridentRegistryImpl.register(item, texture, modelLayerLocation);
    }

    public static void register(Item item, Identifier texture, ModelIdentifier modelIdentifier, ModelLayerLocation modelLayerLocation) {
        AtmosphericTridentRegistryImpl.register(item, texture, modelIdentifier, modelLayerLocation);
    }
}
*///?}