//? if 1.21.1 {
/*package survivalblock.atmosphere.atmospheric_api.mixin.item.itemstack_of_undying;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.advancements.critereon.UsedTotemTrigger;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stat;
import net.minecraft.stats.StatType;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.ItemOfUndying;

@SuppressWarnings({"UnreachableCode", "ConstantValue"})
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    public LivingEntityMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Shadow public abstract boolean hurt(DamageSource source, float amount);

    @WrapOperation(method = "checkTotemDeathProtection", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
    private boolean itemsOfUndyingAreTotemsToo(ItemStack instance, Item item, Operation<Boolean> original, DamageSource source) {
        return original.call(instance, item) || (instance.getItem() instanceof ItemOfUndying itemOfUndying && itemOfUndying.canUse((LivingEntity) (Object) this, instance, source));
    }

    @WrapWithCondition(method = "checkTotemDeathProtection", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;shrink(I)V"))
    private boolean potentiallyStopTotemDecrement(ItemStack instance, int amount, DamageSource source) {
        if (instance.getItem() instanceof ItemOfUndying itemOfUndying) {
            return itemOfUndying.shouldDecrementOnDeathCancel((LivingEntity) (Object) this, instance, amount, source);
        }
        return true;
    }

    @WrapOperation(method = "checkTotemDeathProtection", constant = @Constant(classValue = ServerPlayer.class, ordinal = 0))
    private boolean preventStatIncrementing(Object object, Operation<Boolean> original, DamageSource source, @Local(ordinal = 0) ItemStack stack, @Share("itemStackOfUndying") LocalRef<ItemStack> itemStackRef) {
        itemStackRef.set(stack);
        if (stack.getItem() instanceof ItemOfUndying itemOfUndying) {
            return original.call(object) && itemOfUndying.shouldIncrementStatAndTriggerCriteria((LivingEntity) (Object) this, stack, source);
        }
        return original.call(object);
    }

    @WrapOperation(method = "checkTotemDeathProtection", at = @At(value = "INVOKE", target = "Lnet/minecraft/stats/StatType;get(Ljava/lang/Object;)Lnet/minecraft/stats/Stat;"))
    private Stat<?> replaceItemInStat(StatType<?> instance, Object itemKey, Operation<Stat<?>> original) {
        if (!(itemKey instanceof ItemStack stack)) {
            return original.call(instance, itemKey);
        }
        if (!(stack.getItem() instanceof ItemOfUndying itemOfUndying)) {
            return original.call(instance, itemKey);
        }
        return original.call(instance, itemOfUndying);
    }

    @WrapOperation(method = "checkTotemDeathProtection", at = @At(value = "INVOKE", target = "Lnet/minecraft/advancements/critereon/UsedTotemTrigger;trigger(Lnet/minecraft/server/level/ServerPlayer;Lnet/minecraft/world/item/ItemStack;)V"))
    private void changeCriterion(UsedTotemTrigger instance, ServerPlayer player, ItemStack stack, Operation<Void> original, DamageSource source) {
        if (!(stack.getItem() instanceof ItemOfUndying itemOfUndying)) {
            original.call(instance, player, stack);
            return;
        }
        itemOfUndying.triggerCriterion(instance, player, stack, original, source);
    }

    @WrapWithCondition(method = "checkTotemDeathProtection", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;gameEvent(Lnet/minecraft/core/Holder;)V"))
    private boolean potentiallyPreventGameEvent(LivingEntity instance, Holder<?> registryEntry, DamageSource source, @Share("itemStackOfUndying") LocalRef<ItemStack> itemStackRef) {
        ItemStack stack = itemStackRef.get();
        if (stack.getItem() instanceof ItemOfUndying itemOfUndying) {
            return itemOfUndying.shouldEmitGameEvent(instance, stack, source);
        }
        return true;
    }

    @SuppressWarnings("DiscouragedShift")
    @Inject(method = "checkTotemDeathProtection", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;setHealth(F)V", shift = At.Shift.BEFORE), cancellable = true)
    private void sendPacketAndInvokeTotemLogic(DamageSource source, CallbackInfoReturnable<Boolean> cir, @Share("itemStackOfUndying") LocalRef<ItemStack> itemStackRef) {
        ItemStack stack = itemStackRef.get();
        if (!(stack.getItem() instanceof ItemOfUndying itemOfUndying)) {
            return;
        }
        LivingEntity living = (LivingEntity) (Object) this;
        if (living.level() instanceof ServerLevel serverWorld && itemOfUndying.shouldSendPacket(living, stack, source)) {
            itemOfUndying.getPacket(living, stack, source).sendGlobal(serverWorld);
        }
        itemOfUndying.activate(living, stack, source);
        cir.setReturnValue(true);
    }
}
*///?}