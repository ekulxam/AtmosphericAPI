package survivalblock.atmosphere.atmospheric_api.not_mixin.compat.config;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.pack.PackScreen;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ConfigPackScreen extends PackScreen {

    protected final Screen parent;
    protected final Set<String> allowedPacks;

    public ConfigPackScreen(ResourcePackManager resourcePackManager, Consumer<ResourcePackManager> applier, Path file, Text title, Screen parent, Set<String> allowedPacks) {
        super(resourcePackManager, applier, file, title);
        this.parent = parent;
        this.allowedPacks = ImmutableSet.copyOf(allowedPacks);
    }

    public static @NotNull ConfigPackScreen fromParent(Screen parent, Set<String> allowedPacks) {
        MinecraftClient client = MinecraftClient.getInstance();
        return new ConfigPackScreen(
                client.getResourcePackManager(),
                client.options::refreshResourcePacks,
                client.getResourcePackDir(),
                Text.translatable("resourcePack.atmospheric_api.configscreen"),
                parent,
                allowedPacks);
    }

    public static @NotNull ConfigPackScreen fromParent(Screen parent, String... allowedPacks) {
        return fromParent(parent, Arrays.stream(allowedPacks).collect(Collectors.toSet()));
    }

    @Override
    public void close() {
        super.close();
        Objects.requireNonNull(this.client).setScreen(parent);
    }

    public Set<String> getAllowedPacks() {
        return this.allowedPacks;
    }
}
