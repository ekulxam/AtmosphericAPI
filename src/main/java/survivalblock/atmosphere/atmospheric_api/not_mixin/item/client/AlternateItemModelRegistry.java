//? if 1.21.1 {
/*package survivalblock.atmosphere.atmospheric_api.not_mixin.item.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.item.Item;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.AlternateModelItem;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.IAmASpyglassItem;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public final class AlternateItemModelRegistry {

    private AlternateItemModelRegistry() {
    }

    public static void register(Item item, ModelResourceLocation modelResourceLocation) {
        if (!(item instanceof AlternateModelItem alternateModelItem)) {
            throw new IllegalArgumentException("The item must be an instance of AlternateModelItem!");
        }
        AlternateItemModelRegistryImpl.register(alternateModelItem, modelResourceLocation);
    }

    public static void registerSpyglass(Item item, ModelResourceLocation modelResourceLocation) {
        if (!(item instanceof IAmASpyglassItem spyglass)) {
            throw new IllegalArgumentException("The item must be an instance of IAmASpyglassItem!");
        }
        AlternateItemModelRegistryImpl.registerSpyglass(spyglass, modelResourceLocation);
    }
}
*///?}