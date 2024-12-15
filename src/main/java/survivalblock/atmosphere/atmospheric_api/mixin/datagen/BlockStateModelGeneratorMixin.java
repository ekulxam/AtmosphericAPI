package survivalblock.atmosphere.atmospheric_api.mixin.datagen;

import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import survivalblock.atmosphere.atmospheric_api.not_mixin.datagen.injected_interface.AtmosphericItemModelGenerationPreventer;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;

@Mixin(BlockStateModelGenerator.class)
public class BlockStateModelGeneratorMixin implements AtmosphericItemModelGenerationPreventer {

    @Shadow @Final private Consumer<Item> simpleItemModelExemptionCollector;

    @Override
    public void atmospheric_api$excludeFromSimpleItemModelGeneration(Item item) {
        this.simpleItemModelExemptionCollector.accept(item);
    }

    @Override
    public void atmospheric_api$excludeFromSimpleItemModelGeneration(ItemConvertible itemConvertible) {
        this.simpleItemModelExemptionCollector.accept(itemConvertible.asItem());
    }

    @Override
    public void atmospheric_api$excludeFromSimpleItemModelGeneration(ItemConvertible... itemConvertibles) {
        Arrays.stream(itemConvertibles).forEach(itemConvertible -> this.simpleItemModelExemptionCollector.accept(itemConvertible.asItem()));
    }

    @Override
    public void atmospheric_api$excludeFromSimpleItemModelGeneration(Collection<ItemConvertible> itemConvertibles) {
        itemConvertibles.forEach(itemConvertible -> this.simpleItemModelExemptionCollector.accept(itemConvertible.asItem()));
    }
}
