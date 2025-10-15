package survivalblock.atmosphere.atmospheric_api.not_mixin.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.attribute.*;
//? if =1.21.1 {
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
//?} elif =1.21.8 {

/*import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
}
*///?}
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    //? if =1.21.1 {

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
     //?} elif =1.21.8 {

    /*@Override
    protected void readCustomData(ReadView view) {
        if (this.getWorld() != null && !this.getWorld().isClient) {
            Optional<List<EntityAttributeInstance.Packed>> optional = view.read("attributes", EntityAttributeInstance.Packed.LIST_CODEC);
            optional.ifPresent(list -> Objects.requireNonNull(this.getAttributes()).unpack(list));
        }
    }

    @Override
    protected void writeCustomData(WriteView view) {
        view.put("attributes", EntityAttributeInstance.Packed.LIST_CODEC, this.getAttributes().pack());
    }
    *///?}

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
