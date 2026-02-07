package survivalblock.atmosphere.atmospheric_api.not_mixin.datagen.language;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider.TranslationBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.GameRules;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("NonExtendableApiUsage") // this is fineeee
public record AtmosphericTranslationBuilder(TranslationBuilder delegate) implements TranslationBuilder {

    @Override
    public void add(String translationKey, String value) {
        this.delegate.add(translationKey, value);
    }

    public void add(CreativeModeTab itemGroup, String value) {
        this.add(BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(itemGroup).orElseThrow(() -> new IllegalStateException("ItemGroup " + value + " was not found in the registry!")), value);
    }

    public void add(GameRules.Key<?> rule, String value) {
        this.add(rule.getDescriptionId(), value);
    }

    public String getDamageTypeTranslationKey(HolderLookup.Provider lookup, ResourceKey<DamageType> key) {
        return "death.attack." + lookup.getOrThrow(key).value().msgId();
    }

    @SuppressWarnings("unused")
    public void addDamageType(HolderLookup.Provider lookup, ResourceKey<DamageType> key, String value) {
        this.addDamageType(lookup, key, value, null);
    }

    public void addDamageType(HolderLookup.Provider lookup, ResourceKey<DamageType> key, String value, @Nullable String player) {
        this.addDamageType(lookup, key, value, null, null);
    }

    public void addDamageType(HolderLookup.Provider lookup, ResourceKey<DamageType> key, String value, @Nullable String player, @Nullable String item) {
        String translation = this.getDamageTypeTranslationKey(lookup, key);
        this.add(translation, value);
        if (player != null) {
            this.add(translation + ".player", value);
        }
        if (item != null) {
            this.add(translation + ".item", value);
        }
    }

    @SuppressWarnings("unused")
    public void addEnchantment(ResourceKey<Enchantment> key, String value) {
        this.add("enchantment." + key.location().toLanguageKey(), value);
    }
}
