package survivalblock.atmosphere.atmospheric_api.not_mixin.render.overlay.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.function.BiFunction;

public class OverlayHolder implements Comparable<OverlayHolder> {

    protected final Identifier texture;
    protected final BiFunction<MinecraftClient, ClientPlayerEntity, Float> opacityProvider;
    protected final boolean bypassable;
    protected final int priority;

    public OverlayHolder(Identifier texture, BiFunction<MinecraftClient, ClientPlayerEntity, Float> opacityProvider, boolean bypassable) {
        this(texture, opacityProvider, bypassable, 1000);
    }

    public OverlayHolder(Identifier texture, BiFunction<MinecraftClient, ClientPlayerEntity, Float> opacityProvider, boolean bypassable, int priority) {
        this.texture = texture;
        this.opacityProvider = opacityProvider;
        this.bypassable = bypassable;
        this.priority = priority;
    }

    public Identifier getTexture() {
        return this.texture;
    }

    public boolean isBypassable() {
        return this.bypassable;
    }

    public Float getOpacity(@NotNull MinecraftClient client, @NotNull ClientPlayerEntity player) {
        return this.opacityProvider.apply(client, player);
    }
    @Override
    public int compareTo(@NotNull OverlayHolder other) {
        return other.priority - this.priority;
    }
}
