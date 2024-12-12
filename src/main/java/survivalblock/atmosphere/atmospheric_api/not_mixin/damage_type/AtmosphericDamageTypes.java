package survivalblock.atmosphere.atmospheric_api.not_mixin.damage_type;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;

@SuppressWarnings("unused")
public class AtmosphericDamageTypes {

    public static RegistryEntry.Reference<DamageType> get(RegistryKey<DamageType> key, World world) {
        return world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key);
    }
}
