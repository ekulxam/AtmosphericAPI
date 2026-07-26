/*
 * Copyright (c) 21:56 UTC 26 July 2026 - present ekulxam
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.compat.config;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.packs.PackSelectionScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.repository.PackRepository;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ConfigPackScreen extends PackSelectionScreen {

    protected final Screen parent;
    protected final Set<String> allowedPacks;

    public ConfigPackScreen(PackRepository resourcePackManager, Consumer<PackRepository> applier, Path file, Component title, Screen parent, Set<String> allowedPacks) {
        super(resourcePackManager, applier, file, title);
        this.parent = parent;
        this.allowedPacks = ImmutableSet.copyOf(allowedPacks);
    }

    public static @NotNull ConfigPackScreen fromParent(Screen parent, Component title, Set<String> allowedPacks) {
        Minecraft client = Minecraft.getInstance();
        return new ConfigPackScreen(
                client.getResourcePackRepository(),
                client.options::updateResourcePacks,
                client.getResourcePackDirectory(),
                title,
                parent,
                allowedPacks);
    }

    public static @NotNull ConfigPackScreen fromParent(Screen parent, Component title, String... allowedPacks) {
        return fromParent(parent, title, Arrays.stream(allowedPacks).collect(Collectors.toSet()));
    }

    @Override
    public void onClose() {
        super.onClose();
        Objects.requireNonNull(this.minecraft).setScreen(parent);
    }

    public Set<String> getAllowedPacks() {
        return this.allowedPacks;
    }
}
