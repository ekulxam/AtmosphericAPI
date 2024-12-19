package survivalblock.atmosphere.atmospheric_api.mixin.item.spyglass.client;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(InGameHud.class)
public interface InGameHudAccessor {

    @Accessor("SPYGLASS_SCOPE")
    static Identifier atmospheric_api$getSpyglassOverlay() {
        throw new UnsupportedOperationException();
    }
}
