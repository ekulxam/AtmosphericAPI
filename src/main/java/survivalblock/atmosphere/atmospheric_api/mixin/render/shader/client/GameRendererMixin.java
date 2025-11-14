//? if >1.21.1 {
/*package survivalblock.atmosphere.atmospheric_api.mixin.render.shader.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.client.render.DefaultFramebufferSet;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.util.Pool;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.atmospheric_api.not_mixin.render.shader.client.AtmosphericShaderRegistryImpl;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {

    @Shadow @Final private MinecraftClient client;

    @Shadow @Final private Pool pool;

    @SuppressWarnings("deprecation")
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldRenderer;drawEntityOutlinesFramebuffer()V", shift = At.Shift.AFTER))
    private void applySlumberShader(RenderTickCounter tickCounter, boolean tick, CallbackInfo ci) {
        AtmosphericShaderRegistryImpl.getShaderAppliers().forEach(shaderApplier -> {
            if (!shaderApplier.shouldRender(tickCounter, tick)) {
                return;
            }
            PostEffectProcessor pep = this.client.getShaderLoader().loadPostEffect(shaderApplier.getPostEffectId(), DefaultFramebufferSet.MAIN_ONLY);
            if (pep != null) {
                pep.render(this.client.getFramebuffer(), this.pool);
            }
        });
    }
}
*///?}