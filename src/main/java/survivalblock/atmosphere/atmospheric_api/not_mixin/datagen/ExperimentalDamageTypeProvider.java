package survivalblock.atmosphere.atmospheric_api.not_mixin.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ExperimentalDamageTypeProvider<T> extends FabricDynamicRegistryProvider {

    final Class<T> clazz;

    public ExperimentalDamageTypeProvider(Class<T> clazz, FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
        this.clazz = clazz;
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup, Entries entries) {

    }

    @Override
    public String getName() {
        return clazz.getName();
    }
}
