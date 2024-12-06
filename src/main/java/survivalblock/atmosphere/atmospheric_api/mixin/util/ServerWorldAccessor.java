package survivalblock.atmosphere.atmospheric_api.mixin.util;

import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.entity.EntityLookup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ServerWorld.class)
public interface ServerWorldAccessor {

    @Invoker("getEntityLookup")
    EntityLookup<Entity> atmospheric_api$invokeGetEntityLookup();
}
