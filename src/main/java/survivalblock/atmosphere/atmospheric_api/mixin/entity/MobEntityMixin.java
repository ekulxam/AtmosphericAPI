package survivalblock.atmosphere.atmospheric_api.mixin.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin extends LivingEntity {
    @Shadow protected abstract boolean isAffectedByDaylight();

    protected MobEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean atmospheric_api$isAffectedByDaylight() {
        return super.atmospheric_api$isAffectedByDaylight() || this.isAffectedByDaylight();
    }
}
