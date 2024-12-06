package survivalblock.atmosphere.atmospheric_api.not_mixin.damage_type;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;

public class AtmosphericDamageTypeTags {
    public static final TagKey<DamageType> BYPASSES_CREATIVE = TagKey.of(RegistryKeys.DAMAGE_TYPE, AtmosphericAPI.id("bypasses_creative"));
    public static final TagKey<DamageType> BYPASSES_SPECTATOR = TagKey.of(RegistryKeys.DAMAGE_TYPE, AtmosphericAPI.id("bypasses_creative"));
}
