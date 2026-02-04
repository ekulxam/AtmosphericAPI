/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.mixin.damage_type;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.atmospheric_api.not_mixin.damage_type.AtmosphericDamageTypeGamerules;
import survivalblock.atmosphere.atmospheric_api.not_mixin.damage_type.AtmosphericDamageTypeTags;
import survivalblock.atmosphere.atmospheric_api.not_mixin.funny.ThisIsABadIdea;

@ThisIsABadIdea(ThisIsABadIdea.LevelsOfHorrendousness.PROBABLY)
@Mixin(value = Player.class, priority = 500)
public abstract class PlayerEntityMixin extends LivingEntity {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Shadow public abstract boolean isCreative();

    @Shadow public abstract boolean isSpectator();

    @ModifyExpressionValue(method = /*? =1.21.1 {*/ /*"hurt" *//*?} else {*/ "hurtServer" /*?}*/, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/damagesource/DamageSource;is(Lnet/minecraft/tags/TagKey;)Z"))
    private boolean bypassesCreativeMode(boolean original,/*? =1.21.1 {*/  /*?} else {*/ ServerLevel world, /*?}*/ DamageSource source, float amount) {
        GameRules rules = /*? =1.21.1 {*/ /*this.level() *//*?} else {*/ world/*?}*/.getGameRules();
        return original ||
                (source.is(AtmosphericDamageTypeTags.BYPASSES_CREATIVE) &&
                        this.isCreative() &&
                        rules.getBoolean(AtmosphericDamageTypeGamerules.ALLOW_BYPASSING_CREATIVE)) ||
                (source.is(AtmosphericDamageTypeTags.BYPASSES_SPECTATOR) &&
                        this.isSpectator() &&
                        rules.getBoolean(AtmosphericDamageTypeGamerules.ALLOW_BYPASSING_SPECTATOR));
    }
}
