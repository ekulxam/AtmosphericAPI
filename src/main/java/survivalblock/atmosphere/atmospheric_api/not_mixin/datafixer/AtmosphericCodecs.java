package survivalblock.atmosphere.atmospheric_api.not_mixin.datafixer;

import com.mojang.serialization.Codec;
import survivalblock.atmosphere.atmospheric_api.not_mixin.funny.BadAtProgramming;
import survivalblock.atmosphere.atmospheric_api.not_mixin.util.Duo;

import java.util.List;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

@SuppressWarnings("unused")
public interface AtmosphericCodecs {

    // a codec for boxes, but I don't know why you would need to use this
    // I feel like at this point I'm just making codecs for the sake of making codecs
    Codec<AABB> AABB = duo(Vec3.CODEC).xmap(duo -> new AABB(duo.getFirst(), duo.getLast()),
            box -> new Duo<>(box.getMinPosition(), box.getMaxPosition()));

    @BadAtProgramming
    static <E> Codec<Duo<E>> duo(Codec<E> codec) {
        return codec.listOf(2, 2).xmap(Duo::new, List::copyOf);
    }
}
