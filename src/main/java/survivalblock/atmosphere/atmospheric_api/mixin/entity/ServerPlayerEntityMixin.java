package survivalblock.atmosphere.atmospheric_api.mixin.entity;

import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.server.ServerAdvancementLoader;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;
import survivalblock.atmosphere.atmospheric_api.not_mixin.entity.injected_interface.AtmosphericPlayerAdvancementGranter;

import java.util.Objects;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin implements AtmosphericPlayerAdvancementGranter {

    @Override
    public boolean atmospheric_api$grantAdvancement(ServerPlayerEntity serverPlayer, Identifier advancementId, boolean verboseLogging) {
        //noinspection DataFlowIssue (server should not be null already)
        ServerAdvancementLoader loader = serverPlayer.getServer().getAdvancementLoader();
        AdvancementEntry advancementEntry = loader.get(advancementId);
        try {
            Objects.requireNonNull(advancementEntry, "Advancement entry cannot be null!");
            AdvancementProgress advancementProgress = serverPlayer.getAdvancementTracker().getProgress(advancementEntry);
            if (advancementProgress.isDone()) {
                return false;
            }
            for (String string : advancementProgress.getUnobtainedCriteria()) {
                serverPlayer.getAdvancementTracker().grantCriterion(advancementEntry, string);
                return true;
            }
        } catch (Throwable throwable) {
            if (verboseLogging) {
                AtmosphericAPI.LOGGER.error("An error occured while trying to manually grant an advancement!", throwable);
            }
        }
        return false;
    }
}
