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