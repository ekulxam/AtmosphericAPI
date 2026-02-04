/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.entity;

import net.minecraft.world.phys.Vec3;

import java.util.List;

/**
 * Originally from ATTA-V
 */
@SuppressWarnings("unused")
public interface PositionContainer {

    List<Vec3> positions();
}
