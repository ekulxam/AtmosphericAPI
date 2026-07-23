package survivalblock.atmosphere.atmospheric_api.not_mixin.util;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import org.jetbrains.annotations.ApiStatus;

/**
 * A {@linkplain ModInitializer} that allows access to the mod's {@linkplain ModContainer} and the {@linkplain FabricLoader} instance.
 */
@SuppressWarnings("unused")
@ApiStatus.Experimental
public interface AtmosphericModInitializer extends ModInitializer {
    @Override
    default void onInitialize() {
        String modId = this.getModId();

        if (modId == null) {
            try {
                modId = (String) this.getClass().getField("MOD_ID").get(null);
            } catch (NoSuchFieldException e) {
                try {
                    modId = (String) this.getClass().getField("MODID").get(null);
                } catch (NoSuchFieldException | IllegalAccessException | ClassCastException e1) {
                    e1.addSuppressed(e);
                    throw new RuntimeException(e1);
                }
            } catch (IllegalAccessException | ClassCastException e) {
                throw new RuntimeException(e);
            }
        }

        FabricLoader floader = FabricLoader.getInstance();
        this.onInitialize(floader.getModContainer(modId).orElseThrow(), floader);
    }

    void onInitialize(ModContainer modContainer, FabricLoader floader);

    default String getModId() {
        return null;
    }
}
