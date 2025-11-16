package survivalblock.atmosphere.atmospheric_api.mixin.entity;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@SuppressWarnings("unused")
@Mixin(Entity.class)
public interface EntityAccessor {

    @Accessor("firstTick")
    boolean atmospheric_api$isFirstTick();

    @Accessor("firstTick")
    void atmospheric_api$setFirstTick(boolean firstTick);
}
