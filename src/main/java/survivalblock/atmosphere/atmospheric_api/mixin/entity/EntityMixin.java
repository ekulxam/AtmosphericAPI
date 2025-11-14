package survivalblock.atmosphere.atmospheric_api.mixin.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import survivalblock.atmosphere.atmospheric_api.not_mixin.entity.injected_interface.AtmosphericEntityDaylightGetter;

@Mixin(Entity.class)
public abstract class EntityMixin implements AtmosphericEntityDaylightGetter {

    @Shadow public abstract Level level();

    @Shadow public abstract float getLightLevelDependentMagicValue();

    @Shadow public abstract double getX();

    @Shadow public abstract double getEyeY();

    @Shadow public abstract double getZ();

    @Override
    public boolean atmospheric_api$isAffectedByDaylight() {
        Level world = this.level();
        if (world.isDay()) {
            float f = this.getLightLevelDependentMagicValue();
            BlockPos blockPos = BlockPos.containing(this.getX(), this.getEyeY(), this.getZ());
            return f > 0.5F && world.canSeeSky(blockPos);
        }
        return false;
    }
}
