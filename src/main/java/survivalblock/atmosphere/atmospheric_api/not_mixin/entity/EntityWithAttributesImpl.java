package survivalblock.atmosphere.atmospheric_api.not_mixin.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.attribute.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.world.World;

/**
 * A basic implementation of {@link EntityWithAttributes}.
 * Entities that must implement {@link EntityWithAttributes} and do not need to extend classes other than {@link Entity} itself should extend this class
 */
@SuppressWarnings("unused")
public abstract class EntityWithAttributesImpl extends Entity implements EntityWithAttributes {

    protected final AttributeContainer attributes;

    public EntityWithAttributesImpl(EntityType<? extends Entity> type, World world) {
        super(type, world);
        this.attributes = new AttributeContainer(this.getDefaultAttributeContainer());
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        if (nbt.contains(ATTRIBUTES_NBT_KEY, NbtElement.LIST_TYPE) && this.getWorld() != null && !this.getWorld().isClient) {
            this.getAttributes().readNbt(nbt.getList(ATTRIBUTES_NBT_KEY, NbtElement.COMPOUND_TYPE));
        }
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.put(ATTRIBUTES_NBT_KEY, this.getAttributes().toNbt());
    }

    @Override
    public AttributeContainer getAttributes() {
        return this.attributes;
    }

    @Override
    public boolean setAttributesFrom(AttributeContainer attributes) {
        if (attributes != null) {
            this.attributes.setFrom(attributes);
            return true;
        }
        return false;
    }
}
