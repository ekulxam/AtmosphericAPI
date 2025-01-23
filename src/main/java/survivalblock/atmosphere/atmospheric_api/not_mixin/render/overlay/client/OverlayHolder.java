package survivalblock.atmosphere.atmospheric_api.not_mixin.render.overlay.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;

public class OverlayHolder {

    protected final Identifier texture;

    protected final BiFunction<MinecraftClient, ClientPlayerEntity, Float> opacityProvider;

    protected final boolean bypassable;

    public OverlayHolder(Identifier texture, BiFunction<MinecraftClient, ClientPlayerEntity, Float> opacityProvider, boolean bypassable) {
        this.texture = texture;
        this.opacityProvider = opacityProvider;
        this.bypassable = bypassable;
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
}
