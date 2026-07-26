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
//? if 1.21.1 {
/*package survivalblock.atmosphere.atmospheric_api.not_mixin.item;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.advancements.critereon.UsedTotemTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

@SuppressWarnings("unused")
public interface ItemOfUndying {

    default boolean canUse(LivingEntity living, ItemStack stack, DamageSource source) {
        return true;
    }

    default boolean shouldSendPacket(LivingEntity living, ItemStack stack, DamageSource source) {
        return false;
    }

    default ItemStackOfUndyingS2CPayload getPacket(LivingEntity living, ItemStack stack, DamageSource source) {
        return new ItemStackOfUndyingS2CPayload(stack, living.getId());
    }

    default boolean shouldDecrementOnDeathCancel(LivingEntity living, ItemStack stack, int amount, DamageSource source) {
        return true;
    }

    default boolean shouldIncrementStatAndTriggerCriteria(LivingEntity living, ItemStack stack, DamageSource source) {
        return true;
    }

    // probably not a great idea to pass an Operation here
    default void triggerCriterion(UsedTotemTrigger instance, ServerPlayer player, ItemStack stack, Operation<Void> original, DamageSource source) {
        original.call(instance, player, stack);
    }

    default boolean shouldEmitGameEvent(LivingEntity living, ItemStack stack, DamageSource source) {
        return true;
    }

    default void activate(LivingEntity living, ItemStack stack, DamageSource source) {
        living.setHealth(1.0F);
        living.removeAllEffects();
    }
}
*///?}