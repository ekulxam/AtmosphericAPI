package survivalblock.atmosphere.atmospheric_api.not_mixin.item.client;

import java.util.HashMap;
import java.util.function.BiConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.util.ModelIdentifier;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.AlternateModelItem;
import survivalblock.atmosphere.atmospheric_api.not_mixin.item.IAmASpyglassItem;

@Environment(EnvType.CLIENT)
public final class AlternateItemModelRegistryImpl {
    private static final HashMap<AlternateModelItem, ModelIdentifier> models = new HashMap<>();
    private static final BiConsumer<AlternateModelItem, ModelIdentifier> modelHandler = models::put;

    private static final HashMap<IAmASpyglassItem, ModelIdentifier> spyglassModels = new HashMap<>();
    private static final BiConsumer<IAmASpyglassItem, ModelIdentifier> spyglassMapHandler = spyglassModels::put;

    public static void register(AlternateModelItem item, ModelIdentifier modelIdentifier) {
        modelHandler.accept(item, modelIdentifier);
    }

    public static void registerSpyglass(IAmASpyglassItem item, ModelIdentifier modelIdentifier) {
        spyglassMapHandler.accept(item, modelIdentifier);
    }

    public static HashMap<AlternateModelItem, ModelIdentifier> getModels() {
        return models;
    }

    public static HashMap<IAmASpyglassItem, ModelIdentifier> getSpyglassModels() {
        return spyglassModels;
    }
}
