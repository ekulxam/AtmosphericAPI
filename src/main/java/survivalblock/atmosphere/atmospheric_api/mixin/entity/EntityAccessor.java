package survivalblock.atmosphere.atmospheric_api.mixin.entity;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@SuppressWarnings("unused")
@Mixin(Entity.class)
public interface EntityAccessor {

    @Accessor("firstUpdate")
    boolean atmospheric_api$isFirstUpdate();

    @Accessor("firstUpdate")
    void atmospheric_api$setFirstUpdate(boolean firstUpdate);
}
