package survivalblock.atmosphere.atmospheric_api.access;

import net.minecraft.item.ItemStack;

public interface WaitingOnFabricRenderState {

    ItemStack atmospheric_api$getMainHandStack();

    ItemStack atmospheric_api$getOffHandStack();

    void atmospheric_api$setMainHandStack(ItemStack mainHandStack);

    void atmospheric_api$setOffHandStack(ItemStack offHandStack);
}
