/*
 * Copyright (c) 21:56 UTC 26 July 2026 - present ekulxam
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package survivalblock.atmosphere.atmospheric_api.mixin.entity;

import com.mojang.authlib.GameProfile;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.ServerAdvancementManager;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;
import survivalblock.atmosphere.atmospheric_api.not_mixin.entity.injected_interface.AtmosphericPlayerAdvancementGranter;
import survivalblock.atmosphere.atmospheric_api.not_mixin.util.Masonry;

import java.util.Objects;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerEntityMixin extends Player implements AtmosphericPlayerAdvancementGranter {

    @Shadow public abstract PlayerAdvancements getAdvancements();

    //? if =1.21.1 {
    
    /*public ServerPlayerEntityMixin(Level world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }
     *///?} elif >=1.21.8 {
    public ServerPlayerEntityMixin(Level world, GameProfile profile) {
        super(world, profile);
    }
    //?}


    @Override
    public boolean atmospheric_api$grantAdvancement(Identifier advancementId, boolean verboseLogging) {
        //noinspection DataFlowIssue (server should not be null already)
        ServerAdvancementManager loader = Masonry.getEntityServer(this).getAdvancements();
        AdvancementHolder advancementEntry = loader.get(advancementId);
        try {
            Objects.requireNonNull(advancementEntry, "Advancement entry cannot be null!");
            PlayerAdvancements advancementTracker = this.getAdvancements();
            AdvancementProgress advancementProgress = advancementTracker.getOrStartProgress(advancementEntry);
            if (advancementProgress.isDone()) {
                return false;
            }
            for (String string : advancementProgress.getRemainingCriteria()) {
                advancementTracker.award(advancementEntry, string);
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
