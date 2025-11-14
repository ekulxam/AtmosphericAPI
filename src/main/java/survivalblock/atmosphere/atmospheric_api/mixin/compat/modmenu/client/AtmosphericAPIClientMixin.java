package survivalblock.atmosphere.atmospheric_api.mixin.compat.modmenu.client;

import com.terraformersmc.modmenu.gui.ModsScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPIClient;
import survivalblock.atmosphere.atmospheric_api.not_mixin.funny.ThisIsABadIdea;

@ApiStatus.Internal
@ThisIsABadIdea(ThisIsABadIdea.LevelsOfHorrendousness.PROBABLY)
@Environment(EnvType.CLIENT)
@Mixin(value = AtmosphericAPIClient.class, remap = false)
public class AtmosphericAPIClientMixin {

    @Inject(method = "onInitializeClient", at = @At("HEAD"))
    private void createModmenuScreenCommand(CallbackInfo ci) {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommandManager.literal("open_modmenu_screen").executes(context -> {
                Minecraft client = context.getSource().getClient();
                client.tell(() -> client.setScreen(new ModsScreen(null)));
                return 1;
            }));
        });
    }
}
