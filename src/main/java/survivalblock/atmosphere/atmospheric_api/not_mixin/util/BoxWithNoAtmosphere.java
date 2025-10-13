package survivalblock.atmosphere.atmospheric_api.not_mixin.util;

import net.minecraft.util.math.*;
import org.joml.Vector3f;

/**
 * A {@link Box} with zero side length. This box does not intersect anything.
 * Using the operations of {@link Box} on this class simply does nothing and does NOT
 * create a new instance. For example, see {@link Box#withMinX(double)} and {@link BoxWithNoAtmosphere#withMinX(double)}
 */
@SuppressWarnings("unused")
public class BoxWithNoAtmosphere extends Box {

    public static final BoxWithNoAtmosphere INSTANCE = new BoxWithNoAtmosphere(); // the default instance
    public final Vec3d zero = new Vec3d(0.0, 0.0, 0.0); // creates another instance of the zero vector

    public BoxWithNoAtmosphere() {
        super(0, 0, 0, 0, 0, 0);
    }

    @Override
    public Box withMinX(double minX) {
        return this;
    }

    @Override
    public Box withMinY(double minY) {
        return this;
    }

    @Override
    public Box withMinZ(double minZ) {
        return this;
    }

    @Override
    public Box withMaxX(double maxX) {
        return this;
    }

    @Override
    public Box withMaxY(double maxY) {
        return this;
    }

    @Override
    public Box withMaxZ(double maxZ) {
        return this;
    }

    @Override
    public double getMin(Direction.Axis axis) {
        return 0;
    }

    @Override
    public double getMax(Direction.Axis axis) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof BoxWithNoAtmosphere box)) {
            return false;
        } else if (Double.compare(box.minX, this.minX) != 0) {
            return false;
        } else if (Double.compare(box.minY, this.minY) != 0) {
            return false;
        } else if (Double.compare(box.minZ, this.minZ) != 0) {
            return false;
        } else if (Double.compare(box.maxX, this.maxX) != 0) {
            return false;
        } else {
            return Double.compare(box.maxY, this.maxY) == 0 && Double.compare(box.maxZ, this.maxZ) == 0;
        }
    }

    @Override
    public int hashCode() {
        int i = Double.hashCode(this.minX);
        i = 31 * i + Double.hashCode(this.minY);
        i = 31 * i + Double.hashCode(this.minZ);
        i = 31 * i + Double.hashCode(this.maxX);
        i = 31 * i + Double.hashCode(this.maxY);
        return 31 * i + Double.hashCode(this.maxZ);
    }

    @Override
    public Box shrink(double x, double y, double z) {
        return this;
    }

    @Override
    public Box stretch(Vec3d scale) {
        return this;
    }

    @Override
    public Box stretch(double x, double y, double z) {
        return this;
    }

    @Override
    public Box expand(double x, double y, double z) {
        return this;
    }

    @Override
    public Box expand(double value) {
        return this;
    }

    @Override
    public Box intersection(Box box) {
        return this;
    }

    @Override
    public Box union(Box box) {
        return this;
    }

    @Override
    public Box offset(double x, double y, double z) {
        return this;
    }

    @Override
    public Box offset(BlockPos blockPos) {
        return this;
    }

    @Override
    public Box offset(Vec3d vec) {
        return this;
    }

    @Override
    public Box offset(Vector3f offset) {
        return this;
    }

    @Override
    public boolean intersects(Box box) {
        return false;
    }

    @Override
    public boolean intersects(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return false;
    }

    @Override
    public boolean intersects(Vec3d pos1, Vec3d pos2) {
        return false;
    }

    @Override
    public boolean contains(Vec3d pos) {
        return false;
    }

    @Override
    public boolean contains(double x, double y, double z) {
        return false;
    }

    @Override
    public double getAverageSideLength() {
        return 0;
    }

    @Override
    public double getLengthX() {
        return 0;
    }

    @Override
    public double getLengthY() {
        return 0;
    }

    @Override
    public double getLengthZ() {
        return 0;
    }

    @Override
    public Box contract(double x, double y, double z) {
        return this;
    }

    @Override
    public Box contract(double value) {
        return this;
    }

    @Override
    public Vec3d getCenter() {
        return this.zero;
    }

    //? if =1.21.1 {
    /*@Override
    public Vec3d getBottomCenter() {
        return this.zero;
    }
    *///?} elif =1.21.8 {
    @Override
    public Vec3d getHorizontalCenter() {
        return this.zero;
    }
    //?}

    @Override
    public Vec3d getMinPos() {
        return this.zero;
    }

    @Override
    public Vec3d getMaxPos() {
        return this.zero;
    }
}
