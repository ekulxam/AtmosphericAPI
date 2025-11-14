package survivalblock.atmosphere.atmospheric_api.not_mixin.render.overlay.client;

import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;

@SuppressWarnings("ClassCanBeRecord")
public class OverlayHolder implements Comparable<OverlayHolder> {

    public static final int DEFAULT_PRIORITY = 1000;
    public static final boolean BYPASSABLE_BY_DEFAULT = true;

    protected final ResourceLocation texture;
    protected final BiFunction<Minecraft, LocalPlayer, Float> opacityProvider;
    protected final boolean bypassable;
    protected final int priority;

    public OverlayHolder(ResourceLocation texture, BiFunction<Minecraft, LocalPlayer, Float> opacityProvider) {
        this(texture, opacityProvider, BYPASSABLE_BY_DEFAULT, DEFAULT_PRIORITY);
    }

    public OverlayHolder(ResourceLocation texture, BiFunction<Minecraft, LocalPlayer, Float> opacityProvider, int priority) {
        this(texture, opacityProvider, BYPASSABLE_BY_DEFAULT, priority);
    }

    public OverlayHolder(ResourceLocation texture, BiFunction<Minecraft, LocalPlayer, Float> opacityProvider, boolean bypassable) {
        this(texture, opacityProvider, bypassable, DEFAULT_PRIORITY);
    }

    public OverlayHolder(ResourceLocation texture, BiFunction<Minecraft, LocalPlayer, Float> opacityProvider, boolean bypassable, int priority) {
        this.texture = texture;
        this.opacityProvider = opacityProvider;
        this.bypassable = bypassable;
        this.priority = priority;
    }

    public ResourceLocation getTexture() {
        return this.texture;
    }

    public boolean isBypassable() {
        return this.bypassable;
    }

    public Float getOpacity(@NotNull Minecraft client, @NotNull LocalPlayer player) {
        return this.opacityProvider.apply(client, player);
    }

    @Override
    public int compareTo(@NotNull OverlayHolder other) {
        return other.priority - this.priority;
    }
}
