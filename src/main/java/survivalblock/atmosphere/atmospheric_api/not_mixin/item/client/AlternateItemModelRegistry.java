//? if 1.21.1 {
package survivalblock.atmosphere.atmospheric_api.not_mixin.item.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.item.Item;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.AlternateModelItem;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.IAmASpyglassItem;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public final class AlternateItemModelRegistry {

    public static void register(Item item, ModelIdentifier modelIdentifier) {
        if (!(item instanceof AlternateModelItem alternateModelItem)) {
            throw new IllegalArgumentException("The item must be an instance of AlternateModelItem!");
        }
        AlternateItemModelRegistryImpl.register(alternateModelItem, modelIdentifier);
    }

    public static void registerSpyglass(Item item, ModelIdentifier modelIdentifier) {
        if (!(item instanceof IAmASpyglassItem spyglass)) {
            throw new IllegalArgumentException("The item must be an instance of IAmASpyglassItem!");
        }
        AlternateItemModelRegistryImpl.registerSpyglass(spyglass, modelIdentifier);
    }
}
//?}