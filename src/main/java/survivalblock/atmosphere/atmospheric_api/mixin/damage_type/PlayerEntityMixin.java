package survivalblock.atmosphere.atmospheric_api.mixin.damage_type;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.atmospheric_api.not_mixin.damage_type.AtmosphericDamageTypeGamerules;
import survivalblock.atmosphere.atmospheric_api.not_mixin.damage_type.AtmosphericDamageTypeTags;
import survivalblock.atmosphere.atmospheric_api.not_mixin.funny.ThisIsABadIdea;

@ThisIsABadIdea(ThisIsABadIdea.LevelsOfHorrendousness.PROBABLY)
@Mixin(value = PlayerEntity.class, priority = 500)
public abstract class PlayerEntityMixin extends LivingEntity {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow public abstract boolean isCreative();

    @Shadow public abstract boolean isSpectator();

    @ModifyExpressionValue(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/damage/DamageSource;isIn(Lnet/minecraft/registry/tag/TagKey;)Z"))
    private boolean bypassesCreativeMode(boolean original,/*? =1.21.1 {*/  /*?} else {*/ ServerWorld world, /*?}*/ DamageSource source, float amount) {
        GameRules rules = /*? =1.21.1 {*/ /*this.getWorld() *//*?} else {*/ world/*?}*/.getGameRules();
        return original ||
                (source.isIn(AtmosphericDamageTypeTags.BYPASSES_CREATIVE) &&
                        this.isCreative() &&
                        rules.getBoolean(AtmosphericDamageTypeGamerules.ALLOW_BYPASSING_CREATIVE)) ||
                (source.isIn(AtmosphericDamageTypeTags.BYPASSES_SPECTATOR) &&
                        this.isSpectator() &&
                        rules.getBoolean(AtmosphericDamageTypeGamerules.ALLOW_BYPASSING_SPECTATOR));
    }
}
