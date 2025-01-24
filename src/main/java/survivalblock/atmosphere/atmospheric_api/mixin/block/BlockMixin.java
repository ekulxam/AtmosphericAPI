package survivalblock.atmosphere.atmospheric_api.mixin.block;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.Block;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.atmospheric_api.not_mixin.block.NonRegisterableBlock;

@Mixin(Block.class)
public class BlockMixin {

    @WrapOperation(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/registry/DefaultedRegistry;createEntry(Ljava/lang/Object;)Lnet/minecraft/registry/entry/RegistryEntry$Reference;"))
    private <T extends Block> RegistryEntry.Reference<T> doNotCreateEntry(DefaultedRegistry<T> instance, Object object, Operation<RegistryEntry.Reference<T>> original) {
        if ((Block) (Object) this instanceof NonRegisterableBlock nonRegisterableBlock) {
            return nonRegisterableBlock.getAlternateNullableReference();
        }
        return original.call(instance, object);
    }
}
