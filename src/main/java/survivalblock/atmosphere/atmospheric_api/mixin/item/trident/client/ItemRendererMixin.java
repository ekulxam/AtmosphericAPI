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
//? if =1.21.1 {
/*package survivalblock.atmosphere.atmospheric_api.mixin.item.trident.client;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.ModelIdentifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.client.AtmosphericTridentRegistryImpl;

import static survivalblock.atmosphere.atmospheric_api.not_mixin.item.client.AtmosphericTridentRegistryImpl.TRIDENTS;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {

    @Definition(id = "is", method = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z")
    @Definition(id = "TRIDENT", field = "Lnet/minecraft/world/item/Items;TRIDENT:Lnet/minecraft/world/item/Item;")
    @Expression("?.is(TRIDENT)")
    @WrapOperation(method = {"getModel", "render"}, at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean checkModdedTrident(ItemStack instance, Item item, Operation<Boolean> original) {
        return original.call(instance, item) || TRIDENTS.containsKey(item);
    }

    @ModifyExpressionValue(method = "render", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/entity/ItemRenderer;TRIDENT_MODEL:Lnet/minecraft/client/resources/model/ModelIdentifier;"))
    private ModelIdentifier getModdedTridentInventory(ModelIdentifier original, @Local(argsOnly = true) ItemStack stack) {
        Item item = stack.getItem();
        if (TRIDENTS.containsKey(item)) {
            AtmosphericTridentRegistryImpl.Renderer renderer = TRIDENTS.get(item);
            return renderer.modelIdentifier;
        }
        return original;
    }
}
*///?}