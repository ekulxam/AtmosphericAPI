package survivalblock.atmosphere.atmospheric_api.mixin.datafixer;

import net.minecraft.network.codec.PacketCodecs;
import org.spongepowered.asm.mixin.Mixin;
import survivalblock.atmosphere.atmospheric_api.not_mixin.datafixer.injected_interface.AtmosphericPacketCodecs;
import survivalblock.atmosphere.atmospheric_api.not_mixin.funny.IsThisEvenNecessary;
import survivalblock.atmosphere.atmospheric_api.not_mixin.funny.ThisIsABadIdea;

@ThisIsABadIdea(ThisIsABadIdea.LevelsOfHorrendousness.PROBABLY)
@IsThisEvenNecessary(IsThisEvenNecessary.LevelsOfUnnecessity.BASCIALLY_DEPRECATED)
@Mixin(PacketCodecs.class)
public interface PacketCodecsMixin extends AtmosphericPacketCodecs {

}
