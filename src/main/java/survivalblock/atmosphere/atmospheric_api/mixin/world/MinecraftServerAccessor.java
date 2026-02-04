/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.mixin.world;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.TickTask;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(MinecraftServer.class)
public interface MinecraftServerAccessor {
    @SuppressWarnings("unused")
    @Invoker("wrapRunnable")
    TickTask atmospheric_api$createTask(Runnable runnable);
}
