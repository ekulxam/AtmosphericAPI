package survivalblock.atmosphere.atmospheric_api.mixin.datagen.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.data.client.ModelProvider;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.atmospheric_api.not_mixin.datagen.AtmosphericItemModelGenerationPreventer;

@Mixin(ModelProvider.class)
public class ModelProviderMixin {

    // good method naming go brrrr
    @ModifyExpressionValue(method = "method_25741", at = @At(value = "INVOKE", target = "Ljava/util/Set;contains(Ljava/lang/Object;)Z", remap = false))
    private static boolean doNotGenerateItemModelViaDatagen(boolean original, @Local Item item) {
        return original || AtmosphericItemModelGenerationPreventer.shouldPreventItemModelGeneration(item);
    }
}
