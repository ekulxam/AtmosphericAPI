package survivalblock.atmosphere.atmospheric_api.mixin.entity;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import survivalblock.atmosphere.atmospheric_api.not_mixin.entity.injected_interface.AtmosphericEntityDaylightGetter;

@Mixin(Entity.class)
public abstract class EntityMixin implements AtmosphericEntityDaylightGetter {

    @Shadow public abstract World getWorld();

    @Shadow public abstract float getBrightnessAtEyes();

    @Shadow public abstract double getX();

    @Shadow public abstract double getEyeY();

    @Shadow public abstract double getZ();

    @Override
    public boolean atmospheric_api$isAffectedByDaylight() {
        World world = this.getWorld();
        if (world.isDay()) {
            float f = this.getBrightnessAtEyes();
            BlockPos blockPos = BlockPos.ofFloored(this.getX(), this.getEyeY(), this.getZ());
            return f > 0.5F && world.isSkyVisible(blockPos);
        }
        return false;
    }
}
