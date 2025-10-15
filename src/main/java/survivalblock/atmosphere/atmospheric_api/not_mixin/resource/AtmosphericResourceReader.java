package survivalblock.atmosphere.atmospheric_api.not_mixin.resource;

import com.google.gson.JsonParseException;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceFinder;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.profiler.Profiler;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public abstract class AtmosphericResourceReader<T> implements IdentifiableResourceReloadListener {

    protected final String errorMessage;

    protected final Codec<T> elementCodec;

    protected final ResourceFinder resourceFinder;

    protected AtmosphericResourceReader(String errorMessage, Codec<T> elementCodec, ResourceFinder resourceFinder) {
        this.errorMessage = errorMessage;
        this.elementCodec = elementCodec;
        this.resourceFinder = resourceFinder;
    }

    protected abstract void upload(Map<Identifier, T> map, /*? =1.21.1 {*/ Profiler profiler /*?} else {*/ /*ResourceManager manager, Executor applyExecutor*//*?}*/);

    @Override
    public CompletableFuture<Void> reload(Synchronizer synchronizer, ResourceManager manager,/*? =1.21.1 {*/ Profiler prepareProfiler, Profiler applyProfiler, /*?} else {*/ /*?}*/ Executor prepareExecutor, Executor applyExecutor) {
        /*? =1.21.1 {*/ prepareProfiler.startTick(); /*?} else {*/ /*?}*/
        CompletableFuture<Map<Identifier, T>> completableFuture = reloadAndFind(manager, prepareExecutor);
        return completableFuture
                .thenCompose(synchronizer::whenPrepared)
                .thenAcceptAsync(result -> this.upload(result, /*? =1.21.1 {*/ applyProfiler /*?} else {*/ /*manager, applyExecutor *//*?}*/ ), applyExecutor);
    }

    // what did I just create
    // I love it when I just mash together a bunch of code
    private CompletableFuture<Map<Identifier, T>> reloadAndFind(ResourceManager resourceManager, Executor executor) {
        return CompletableFuture.supplyAsync(() -> this.resourceFinder.findResources(resourceManager), executor)
                .thenCompose(
                        resources -> {
                            List<CompletableFuture<Pair<Identifier, T>>> list = new ArrayList<>(resources.size());
                            for (Map.Entry<Identifier, Resource> entry : resources.entrySet()) {
                                list.add(CompletableFuture.supplyAsync(() -> {
                                    try {
                                        JsonReader reader = new JsonReader(entry.getValue().getReader());
                                        reader.setLenient(false);
                                        Pair<Identifier, T> pair;
                                        try {
                                            pair = Pair.of(entry.getKey(), this.elementCodec.parse(JsonOps.INSTANCE, Streams.parse(reader)).getOrThrow(JsonParseException::new));
                                        } catch (Throwable throwable) {
                                            try {
                                                reader.close();
                                            } catch (Throwable throwable1) {
                                                throwable.addSuppressed(throwable1);
                                            }

                                            throw throwable;
                                        }
                                        reader.close();
                                        return pair;
                                    } catch (Exception exception) {
                                        AtmosphericAPI.LOGGER.error(this.errorMessage, entry.getKey(), exception);
                                        return null;
                                    }
                                }, executor));
                            }
                            return Util.combineSafe(list)
                                    .thenApply(resourcesx -> resourcesx.stream().filter(Objects::nonNull).collect(Collectors.toUnmodifiableMap(Pair::getFirst, Pair::getSecond)));
                        }
                );
    }
}