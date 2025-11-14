package survivalblock.atmosphere.atmospheric_api.mixin.entity;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import com.mojang.serialization.Codec;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
//? if >=1.21.6
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.atmospheric_api.not_mixin.entity.DamagingPersistentProjectile;
import survivalblock.atmosphere.atmospheric_api.not_mixin.entity.StacklessPersistentProjectile;

@Mixin(AbstractArrow.class)
public class PersistentProjectileEntityMixin {

    //? if =1.21.1 {

    /*/^*
     * Avoids encoding the {@link AbstractArrow#stack} for a {@link StacklessPersistentProjectile}<p>I can't use a {@link com.llamalad7.mixinextras.injector.v2.WrapWithCondition} on the {@link CompoundTag#put(String, Tag)} call because {@link ItemStack#save(HolderLookup.Provider)} is still called
     * @param instance the {@link ItemStack} to be encoded
     * @param registries the {@link net.minecraft.core.HolderLookup.Provider} provided
     * @param original the original {@link CompoundTag#put(String, Tag)} call
     * @return a new "empty" instance of {@link CompoundTag} if {@link StacklessPersistentProjectile#shouldAvoidEncodingStack()} is true
     ^/
    @SuppressWarnings("JavadocReference")
    @WrapOperation(method = "addAdditionalSaveData", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;save(Lnet/minecraft/core/HolderLookup$Provider;)Lnet/minecraft/nbt/Tag;"))
    private Tag doNotEncodeIfStackIsEmpty(ItemStack instance, HolderLookup.Provider registries, Operation<Tag> original) {

        if (!(this instanceof StacklessPersistentProjectile stacklessPersistentProjectile)) {
            return original.call(instance, registries);
        }
        if (stacklessPersistentProjectile.shouldAvoidEncodingStack()) {
            return new CompoundTag();
        }
        return original.call(instance, registries);
    }

    @Inject(method = "addAdditionalSaveData", at = @At(value = "RETURN"))
    private void removeItemFromNbt(CompoundTag nbt, CallbackInfo ci) {
        if (!(this instanceof StacklessPersistentProjectile stacklessPersistentProjectile)) {
            return;
        }
        if (stacklessPersistentProjectile.shouldAvoidEncodingStack()) {
            if (nbt.contains("item")) nbt.remove("item");
        }
    }     *///?} elif =1.21.8 {
    
    @WrapOperation(method = "addAdditionalSaveData", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/storage/ValueOutput;store(Ljava/lang/String;Lcom/mojang/serialization/Codec;Ljava/lang/Object;)V", ordinal = 0), slice = @Slice(from = @At(value = "FIELD", target = "Lnet/minecraft/world/item/ItemStack;CODEC:Lcom/mojang/serialization/Codec;")))
    private <T> void doNotEncodeIfStackIsEmpty(ValueOutput instance, String s, Codec<T> tCodec, T t, Operation<Void> original) {
        if (!(this instanceof StacklessPersistentProjectile stacklessPersistentProjectile) || !stacklessPersistentProjectile.shouldAvoidEncodingStack()) {
            original.call(instance, s, tCodec, t);
        }
    }
    //?}

    @WrapWithCondition(method = "onHitEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;setArrowCount(I)V"))
    private boolean notAllProjectilesAreArrows(LivingEntity instance, int stuckArrowCount, @Share("deltaStuckArrow")LocalIntRef localIntRef) {
        if ((AbstractArrow) (Object) this instanceof DamagingPersistentProjectile damagingPersistentProjectile) {
            return damagingPersistentProjectile.shouldIncreaseStuckArrowCount();
        }
        return true;
    }

    @WrapOperation(method = "onHitEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/damagesource/DamageSources;arrow(Lnet/minecraft/world/entity/projectile/AbstractArrow;Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/world/damagesource/DamageSource;"))
    private DamageSource customDamageType(DamageSources instance, AbstractArrow source, Entity attacker, Operation<DamageSource> original) {
        if ((AbstractArrow) (Object) this instanceof DamagingPersistentProjectile damagingPersistentProjectile) {
            return new DamageSource(damagingPersistentProjectile.getDamageType(), source, attacker);
        }
        return original.call(instance, source, attacker);
    }
}
