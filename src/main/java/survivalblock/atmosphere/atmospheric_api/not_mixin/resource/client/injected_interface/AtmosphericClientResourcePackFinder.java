/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.resource.client.injected_interface;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.repository.Pack;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public interface AtmosphericClientResourcePackFinder {

    default boolean atmospheric_api$isResourcePackLoaded(String name) {
        return false;
    }

    default boolean atmospheric_api$doesResourcePackExist(String name) {
        return false;
    }

    @Nullable
    default Pack atmospheric_api$getResourcePack(String name) {
        return null;
    }

    @Nullable
    default Pack atmospheric_api$getActiveResourcePack(String name) {
        return null;
    }

    default boolean atmospheric_api$enableResourcePack(String name) {
        return false;
    }

    default boolean atmospheric_api$disableResourcePack(String name) {
        return false;
    }

    default boolean atmospheric_api$isResourcePackLoaded(ResourceLocation id) {
        return this.atmospheric_api$isResourcePackLoaded(id.toString());
    }

    default boolean atmospheric_api$doesResourcePackExist(ResourceLocation id) {
        return this.atmospheric_api$doesResourcePackExist(id.toString());
    }

    @Nullable
    default Pack atmospheric_api$getResourcePack(ResourceLocation id) {
        return this.atmospheric_api$getResourcePack(id.toString());
    }

    @Nullable
    default Pack atmospheric_api$getActiveResourcePack(ResourceLocation id) {
        return this.atmospheric_api$getActiveResourcePack(id.toString());
    }

    default boolean atmospheric_api$enableResourcePack(ResourceLocation id) {
        return this.atmospheric_api$enableResourcePack(id.toString());
    }

    default boolean atmospheric_api$disableResourcePack(ResourceLocation id) {
        return this.atmospheric_api$disableResourcePack(id.toString());
    }
}
