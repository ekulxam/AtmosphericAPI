//? if 1.21.1 {
package survivalblock.atmosphere.atmospheric_api.mixin.datagen;

import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import survivalblock.atmosphere.atmospheric_api.not_mixin.datagen.injected_interface.AtmosphericItemModelGenerationPreventer;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;

@Debug(export=true)
@MixinEnvironment("1.21.1")
@Mixin(BlockStateModelGenerator.class)
public class BlockStateModelGeneratorMixin implements AtmosphericItemModelGenerationPreventer {

    @Shadow @Final private Consumer<Item> simpleItemModelExemptionCollector;

    @Override
    public void atmospheric_api$excludeFromSimpleItemModelGeneration(Item item) {
        this.simpleItemModelExemptionCollector.accept(item);
    }

    @Override
    public void atmospheric_api$excludeFromSimpleItemModelGeneration(Item... items) {
        Arrays.stream(items).forEach(simpleItemModelExemptionCollector);
    }

    @Override
    public void atmospheric_api$excludeFromSimpleItemModelGeneration(Collection<Item> items) {
        items.forEach(simpleItemModelExemptionCollector);
    }

    @Override
    public void atmospheric_api$excludeConvertibleFromSimpleItemModelGeneration(ItemConvertible itemConvertible) {
        this.simpleItemModelExemptionCollector.accept(itemConvertible.asItem());
    }

    @Override
    public void atmospheric_api$excludeConvertibleFromSimpleItemModelGeneration(ItemConvertible... itemConvertibles) {
        Arrays.stream(itemConvertibles).forEach(itemConvertible -> this.simpleItemModelExemptionCollector.accept(itemConvertible.asItem()));
    }

    @Override
    public void atmospheric_api$excludeConvertibleFromSimpleItemModelGeneration(Collection<ItemConvertible> itemConvertibles) {
        itemConvertibles.forEach(itemConvertible -> this.simpleItemModelExemptionCollector.accept(itemConvertible.asItem()));
    }
}
//?}