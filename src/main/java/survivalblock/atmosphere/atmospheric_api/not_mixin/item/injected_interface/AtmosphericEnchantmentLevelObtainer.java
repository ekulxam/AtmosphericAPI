package survivalblock.atmosphere.atmospheric_api.not_mixin.item.injected_interface;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.tag.TagKey;

@SuppressWarnings("unused")
public interface AtmosphericEnchantmentLevelObtainer {

    default int atmospheric_api$getAbsoluteLevel(TagKey<Enchantment> tag) {
        return 0;
    }
}
