package survivalblock.atmosphere.atmospheric_api.not_mixin.entity;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

@SuppressWarnings("unused")
public abstract class EntityWithScale extends EntityWithAttributesImpl {

    protected float prevScale = 1.0F;

    public EntityWithScale(EntityType<? extends EntityWithAttributesImpl> type, World world) {
        super(type, world);
    }

    @Override
    public void tick() {
        super.tick();
        float l = this.getScale();
        if (l != this.prevScale) {
            this.prevScale = l;
            this.calculateDimensions();
        }
    }

    public abstract float getScale();

    @Override
    public EntityDimensions getDimensions(EntityPose pose) {
        return this.getBaseDimensions(pose).scaled(this.getScale());
    }

    protected EntityDimensions getBaseDimensions(EntityPose pose) {
        return this.getType().getDimensions();
    }
}
