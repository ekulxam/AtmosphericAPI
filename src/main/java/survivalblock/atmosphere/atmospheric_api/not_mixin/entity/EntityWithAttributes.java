package survivalblock.atmosphere.atmospheric_api.not_mixin.entity;

import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * Classes implementing this interface should extend {@link net.minecraft.entity.Entity}.<p>
 * Classes extending {@link net.minecraft.entity.LivingEntity} already have attributes and do not need to implement this class.
 * @see net.minecraft.entity.attribute.EntityAttributes
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public interface EntityWithAttributes {

    String ATTRIBUTES_NBT_KEY = "attributes";

    AttributeContainer getAttributes();

    @Nullable
    default EntityAttributeInstance getAttributeInstance(RegistryEntry<EntityAttribute> attribute) {
        return this.getAttributes().getCustomInstance(attribute);
    }

    default double getAttributeValue(RegistryEntry<EntityAttribute> attribute) {
        return this.getAttributes().getValue(attribute);
    }

    default double getAttributeBaseValue(RegistryEntry<EntityAttribute> attribute) {
        return this.getAttributes().getBaseValue(attribute);
    }

    /**
     * In vanilla code, {@link net.minecraft.server.network.EntityTrackerEntry#sendPackets(ServerPlayerEntity, Consumer)} handles attribute syncing between server and client.
     * A mixin has been created with allows instances {@link EntityWithAttributes} to have their attributes synced like this.
     * @return whether the attributes should be synced using {@link net.minecraft.server.network.EntityTrackerEntry}
     */
    default boolean shouldAutoSyncAttributes() {
        return true;
    }

    default void resetAttributes() {
        this.setAttributesFrom(this.getDefaultAttributeContainer());
    }

    /**
     * Sets the values of the attributes from another {@link org.spongepowered.asm.mixin.injection.At}
     * @param attributeContainer the {@link AttributeContainer} to set from
     * @return true if the operation succeeded
     */
    default boolean setAttributesFrom(AttributeContainer attributeContainer) {
        return false;
    }

    /**
     * This method should create an {@link AttributeContainer} with the default attribute values
     * @return a new instance of {@link AttributeContainer}
     */
    AttributeContainer getDefaultAttributeContainer();
}
