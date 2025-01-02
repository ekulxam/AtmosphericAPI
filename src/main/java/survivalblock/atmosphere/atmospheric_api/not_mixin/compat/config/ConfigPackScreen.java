package survivalblock.atmosphere.atmospheric_api.not_mixin.compat.config;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.pack.PackScreen;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Consumer;

public class ConfigPackScreen extends PackScreen {

    private final Screen parent;

    public ConfigPackScreen(ResourcePackManager resourcePackManager, Consumer<ResourcePackManager> applier, Path file, Text title, Screen parent) {
        super(resourcePackManager, applier, file, title);
        this.parent = parent;
    }

    public static @NotNull ConfigPackScreen fromParent(Screen parent) {
        MinecraftClient client = MinecraftClient.getInstance();
        return new ConfigPackScreen(
                client.getResourcePackManager(),
                client.options::refreshResourcePacks,
                client.getResourcePackDir(),
                Text.translatable("resourcePack.title"),
                parent);
    }

    @Override
    public void close() {
        super.close();
        Objects.requireNonNull(this.client).setScreen(parent);
    }
}
