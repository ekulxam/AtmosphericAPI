/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
//? if 1.21.1 {
/*package survivalblock.atmosphere.atmospheric_api.not_mixin.item.client;

import java.util.HashMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.resources.model.ModelResourceLocation;
import org.jetbrains.annotations.ApiStatus;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.AlternateModelItem;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.IAmASpyglassItem;

@Environment(EnvType.CLIENT)
public final class AlternateItemModelRegistryImpl {

    private AlternateItemModelRegistryImpl() {
    }

    private static final HashMap<AlternateModelItem, ModelResourceLocation> models = new HashMap<>();

    private static final HashMap<IAmASpyglassItem, ModelResourceLocation> spyglassModels = new HashMap<>();

    @ApiStatus.Internal
    static void register(AlternateModelItem item, ModelResourceLocation modelResourceLocation) {
        models.put(item, modelResourceLocation);
    }

    @ApiStatus.Internal
    static void registerSpyglass(IAmASpyglassItem item, ModelResourceLocation modelResourceLocation) {
        spyglassModels.put(item, modelResourceLocation);
    }

    public static HashMap<AlternateModelItem, ModelResourceLocation> getModels() {
        return models;
    }

    public static HashMap<IAmASpyglassItem, ModelResourceLocation> getSpyglassModels() {
        return spyglassModels;
    }
}
*///?}