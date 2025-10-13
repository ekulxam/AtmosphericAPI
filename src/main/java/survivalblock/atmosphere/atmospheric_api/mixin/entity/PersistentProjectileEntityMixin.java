package survivalblock.atmosphere.atmospheric_api.mixin.entity;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import com.mojang.serialization.Codec;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.storage.WriteView;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.atmospheric_api.not_mixin.entity.DamagingPersistentProjectile;
import survivalblock.atmosphere.atmospheric_api.not_mixin.entity.StacklessPersistentProjectile;

@Mixin(PersistentProjectileEntity.class)
public class PersistentProjectileEntityMixin {

    /**
     * Avoids encoding the {@link PersistentProjectileEntity#stack} for a {@link StacklessPersistentProjectile}<p>
     * I can't use a {@link com.llamalad7.mixinextras.injector.v2.WrapWithCondition} on the {@link NbtCompound#put(String, NbtElement)} call because {@link ItemStack#encode(RegistryWrapper.WrapperLookup)} is still called
     * @param instance the {@link ItemStack} to be encoded
     * @param registries the {@link net.minecraft.registry.RegistryWrapper.WrapperLookup} provided
     * @param original the original {@link NbtCompound#put(String, NbtElement)} call
     * @return a new "empty" instance of {@link NbtCompound} if {@link StacklessPersistentProjectile#shouldAvoidEncodingStack()} is true
     */
    @SuppressWarnings("JavadocReference")
    //? if =1.21.1 {
    
/*@WrapOperation(method = "writeCustomDataToNbt", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;encode(Lnet/minecraft/registry/RegistryWrapper$WrapperLookup;)Lnet/minecraft/nbt/NbtElement;"))
    private NbtElement doNotEncodeIfStackIsEmpty(ItemStack instance, RegistryWrapper.WrapperLookup registries, Operation<NbtElement> original) {

        if (!(this instanceof StacklessPersistentProjectile stacklessPersistentProjectile)) {
            return original.call(instance, registries);
        }
        if (stacklessPersistentProjectile.shouldAvoidEncodingStack()) {
            return new NbtCompound();
        }
        return original.call(instance, registries);
    }

    @Inject(method = "writeCustomDataToNbt", at = @At(value = "RETURN"))
    private void removeItemFromNbt(NbtCompound nbt, CallbackInfo ci) {
        if (!(this instanceof StacklessPersistentProjectile stacklessPersistentProjectile)) {
            return;
        }
        if (stacklessPersistentProjectile.shouldAvoidEncodingStack()) {
            if (nbt.contains("item")) nbt.remove("item");
        }
    }     *///?} elif =1.21.8 {
    @WrapOperation(method = "writeCustomData", at = @At(value = "INVOKE", target = "Lnet/minecraft/storage/WriteView;put(Ljava/lang/String;Lcom/mojang/serialization/Codec;Ljava/lang/Object;)V", ordinal = 0), slice = @Slice(from = @At(value = "FIELD", target = "Lnet/minecraft/item/ItemStack;CODEC:Lcom/mojang/serialization/Codec;")))
    private <T> void doNotEncodeIfStackIsEmpty(WriteView instance, String s, Codec<T> tCodec, T t, Operation<Void> original) {
        if (!(this instanceof StacklessPersistentProjectile stacklessPersistentProjectile) || !stacklessPersistentProjectile.shouldAvoidEncodingStack()) {
            original.call(instance, s, tCodec, t);
        }
    }
    //?}

    @WrapWithCondition(method = "onEntityHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setStuckArrowCount(I)V"))
    private boolean notAllProjectilesAreArrows(LivingEntity instance, int stuckArrowCount, @Share("deltaStuckArrow")LocalIntRef localIntRef) {
        if ((PersistentProjectileEntity) (Object) this instanceof DamagingPersistentProjectile damagingPersistentProjectile) {
            return damagingPersistentProjectile.shouldIncreaseStuckArrowCount();
        }
        return true;
    }

    @WrapOperation(method = "onEntityHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/damage/DamageSources;arrow(Lnet/minecraft/entity/projectile/PersistentProjectileEntity;Lnet/minecraft/entity/Entity;)Lnet/minecraft/entity/damage/DamageSource;"))
    private DamageSource customDamageType(DamageSources instance, PersistentProjectileEntity source, Entity attacker, Operation<DamageSource> original) {
        if ((PersistentProjectileEntity) (Object) this instanceof DamagingPersistentProjectile damagingPersistentProjectile) {
            return new DamageSource(damagingPersistentProjectile.getDamageType(), source, attacker);
        }
        return original.call(instance, source, attacker);
    }
}
