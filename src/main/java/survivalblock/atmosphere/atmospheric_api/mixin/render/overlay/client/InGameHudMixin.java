package survivalblock.atmosphere.atmospheric_api.mixin.render.overlay.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.LayeredDrawer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.atmospheric_api.not_mixin.render.overlay.client.AtmosphericOverlayRegistryImpl;

import java.util.function.BooleanSupplier;

@Mixin(value = InGameHud.class, priority = 1500)
public abstract class InGameHudMixin {

    @Shadow @Final private MinecraftClient client;

    @Shadow protected abstract void renderOverlay(DrawContext context, Identifier texture, float opacity);

    @Inject(method = "renderMiscOverlays", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getFrozenTicks()I", shift = At.Shift.BEFORE))
    private void renderCustomOverlaysInMiscOverlays(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        this.atmospheric_api$renderCustomOverlays(context, false);
    }

    @WrapOperation(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/LayeredDrawer;addSubDrawer(Lnet/minecraft/client/gui/LayeredDrawer;Ljava/util/function/BooleanSupplier;)Lnet/minecraft/client/gui/LayeredDrawer;", ordinal = 0))
    private LayeredDrawer addSubDrawerToRenderNonBypassableOverlays(LayeredDrawer instance, LayeredDrawer drawer, BooleanSupplier shouldRender, Operation<LayeredDrawer> original) {
        LayeredDrawer alternateOverlayDrawer = new LayeredDrawer().addLayer((context, tickCounter) -> this.atmospheric_api$renderCustomOverlays(context, true));
        return original.call(instance, drawer, shouldRender).addSubDrawer(alternateOverlayDrawer, () -> !shouldRender.getAsBoolean());
    }

    @Unique
    private void atmospheric_api$renderCustomOverlays(DrawContext context, boolean isHudHidden) {
        if (this.client.player != null) {
            AtmosphericOverlayRegistryImpl.OVERLAY_HOLDERS.forEach(overlayHolder -> {
                if (isHudHidden && overlayHolder.isBypassable()) {
                    return;
                }
                this.renderOverlay(context, overlayHolder.getTexture(), overlayHolder.getOpacity(this.client, this.client.player));
            });
        }
    }
}
