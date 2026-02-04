/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.resource.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@ApiStatus.Internal
@Environment(EnvType.CLIENT)
public final class AdditionalSplashTextRegistryImpl {

    public static final List<SplashHolder> ADDITIONAL_SPLASHES = new ArrayList<>();

    private AdditionalSplashTextRegistryImpl() {
    }

    public static void register(String string) {
        register(string, 1000);
    }

    public static void register(String string, int priority) {
        ADDITIONAL_SPLASHES.add(new SplashHolder(string, priority));
        ADDITIONAL_SPLASHES.sort(null);
    }

    public record SplashHolder(String splash, int priority) implements Comparable<SplashHolder> {
        @Override
        public int compareTo(@NotNull SplashHolder other) {
            return other.priority - this.priority;
        }
    }
}
