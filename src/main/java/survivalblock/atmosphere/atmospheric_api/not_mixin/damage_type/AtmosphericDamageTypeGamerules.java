package survivalblock.atmosphere.atmospheric_api.not_mixin.damage_type;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.level.GameRules;

public class AtmosphericDamageTypeGamerules {

    public static final GameRules.Key<GameRules.BooleanValue> ALLOW_BYPASSING_CREATIVE = GameRuleRegistry.register("atmosphericAPI:damageTypesCanBypassCreativeInvulnerability", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true));
    public static final GameRules.Key<GameRules.BooleanValue> ALLOW_BYPASSING_SPECTATOR = GameRuleRegistry.register("atmosphericAPI:damageTypesCanBypassSpectatorInvulnerability", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true));

    public static void init() {

    }
}
