/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
//? if >=1.21.11 {
package survivalblock.atmosphere.atmospheric_api.mixin.render.screenshake.client;

import net.minecraft.client.Camera;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@SuppressWarnings({"UnusedMixin", "unused"})
@Mixin(Camera.class)
public interface CameraAccessor {
    @Invoker("setPosition")
    void atmospheric_api$invokeSetPosition(Vec3 position);
}
//?}