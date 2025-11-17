package survivalblock.atmosphere.atmospheric_api.mixin.render.overlay.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
//? if <1.21.6
/*import net.minecraft.client.gui.LayeredDraw;*/
import net.minecraft.resources.ResourceLocation;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.atmospheric_api.not_mixin.render.overlay.client.AtmosphericOverlayRegistryImpl;

import java.util.function.BooleanSupplier;
@Mixin(value = Gui.class, priority = 1500)
public abstract class InGameHudMixin {

    @Shadow @Final private Minecraft minecraft;

    @Shadow protected abstract void renderTextureOverlay(GuiGraphics context, ResourceLocation texture, float opacity);

    @Inject(method = "renderCameraOverlays", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;getTicksFrozen()I", shift = At.Shift.BEFORE))
    private void renderCustomOverlaysInMiscOverlays(GuiGraphics context, DeltaTracker tickCounter, CallbackInfo ci) {
        this.atmospheric_api$renderCustomOverlays(context, false);
    }

    //? if =1.21.1 {
    /*@WrapOperation(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/LayeredDraw;add(Lnet/minecraft/client/gui/LayeredDraw;Ljava/util/function/BooleanSupplier;)Lnet/minecraft/client/gui/LayeredDraw;", ordinal = 0))
    private LayeredDraw addSubDrawerToRenderNonBypassableOverlays(LayeredDraw instance, LayeredDraw drawer, BooleanSupplier shouldRender, Operation<LayeredDraw> original) {
        LayeredDraw alternateOverlayDrawer = new LayeredDraw().add((context, tickCounter) -> this.atmospheric_api$renderCustomOverlays(context, true));
        return original.call(instance, drawer, shouldRender).add(alternateOverlayDrawer, () -> !shouldRender.getAsBoolean());
    }
     *///?} elif >=1.21.8 {
    @ModifyExpressionValue(method = "render", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Options;hideGui:Z", ordinal = 0, opcode = Opcodes.GETFIELD))
    private boolean renderNonBypassable(boolean original, GuiGraphics context, DeltaTracker renderTickCounter) {
        if (original) {
            this.atmospheric_api$renderCustomOverlays(context, true);
        }
        return original;
    }
    //?}

    @Unique
    private void atmospheric_api$renderCustomOverlays(GuiGraphics context, boolean isHudHidden) {
        if (this.minecraft.player != null) {
            AtmosphericOverlayRegistryImpl.getOverlayHolders().forEach(overlayHolder -> {
                if (isHudHidden && overlayHolder.isBypassable()) {
                    return;
                }
                this.renderTextureOverlay(context, overlayHolder.getTexture(), overlayHolder.getOpacity(this.minecraft, this.minecraft.player));
            });
        }
    }
}
