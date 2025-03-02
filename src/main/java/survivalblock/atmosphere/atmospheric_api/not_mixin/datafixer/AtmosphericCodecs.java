package survivalblock.atmosphere.atmospheric_api.not_mixin.datafixer;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import survivalblock.atmosphere.atmospheric_api.not_mixin.funny.BadAtProgramming;
import survivalblock.atmosphere.atmospheric_api.not_mixin.util.Duo;

import java.util.List;

@SuppressWarnings("unused")
public interface AtmosphericCodecs {

    // a codec for boxes, but I don't know why you would need to use this
    // I feel like at this point I'm just making codecs for the sake of making codecs
    Codec<Box> BOX = duo(Vec3d.CODEC).xmap(duo -> new Box(duo.getFirst(), duo.getLast()),
            box -> new Duo<>(box.getMinPos(), box.getMaxPos()));

    @BadAtProgramming
    static <E> Codec<Duo<E>> duo(Codec<E> codec) {
        return codec.listOf(2, 2).xmap(Duo::new, List::copyOf);
    }
}
