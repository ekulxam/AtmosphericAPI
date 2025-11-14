package survivalblock.atmosphere.atmospheric_api.not_mixin.damage_type;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;

public class AtmosphericDamageTypeTags {
    public static final TagKey<DamageType> BYPASSES_CREATIVE = TagKey.create(Registries.DAMAGE_TYPE, AtmosphericAPI.id("bypasses_creative"));
    public static final TagKey<DamageType> BYPASSES_SPECTATOR = TagKey.create(Registries.DAMAGE_TYPE, AtmosphericAPI.id("bypasses_spectator"));
}
