package survivalblock.atmosphere.atmospheric_api.not_mixin.entity;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.entry.RegistryEntry;

/**
 * A small utility interface for instances of {@link net.minecraft.entity.projectile.PersistentProjectileEntity}
 * that want more control over {@link net.minecraft.entity.projectile.PersistentProjectileEntity#damage(DamageSource, float)}
 * without explicitly overriding the method
 */
public interface DamagingPersistentProjectile {

    RegistryEntry.Reference<DamageType> getDamageType();

    default boolean shouldIncreaseStuckArrowCount() {
        return false;
    }
}
