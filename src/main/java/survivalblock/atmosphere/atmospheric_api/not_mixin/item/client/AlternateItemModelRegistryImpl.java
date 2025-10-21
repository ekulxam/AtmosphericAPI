//? if 1.21.1 {
/*package survivalblock.atmosphere.atmospheric_api.not_mixin.item.client;

import java.util.HashMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.util.ModelIdentifier;
import org.jetbrains.annotations.ApiStatus;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.AlternateModelItem;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.IAmASpyglassItem;

@Environment(EnvType.CLIENT)
public final class AlternateItemModelRegistryImpl {

    private AlternateItemModelRegistryImpl() {
    }

    private static final HashMap<AlternateModelItem, ModelIdentifier> models = new HashMap<>();

    private static final HashMap<IAmASpyglassItem, ModelIdentifier> spyglassModels = new HashMap<>();

    @ApiStatus.Internal
    static void register(AlternateModelItem item, ModelIdentifier modelIdentifier) {
        models.put(item, modelIdentifier);
    }

    @ApiStatus.Internal
    static void registerSpyglass(IAmASpyglassItem item, ModelIdentifier modelIdentifier) {
        spyglassModels.put(item, modelIdentifier);
    }

    public static HashMap<AlternateModelItem, ModelIdentifier> getModels() {
        return models;
    }

    public static HashMap<IAmASpyglassItem, ModelIdentifier> getSpyglassModels() {
        return spyglassModels;
    }
}
*///?}