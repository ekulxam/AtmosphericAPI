package survivalblock.atmosphere.atmospheric_api.not_mixin.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

import java.util.concurrent.CompletableFuture;

/**
 * @deprecated Just use {@linkplain FabricDynamicRegistryProvider} or {@linkplain AtmosphericDynamicRegistriesProvider}
 */
@Deprecated(since = "3.1.4")
@SuppressWarnings("unused")
public abstract class AtmosphericDynamicRegistryProvider<T> extends FabricDynamicRegistryProvider {

    protected final ResourceKey<? extends Registry<T>> registryRef;

    public AtmosphericDynamicRegistryProvider(FabricDataOutput output, ResourceKey<? extends Registry<T>> registryRef, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
        this.registryRef = registryRef;
    }

    @Override
    protected void configure(HolderLookup.Provider wrapperLookup, Entries entries) {
        this.configure(wrapperLookup, entries, new RegistryEntryLookupContainer(wrapperLookup));
    }

    protected abstract void configure(HolderLookup.Provider wrapperLookup, Entries entries, RegistryEntryLookupContainer container);

    @Override
    public String getName() {
        return "Dynamic Registry for " + registryRef.location();
    }
}
