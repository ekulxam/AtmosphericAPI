//? if >1.21.1 {
package survivalblock.atmosphere.atmospheric_api.mixin.render.shader.client;

import com.mojang.blaze3d.resource.CrossFrameResourcePool;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelTargetBundle;
import net.minecraft.client.renderer.PostChain;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.atmospheric_api.not_mixin.render.shader.client.AtmosphericShaderRegistryImpl;

@SuppressWarnings("UnusedMixin")
@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {

    @Shadow @Final private Minecraft minecraft;

    @Shadow @Final private CrossFrameResourcePool resourcePool;

    @SuppressWarnings("deprecation")
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/LevelRenderer;doEntityOutline()V", shift = At.Shift.AFTER))
    private void applySlumberShader(DeltaTracker deltaTracker, boolean renderLevel, CallbackInfo ci) {
        AtmosphericShaderRegistryImpl.getShaderAppliers().forEach(shaderApplier -> {
            if (!shaderApplier.shouldRender(deltaTracker, renderLevel)) {
                return;
            }
            PostChain postChain = this.minecraft.getShaderManager().getPostChain(shaderApplier.getPostEffectId(), LevelTargetBundle.MAIN_TARGETS);
            if (postChain != null) {
                postChain.process(this.minecraft.getMainRenderTarget(), this.resourcePool);
            }
        });
    }
}
//?}