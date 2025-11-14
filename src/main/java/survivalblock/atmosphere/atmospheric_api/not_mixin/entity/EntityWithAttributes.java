package survivalblock.atmosphere.atmospheric_api.not_mixin.entity;

import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

/**
 * Classes implementing this interface should extend {@link net.minecraft.world.entity.Entity}.<p>
 * Classes extending {@link net.minecraft.world.entity.LivingEntity} already have attributes and do not need to implement this class.
 * @see net.minecraft.world.entity.ai.attributes.Attributes
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public interface EntityWithAttributes {

    String ATTRIBUTES_NBT_KEY = "attributes";

    AttributeMap getAttributes();

    @Nullable
    default AttributeInstance getAttributeInstance(Holder<Attribute> attribute) {
        return this.getAttributes().getInstance(attribute);
    }

    default double getAttributeValue(Holder<Attribute> attribute) {
        return this.getAttributes().getValue(attribute);
    }

    default double getAttributeBaseValue(Holder<Attribute> attribute) {
        return this.getAttributes().getBaseValue(attribute);
    }

    /**
     * In vanilla code, {@link net.minecraft.server.level.ServerEntity#sendPairingData(ServerPlayer, Consumer)} handles attribute syncing between server and client.
     * A mixin has been created with allows instances {@link EntityWithAttributes} to have their attributes synced like this.
     * @return whether the attributes should be synced using {@link net.minecraft.server.level.ServerEntity}
     */
    default boolean shouldAutoSyncAttributes() {
        return true;
    }

    default void resetAttributes() {
        this.setAttributesFrom(new AttributeMap(this.getDefaultAttributeContainer()));
    }

    /**
     * Sets the values of the attributes from another {@link org.spongepowered.asm.mixin.injection.At}
     * @param attributeContainer the {@link AttributeMap} to set from
     * @return true if the operation succeeded
     */
    default boolean setAttributesFrom(AttributeMap attributeContainer) {
        return false;
    }

    /**
     * @return a {@link AttributeSupplier} with the entity's default attribute values
     */
    AttributeSupplier getDefaultAttributeContainer();

    /**
     * This method should create an {@link AttributeMap} with the default attribute values
     * @return a new instance of {@link AttributeMap}
     */
    default AttributeMap getDefaultAttributes() {
        return new AttributeMap(this.getDefaultAttributeContainer());
    }
}
