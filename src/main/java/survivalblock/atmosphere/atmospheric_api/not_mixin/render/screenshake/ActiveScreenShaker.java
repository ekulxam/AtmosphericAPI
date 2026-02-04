/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.render.screenshake;

import net.minecraft.world.level.Level;

@SuppressWarnings("unused")
public interface ActiveScreenShaker extends ScreenShaker {

    void activate(Level world) throws IllegalStateException;
}
