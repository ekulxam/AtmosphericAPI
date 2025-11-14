//? if 1.21.1 {
/*package survivalblock.atmosphere.atmospheric_api.mixin.datagen;

import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import survivalblock.atmosphere.atmospheric_api.not_mixin.datagen.injected_interface.AtmosphericItemModelGenerationPreventer;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

@Debug(export=true)
@Mixin(BlockModelGenerators.class)
public class BlockStateModelGeneratorMixin implements AtmosphericItemModelGenerationPreventer {

    @Shadow @Final private Consumer<Item> skippedAutoModelsOutput;

    @Override
    public void atmospheric_api$excludeFromSimpleItemModelGeneration(Item item) {
        this.skippedAutoModelsOutput.accept(item);
    }

    @Override
    public void atmospheric_api$excludeFromSimpleItemModelGeneration(Item... items) {
        Arrays.stream(items).forEach(skippedAutoModelsOutput);
    }

    @Override
    public void atmospheric_api$excludeFromSimpleItemModelGeneration(Collection<Item> items) {
        items.forEach(skippedAutoModelsOutput);
    }

    @Override
    public void atmospheric_api$excludeConvertibleFromSimpleItemModelGeneration(ItemLike itemConvertible) {
        this.skippedAutoModelsOutput.accept(itemConvertible.asItem());
    }

    @Override
    public void atmospheric_api$excludeConvertibleFromSimpleItemModelGeneration(ItemLike... itemConvertibles) {
        Arrays.stream(itemConvertibles).forEach(itemConvertible -> this.skippedAutoModelsOutput.accept(itemConvertible.asItem()));
    }

    @Override
    public void atmospheric_api$excludeConvertibleFromSimpleItemModelGeneration(Collection<ItemLike> itemConvertibles) {
        itemConvertibles.forEach(itemConvertible -> this.skippedAutoModelsOutput.accept(itemConvertible.asItem()));
    }
}
*///?}