/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
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
import net.minecraft.client.resources.model.ModelResourceLocation;
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

    @ModifyExpressionValue(method = "render", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/entity/ItemRenderer;TRIDENT_MODEL:Lnet/minecraft/client/resources/model/ModelResourceLocation;"))
    private ModelResourceLocation getModdedTridentInventory(ModelResourceLocation original, @Local(argsOnly = true) ItemStack stack) {
        Item item = stack.getItem();
        if (TRIDENTS.containsKey(item)) {
            AtmosphericTridentRegistryImpl.Renderer renderer = TRIDENTS.get(item);
            return renderer.modelResourceLocation;
        }
        return original;
    }
}
*///?}