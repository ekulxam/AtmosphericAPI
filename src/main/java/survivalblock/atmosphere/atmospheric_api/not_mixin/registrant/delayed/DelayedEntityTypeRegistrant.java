package survivalblock.atmosphere.atmospheric_api.not_mixin.registrant.delayed;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.function.Function;

@SuppressWarnings("unused")
public class DelayedEntityTypeRegistrant extends DelayedRegistrant<EntityType<?>> {
    protected DelayedEntityTypeRegistrant(String modId, Registry<EntityType<?>> registry) {
        super(modId, registry);
    }

    protected DelayedEntityTypeRegistrant(Function<String, ResourceLocation> idFunction, Registry<EntityType<?>> registry) {
        super(idFunction, registry);
    }

    public DelayedEntityTypeRegistrant(String modId) {
        this(modId, BuiltInRegistries.ENTITY_TYPE);
    }

    public DelayedEntityTypeRegistrant(Function<String, ResourceLocation> idFunction) {
        this(idFunction, BuiltInRegistries.ENTITY_TYPE);
    }

    public <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        return this.register(name, builder.build());
    }

    public <T extends Entity> EntityType<T> register(String name, EntityType<T> entityType) {
        return super.register(name, entityType);
    }
}
