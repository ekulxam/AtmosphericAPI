package survivalblock.atmosphere.atmospheric_api.mixin.datafixer;

import net.minecraft.network.codec.PacketCodecs;
import org.spongepowered.asm.mixin.Mixin;
import survivalblock.atmosphere.atmospheric_api.not_mixin.datafixer.injected_interface.AtmosphericPacketCodecs;
import survivalblock.atmosphere.atmospheric_api.not_mixin.funny.IsThisEvenNecessary;
import survivalblock.atmosphere.atmospheric_api.not_mixin.funny.ThisIsProbablyABadIdea;

@ThisIsProbablyABadIdea
@IsThisEvenNecessary
@Deprecated
@Mixin(PacketCodecs.class)
public interface PacketCodecsMixin extends AtmosphericPacketCodecs {

}
