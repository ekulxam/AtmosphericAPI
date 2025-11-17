package survivalblock.atmosphere.atmospheric_api.mixin.entity;

import net.minecraft.world.entity.projectile.Projectile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Projectile.class)
public interface ProjectileEntityAccessor {

    @SuppressWarnings("unused")
    @Accessor("leftOwner")
    boolean atmospheric_api$hasLeftOwner();
}
