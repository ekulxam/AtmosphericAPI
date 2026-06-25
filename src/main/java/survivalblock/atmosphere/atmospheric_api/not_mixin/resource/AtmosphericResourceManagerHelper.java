/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.resource;

//? if >=1.21.11 {
import net.fabricmc.fabric.api.resource.v1.pack.PackActivationType;
import net.fabricmc.fabric.impl.resource.ResourceLoaderImpl;
//?} else {
/*import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.fabric.impl.resource.loader.ResourceManagerHelperImpl;
*///?}
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

//~ if >=1.21.11 'ResourcePackActivationType' -> 'PackActivationType' {
@SuppressWarnings({"UnstableApiUsage", "unused"})
public final class AtmosphericResourceManagerHelper {


    //~ if >=1.21.11 'ResourceManagerHelperImpl.registerBuiltinResourcePack' -> 'ResourceLoaderImpl.registerBuiltinPack' {
    public static boolean registerBuiltinDataPack(Identifier id, ModContainer container, PackActivationType activationType) {
        return ResourceLoaderImpl.registerBuiltinPack(id, "datapacks/" + id.getPath(), container, activationType);
    }

    public static boolean registerBuiltinDataPack(Identifier id, ModContainer container, Component displayName, PackActivationType activationType) {
        return ResourceLoaderImpl.registerBuiltinPack(id, "datapacks/" + id.getPath(), container, displayName, activationType);
    }

    public static boolean registerBuiltinSomething(Identifier id, ModContainer container, String subPath, PackActivationType activationType) {
        return ResourceLoaderImpl.registerBuiltinPack(id, subPath + id.getPath(), container, activationType);
    }

    public static boolean registerBuiltinSomething(Identifier id, ModContainer container, String subPath, Component displayName, PackActivationType activationType) {
        return ResourceLoaderImpl.registerBuiltinPack(id, subPath + id.getPath(), container, displayName, activationType);
    }
    //~}
}
//~}