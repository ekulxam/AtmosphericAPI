/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.damage_type;

//? if >=1.21.11 {
import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
//?} else {
/*import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.level.GameRules;
*///?}
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;

public class AtmosphericDamageTypeGamerules {

    public static final /*? <1.21.11 {*/ /*GameRules.Key<GameRules.BooleanValue> *//*?} else {*/ GameRule<Boolean> /*?}*/  ALLOW_BYPASSING_CREATIVE = registerBool("damage_types_can_bypass_creative_invulnerability", true);
    public static final /*? <1.21.11 {*/ /*GameRules.Key<GameRules.BooleanValue> *//*?} else {*/ GameRule<Boolean> /*?}*/  ALLOW_BYPASSING_SPECTATOR = registerBool("damage_types_can_bypass_spectator_invulnerability", true);

    public static /*? <1.21.11 {*/ /*GameRules.Key<GameRules.BooleanValue> *//*?} else {*/ GameRule<Boolean> /*?}*/ registerBool(String name, boolean defaultValue) {
        return register(name, /*? <1.21.11 {*/ /*GameRuleFactory.createBooleanRule(defaultValue) *//*?} else {*/ GameRuleBuilder.forBoolean(defaultValue) /*?}*/, /*? <1.21.11 {*/ /*GameRules.Category *//*?} else {*/ GameRuleCategory /*?}*/.MISC);
    }

    public static <T /*? <1.21.11 {*/ /*extends GameRules.Value<T> *//*?}*/> /*? <1.21.11 {*/ /*GameRules.Key<T> *//*?} else {*/ GameRule<T> /*?}*/ register(String name, /*? <1.21.11 {*/ /*GameRules.Type<T> type *//*?} else {*/ GameRuleBuilder<T> builder /*?}*/, /*? <1.21.11 {*/ /*GameRules.Category *//*?} else {*/ GameRuleCategory /*?}*/ category) {
        //? <1.21.11
        //return GameRuleRegistry.register(AtmosphericAPI.MOD_ID + ":" + name, category, type);
        //? >=1.21.11
        return builder.category(category).buildAndRegister(AtmosphericAPI.id(name));
    }

    public static void init() {

    }
}
