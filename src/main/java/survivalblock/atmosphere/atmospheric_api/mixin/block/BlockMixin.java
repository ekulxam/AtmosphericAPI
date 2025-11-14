package survivalblock.atmosphere.atmospheric_api.mixin.block;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.DefaultedRegistry;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.atmospheric_api.not_mixin.block.NonRegisterableBlock;

@Mixin(Block.class)
public class BlockMixin {

    @WrapOperation(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/DefaultedRegistry;createIntrusiveHolder(Ljava/lang/Object;)Lnet/minecraft/core/Holder$Reference;"))
    private <T extends Block> Holder.Reference<T> doNotCreateEntry(DefaultedRegistry<T> instance, Object object, Operation<Holder.Reference<T>> original) {
        if ((Block) (Object) this instanceof NonRegisterableBlock nonRegisterableBlock) {
            return nonRegisterableBlock.getAlternateNullableReference();
        }
        return original.call(instance, object);
    }
}
