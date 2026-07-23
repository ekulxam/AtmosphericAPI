//? if >=1.21.9 {
package survivalblock.atmosphere.atmospheric_api.not_mixin.resource;

import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.PreparableReloadListener;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Function;

/**
 * @see StateResourceLoader
 */
public record StatefulReloadListener<T extends PreparableReloadListener>(Map<SharedState, T> listeners, Function<SharedState, T> factory, Identifier id) implements PreparableReloadListener {
    @Override
    public CompletableFuture<Void> reload(SharedState state, Executor taskExecutor, PreparationBarrier preparationBarrier, Executor reloadExecutor) {
        CompletableFuture<Void> reload = this.listeners.computeIfAbsent(state, this.factory).reload(state, taskExecutor, preparationBarrier, reloadExecutor);
        this.listeners.remove(state);
        return reload;
    }

    @Override
    public void prepareSharedState(SharedState state) {
        this.listeners.computeIfAbsent(state, this.factory).prepareSharedState(state);
    }

    @Override
    public String getName() {
        return "StatefulReloadListener(" + this.id + ")";
    }
}
//?}