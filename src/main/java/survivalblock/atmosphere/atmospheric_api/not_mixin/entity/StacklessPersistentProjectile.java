package survivalblock.atmosphere.atmospheric_api.not_mixin.entity;

import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;

/**
 * A {@link net.minecraft.entity.projectile.PersistentProjectileEntity} that can/should have null or
 * {@link net.minecraft.item.ItemStack#EMPTY} as the return value of {@link PersistentProjectileEntity#getItemStack()}
 * @see survivalblock.atmosphere.atmospheric_api.mixin.entity.PersistentProjectileEntityMixin
 */
public interface StacklessPersistentProjectile {

    default boolean shouldAvoidEncodingStack() {
        ItemStack stack = ((PersistentProjectileEntity) this).getItemStack();
        return stack == null || stack.isEmpty();
    }
}
