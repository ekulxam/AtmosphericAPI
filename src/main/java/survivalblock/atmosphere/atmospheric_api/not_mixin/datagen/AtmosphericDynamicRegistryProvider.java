package survivalblock.atmosphere.atmospheric_api.not_mixin.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.*;

import java.util.concurrent.CompletableFuture;

public abstract class AtmosphericDynamicRegistryProvider<T> extends FabricDynamicRegistryProvider {

    protected final RegistryKey<? extends Registry<T>> registryRef;

    public AtmosphericDynamicRegistryProvider(FabricDataOutput output, RegistryKey<? extends Registry<T>> registryRef, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
        this.registryRef = registryRef;
    }

    @Override
    public String getName() {
        return registryRef.getValue().toString();
    }
}
