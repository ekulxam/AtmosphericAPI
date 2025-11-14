package survivalblock.atmosphere.atmospheric_api.mixin.item.spyglass.client;

import net.minecraft.client.gui.Gui;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Gui.class)
public interface InGameHudAccessor {

    @Accessor("SPYGLASS_SCOPE_LOCATION")
    static ResourceLocation atmospheric_api$getSpyglassOverlay() {
        throw new UnsupportedOperationException();
    }
}
