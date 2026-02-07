package survivalblock.atmosphere.atmospheric_api.not_mixin.datagen.language;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("unused")
public abstract class AtmosphericLanguageGenerator extends FabricLanguageProvider {
    protected AtmosphericLanguageGenerator(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    protected AtmosphericLanguageGenerator(FabricDataOutput dataOutput, String languageCode, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, languageCode, registryLookup);
    }

    @Override
    public final void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        this.generateTranslations(registryLookup, new AtmosphericTranslationBuilder(translationBuilder));
    }

    public void generateTranslations(HolderLookup.Provider registryLookup, AtmosphericTranslationBuilder translationBuilder) {

    }
}
