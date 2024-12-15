package survivalblock.atmosphere.atmospheric_api.mixin.entity;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.registry.RegistryWrapper;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
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
    @WrapOperation(method = "writeCustomDataToNbt", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;encode(Lnet/minecraft/registry/RegistryWrapper$WrapperLookup;)Lnet/minecraft/nbt/NbtElement;"))
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
    }
}
