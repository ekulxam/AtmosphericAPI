package survivalblock.atmosphere.atmospheric_api.not_mixin.registrant.delayed;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Function;

@SuppressWarnings("unused")
public class DelayedBlockEntityTypeRegistrant extends DelayedRegistrant<BlockEntityType<?>> {
    protected DelayedBlockEntityTypeRegistrant(String modId, Registry<BlockEntityType<?>> registry) {
        super(modId, registry);
    }

    protected DelayedBlockEntityTypeRegistrant(Function<String, ResourceLocation> idFunction, Registry<BlockEntityType<?>> registry) {
        super(idFunction, registry);
    }

    public DelayedBlockEntityTypeRegistrant(String modId) {
        this(modId, BuiltInRegistries.BLOCK_ENTITY_TYPE);
    }

    public DelayedBlockEntityTypeRegistrant(Function<String, ResourceLocation> idFunction) {
        this(idFunction, BuiltInRegistries.BLOCK_ENTITY_TYPE);
    }

    public <T extends BlockEntity> BlockEntityType<T> register(String name, FabricBlockEntityType.Builder<T> builder) {
        return this.register(name, builder.build());
    }

    public <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType<T> blockEntityType) {
        return super.register(name, blockEntityType);
    }
}
