//? if 1.21.1 {
/*package survivalblock.atmosphere.atmospheric_api.mixin.item.itemstack_of_undying;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import net.minecraft.advancement.criterion.UsedTotemCriterion;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stat;
import net.minecraft.stat.StatType;
import net.minecraft.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.ItemOfUndying;

@MixinEnvironment("1.21.1")
@SuppressWarnings({"UnreachableCode", "ConstantValue"})
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow public abstract boolean damage(DamageSource source, float amount);

    @WrapOperation(method = "tryUseTotem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean itemsOfUndyingAreTotemsToo(ItemStack instance, Item item, Operation<Boolean> original, DamageSource source) {
        return original.call(instance, item) || (instance.getItem() instanceof ItemOfUndying itemOfUndying && itemOfUndying.canUse((LivingEntity) (Object) this, instance, source));
    }

    @WrapWithCondition(method = "tryUseTotem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;decrement(I)V"))
    private boolean potentiallyStopTotemDecrement(ItemStack instance, int amount, DamageSource source) {
        if (instance.getItem() instanceof ItemOfUndying itemOfUndying) {
            return itemOfUndying.shouldDecrementOnDeathCancel((LivingEntity) (Object) this, instance, amount, source);
        }
        return true;
    }

    @WrapOperation(method = "tryUseTotem", constant = @Constant(classValue = ServerPlayerEntity.class, ordinal = 0))
    private boolean preventStatIncrementing(Object object, Operation<Boolean> original, DamageSource source, @Local(ordinal = 0) ItemStack stack, @Share("itemStackOfUndying") LocalRef<ItemStack> itemStackRef) {
        itemStackRef.set(stack);
        if (stack.getItem() instanceof ItemOfUndying itemOfUndying) {
            return original.call(object) && itemOfUndying.shouldIncrementStatAndTriggerCriteria((LivingEntity) (Object) this, stack, source);
        }
        return original.call(object);
    }

    @WrapOperation(method = "tryUseTotem", at = @At(value = "INVOKE", target = "Lnet/minecraft/stat/StatType;getOrCreateStat(Ljava/lang/Object;)Lnet/minecraft/stat/Stat;"))
    private Stat<?> replaceItemInStat(StatType<?> instance, Object itemKey, Operation<Stat<?>> original) {
        if (!(itemKey instanceof ItemStack stack)) {
            return original.call(instance, itemKey);
        }
        if (!(stack.getItem() instanceof ItemOfUndying itemOfUndying)) {
            return original.call(instance, itemKey);
        }
        return original.call(instance, itemOfUndying);
    }

    @WrapOperation(method = "tryUseTotem", at = @At(value = "INVOKE", target = "Lnet/minecraft/advancement/criterion/UsedTotemCriterion;trigger(Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/item/ItemStack;)V"))
    private void changeCriterion(UsedTotemCriterion instance, ServerPlayerEntity player, ItemStack stack, Operation<Void> original, DamageSource source) {
        if (!(stack.getItem() instanceof ItemOfUndying itemOfUndying)) {
            original.call(instance, player, stack);
            return;
        }
        itemOfUndying.triggerCriterion(instance, player, stack, original, source);
    }

    @WrapWithCondition(method = "tryUseTotem", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;emitGameEvent(Lnet/minecraft/registry/entry/RegistryEntry;)V"))
    private boolean potentiallyPreventGameEvent(LivingEntity instance, RegistryEntry<?> registryEntry, DamageSource source, @Share("itemStackOfUndying") LocalRef<ItemStack> itemStackRef) {
        ItemStack stack = itemStackRef.get();
        if (stack.getItem() instanceof ItemOfUndying itemOfUndying) {
            return itemOfUndying.shouldEmitGameEvent(instance, stack, source);
        }
        return true;
    }

    @SuppressWarnings("DiscouragedShift")
    @Inject(method = "tryUseTotem", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setHealth(F)V", shift = At.Shift.BEFORE), cancellable = true)
    private void sendPacketAndInvokeTotemLogic(DamageSource source, CallbackInfoReturnable<Boolean> cir, @Share("itemStackOfUndying") LocalRef<ItemStack> itemStackRef) {
        ItemStack stack = itemStackRef.get();
        if (!(stack.getItem() instanceof ItemOfUndying itemOfUndying)) {
            return;
        }
        LivingEntity living = (LivingEntity) (Object) this;
        if (living.getWorld() instanceof ServerWorld serverWorld && itemOfUndying.shouldSendPacket(living, stack, source)) {
            itemOfUndying.getPacket(living, stack, source).sendGlobal(serverWorld);
        }
        itemOfUndying.activate(living, stack, source);
        cir.setReturnValue(true);
    }
}
*///?}