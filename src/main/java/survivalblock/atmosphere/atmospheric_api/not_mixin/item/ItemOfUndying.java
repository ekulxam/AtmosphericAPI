package survivalblock.atmosphere.atmospheric_api.not_mixin.item;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.advancement.criterion.UsedTotemCriterion;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

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
    default void triggerCriterion(UsedTotemCriterion instance, ServerPlayerEntity player, ItemStack stack, Operation<Void> original, DamageSource source) {
        original.call(instance, player, stack);
    }

    default boolean shouldEmitGameEvent(LivingEntity living, ItemStack stack, DamageSource source) {
        return true;
    }

    default void activate(LivingEntity living, ItemStack stack, DamageSource source) {
        living.setHealth(1.0F);
        living.clearStatusEffects();
    }
}
