package survivalblock.atmosphere.atmospheric_api.not_mixin.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

/**
 * A {@link AABB} with zero side length. This box does not intersect anything.
 * Using the operations of {@link AABB} on this class simply does nothing and does NOT
 * create a new instance. For example, see {@link AABB#setMinX(double)} and {@link BoxWithNoAtmosphere#setMinX(double)}
 */
@SuppressWarnings("unused")
public class BoxWithNoAtmosphere extends AABB {

    public static final BoxWithNoAtmosphere INSTANCE = new BoxWithNoAtmosphere(); // the default instance
    public final Vec3 zero = new Vec3(0.0, 0.0, 0.0); // creates another instance of the zero vector

    public BoxWithNoAtmosphere() {
        super(0, 0, 0, 0, 0, 0);
    }

    @Override
    public AABB setMinX(double minX) {
        return this;
    }

    @Override
    public AABB setMinY(double minY) {
        return this;
    }

    @Override
    public AABB setMinZ(double minZ) {
        return this;
    }

    @Override
    public AABB setMaxX(double maxX) {
        return this;
    }

    @Override
    public AABB setMaxY(double maxY) {
        return this;
    }

    @Override
    public AABB setMaxZ(double maxZ) {
        return this;
    }

    @Override
    public double min(Direction.Axis axis) {
        return 0;
    }

    @Override
    public double max(Direction.Axis axis) {
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
    public AABB contract(double x, double y, double z) {
        return this;
    }

    @Override
    public AABB expandTowards(Vec3 scale) {
        return this;
    }

    @Override
    public AABB expandTowards(double x, double y, double z) {
        return this;
    }

    @Override
    public AABB inflate(double x, double y, double z) {
        return this;
    }

    @Override
    public AABB inflate(double value) {
        return this;
    }

    @Override
    public AABB intersect(AABB box) {
        return this;
    }

    @Override
    public AABB minmax(AABB box) {
        return this;
    }

    @Override
    public AABB move(double x, double y, double z) {
        return this;
    }

    @Override
    public AABB move(BlockPos blockPos) {
        return this;
    }

    @Override
    public AABB move(Vec3 vec) {
        return this;
    }

    @Override
    public AABB move(Vector3f offset) {
        return this;
    }

    @Override
    public boolean intersects(AABB box) {
        return false;
    }

    @Override
    public boolean intersects(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return false;
    }

    @Override
    public boolean intersects(Vec3 pos1, Vec3 pos2) {
        return false;
    }

    @Override
    public boolean contains(Vec3 pos) {
        return false;
    }

    @Override
    public boolean contains(double x, double y, double z) {
        return false;
    }

    @Override
    public double getSize() {
        return 0;
    }

    @Override
    public double getXsize() {
        return 0;
    }

    @Override
    public double getYsize() {
        return 0;
    }

    @Override
    public double getZsize() {
        return 0;
    }

    @Override
    public AABB deflate(double x, double y, double z) {
        return this;
    }

    @Override
    public AABB deflate(double value) {
        return this;
    }

    @Override
    public Vec3 getCenter() {
        return this.zero;
    }

    //? if =1.21.1 {
    /*@Override
    public Vec3 getBottomCenter() {
        return this.zero;
    }
    *///?} elif =1.21.8 {
    @Override
    public Vec3 getBottomCenter() {
        return this.zero;
    }
    //?}

    @Override
    public Vec3 getMinPosition() {
        return this.zero;
    }

    @Override
    public Vec3 getMaxPosition() {
        return this.zero;
    }
}
