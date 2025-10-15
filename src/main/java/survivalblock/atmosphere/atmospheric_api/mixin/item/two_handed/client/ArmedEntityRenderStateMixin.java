//? if 1.21.8 {
/*package survivalblock.atmosphere.atmospheric_api.mixin.item.two_handed.client;

import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.entity.state.ArmedEntityRenderState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.atmospheric_api.access.WaitingOnFabricRenderState;

@Debug(export = true)
@Mixin(ArmedEntityRenderState.class)
public class ArmedEntityRenderStateMixin implements WaitingOnFabricRenderState {

    @Unique
    ItemStack atmospheric_api$mainHandStack = ItemStack.EMPTY;
    @Unique
    ItemStack atmospheric_api$offHandStack = ItemStack.EMPTY;

    @Inject(method = "updateRenderState", at = @At(value = "HEAD"))
    private static void updateHandStacks(LivingEntity entity, ArmedEntityRenderState state, ItemModelManager itemModelManager, CallbackInfo ci) {
        WaitingOnFabricRenderState please = ((WaitingOnFabricRenderState) state);
        please.atmospheric_api$setMainHandStack(entity.getMainHandStack());
        please.atmospheric_api$setOffHandStack(entity.getOffHandStack());
    }

    @Override
    public ItemStack atmospheric_api$getMainHandStack() {
        return this.atmospheric_api$mainHandStack;
    }

    @Override
    public ItemStack atmospheric_api$getOffHandStack() {
        return this.atmospheric_api$offHandStack;
    }

    @Override
    public void atmospheric_api$setMainHandStack(ItemStack mainHandStack) {
        this.atmospheric_api$mainHandStack = mainHandStack;
    }

    @Override
    public void atmospheric_api$setOffHandStack(ItemStack offHandStack) {
        this.atmospheric_api$offHandStack = offHandStack;
    }
}
*///?}