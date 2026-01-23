package survivalblock.atmosphere.atmospheric_api.mixin.resource.client;

import net.minecraft.client.resources.SplashManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.atmospheric_api.not_mixin.resource.client.AdditionalSplashTextRegistryImpl;

import java.util.List;

@SuppressWarnings({"unused", "UnusedMixin"})
@Mixin(SplashManager.class)
public class SplashManagerMixin {

    @Shadow
    @Final
    private List<String> splashes;

    @Inject(method = "apply(Ljava/util/List;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V", at = @At("RETURN"))
    private void addSplashes(CallbackInfo ci) {
        for (AdditionalSplashTextRegistryImpl.SplashHolder splashHolder : AdditionalSplashTextRegistryImpl.ADDITIONAL_SPLASHES) {
            this.splashes.add(splashHolder.splash());
        }
    }
}
