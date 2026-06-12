/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.util.client;

import com.mojang.math.Axis;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.minecraft.util.StringRepresentable;

public enum IdentifiableAxis implements StringRepresentable {
    X("x", Axis.XP),
    XP("XP", Axis.XP),
    POSITIVE_X("positive_x", Axis.XP),
    XN("XN", Axis.XN),
    NEGATIVE_X("negative_x", Axis.XN),
    Y("y", Axis.YP),
    YP("YP", Axis.YP),
    POSITIVE_Y("positive_y", Axis.YP),
    YN("YN", Axis.YN),
    NEGATIVE_Y("negative_y", Axis.YN),
    Z("z", Axis.ZP),
    ZP("ZP", Axis.ZP),
    POSITIVE_Z("positive_z", Axis.ZP),
    ZN("ZN", Axis.ZN),
    NEGATIVE_Z("negative_z", Axis.ZN);

    public static final Codec<IdentifiableAxis> CODEC = StringRepresentable.fromEnum(IdentifiableAxis::values);

    private final String name;
    public final Axis axis;

    IdentifiableAxis(String name, Axis axis) {
        this.name = name;
        this.axis = axis;
    }

    public static DataResult<IdentifiableAxis> fromAxis(Axis axis) {
        if (axis == Axis.XP) {
            return DataResult.success(XP);
        }
        if (axis == Axis.XN) {
            return DataResult.success(XP);
        }
        if (axis == Axis.YP) {
            return DataResult.success(YP);
        }
        if (axis == Axis.YN) {
            return DataResult.success(YN);
        }
        if (axis == Axis.ZP) {
            return DataResult.success(ZP);
        }
        if (axis == Axis.ZN) {
            return DataResult.success(ZN);
        }
        return DataResult.error(() -> "Axis " + axis + " is not supported!");
    }

    public String getSerializedName() {
        return this.name;
    }
}