/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.level.Level;
//? if >=1.21.6 {
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
//?}

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * A basic implementation of {@link EntityWithAttributes}.
 * Entities that must implement {@link EntityWithAttributes} and do not need to extend classes other than {@link Entity} itself should extend this class
 */
@SuppressWarnings("unused")
public abstract class EntityWithAttributesImpl extends Entity implements EntityWithAttributes {

    protected final AttributeMap attributes;

    public EntityWithAttributesImpl(EntityType<? extends Entity> type, Level world) {
        super(type, world);
        this.attributes = new AttributeMap(this.getDefaultAttributeContainer());
    }

    @Override
    public void tick() {
        super.tick();
    }

    //? if =1.21.1 {

    /*@Override
    protected void readAdditionalSaveData(CompoundTag nbt) {
        if (nbt.contains(ATTRIBUTES_NBT_KEY, Tag.TAG_LIST) && this.level() != null && !this.level().isClientSide) {
            this.getAttributes().load(nbt.getList(ATTRIBUTES_NBT_KEY, Tag.TAG_COMPOUND));
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag nbt) {
        nbt.put(ATTRIBUTES_NBT_KEY, this.getAttributes().save());
    }
     *///?} elif >=1.21.8 {

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        if (this.level() != null && !this.level().isClientSide()) {
            Optional<List<AttributeInstance.Packed>> optional = input.read(ATTRIBUTES_NBT_KEY, AttributeInstance.Packed.LIST_CODEC);
            optional.ifPresent(list -> Objects.requireNonNull(this.getAttributes()).apply(list));
        }
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        output.store(ATTRIBUTES_NBT_KEY, AttributeInstance.Packed.LIST_CODEC, this.getAttributes().pack());
    }
    //?}

    @Override
    public AttributeMap getAttributes() {
        return this.attributes;
    }

    @Override
    public boolean setAttributesFrom(AttributeMap attributes) {
        if (attributes != null) {
            this.attributes.assignAllValues(attributes);
            return true;
        }
        return false;
    }
}
