package survivalblock.atmosphere.atmospheric_api.not_mixin.self_datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class AtmosphericAPIEnUsLangGenerator extends FabricLanguageProvider {

    public AtmosphericAPIEnUsLangGenerator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        // packs
        translationBuilder.add("resourcePack.atmospheric_api.disableallscreenshakers.name", "Disable All ScreenShakers from Atmospheric API");

        translationBuilder.add("resourcePack.atmospheric_api.configscreen", "Atmospheric API - Resource Pack Config Screen");
    }
}
