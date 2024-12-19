package survivalblock.atmosphere.atmospheric_api.mixin.entity;

import com.mojang.authlib.GameProfile;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.ServerAdvancementLoader;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;
import survivalblock.atmosphere.atmospheric_api.not_mixin.entity.injected_interface.AtmosphericPlayerAdvancementGranter;

import java.util.Objects;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity implements AtmosphericPlayerAdvancementGranter {

    @Shadow public abstract PlayerAdvancementTracker getAdvancementTracker();

    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @Override
    public boolean atmospheric_api$grantAdvancement(Identifier advancementId, boolean verboseLogging) {
        //noinspection DataFlowIssue (server should not be null already)
        ServerAdvancementLoader loader = this.getServer().getAdvancementLoader();
        AdvancementEntry advancementEntry = loader.get(advancementId);
        try {
            Objects.requireNonNull(advancementEntry, "Advancement entry cannot be null!");
            PlayerAdvancementTracker advancementTracker = this.getAdvancementTracker();
            AdvancementProgress advancementProgress = advancementTracker.getProgress(advancementEntry);
            if (advancementProgress.isDone()) {
                return false;
            }
            for (String string : advancementProgress.getUnobtainedCriteria()) {
                advancementTracker.grantCriterion(advancementEntry, string);
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
