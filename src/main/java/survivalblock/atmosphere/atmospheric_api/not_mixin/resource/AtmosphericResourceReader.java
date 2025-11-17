package survivalblock.atmosphere.atmospheric_api.not_mixin.resource;

import com.google.gson.JsonParseException;
//? if >=1.21.8
import com.google.gson.Strictness;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
//? if <1.21.9
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.Util;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.ResourceLocation;
//? if >=1.21.9
/*import net.minecraft.server.packs.resources.PreparableReloadListener;*/
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
//? if =1.21.1
/*import net.minecraft.util.profiling.ProfilerFiller;*/
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public abstract class AtmosphericResourceReader<T> implements  /*? >=1.21.9 {*/ /*PreparableReloadListener*//*?} else {*/ IdentifiableResourceReloadListener /*?}*/ {

    protected final String errorMessage;
    protected final Codec<T> elementCodec;
    protected final FileToIdConverter resourceFinder;

    //? if >=1.21.8 {
    protected Strictness strictness = Strictness.STRICT;
    //?} else {
    /*protected boolean lenient = false;
    *///?}

    protected AtmosphericResourceReader(String errorMessage, Codec<T> elementCodec, FileToIdConverter resourceFinder) {
        this.errorMessage = errorMessage;
        this.elementCodec = elementCodec;
        this.resourceFinder = resourceFinder;
    }

    //? if <1.21.9 {
    protected abstract void upload(Map<ResourceLocation, T> map, /*? =1.21.1 {*/ /*ProfilerFiller profiler *//*?} else {*/ ResourceManager manager, Executor applyExecutor/*?}*/);
    
    @Override
    public CompletableFuture<Void> reload(PreparationBarrier synchronizer, ResourceManager manager,/*? =1.21.1 {*/ /*ProfilerFiller prepareProfiler, ProfilerFiller applyProfiler, *//*?} else {*/ /*?}*/ Executor prepareExecutor, Executor applyExecutor) {
        /*? =1.21.1 {*/ /*prepareProfiler.startTick(); *//*?}*/
        CompletableFuture<Map<ResourceLocation, T>> completableFuture = reloadAndFind(manager, prepareExecutor);
        return completableFuture
                .thenCompose(synchronizer::wait)
                .thenAcceptAsync(result -> this.upload(result, /*? =1.21.1 {*/ /*applyProfiler *//*?} else {*/ manager, applyExecutor /*?}*/ ), applyExecutor);
    }
    //?} else {
    /*protected abstract void upload(Map<ResourceLocation, T> map, SharedState state, Executor applyExecutor);

    @Override
    public CompletableFuture<Void> reload(SharedState sharedState, Executor prepareExecutor, PreparationBarrier synchronizer, Executor applyExecutor) {
        CompletableFuture<Map<ResourceLocation, T>> completableFuture = reloadAndFind(sharedState, prepareExecutor);
        return completableFuture
                .thenCompose(synchronizer::wait)
                .thenAcceptAsync(result -> this.upload(result, sharedState, applyExecutor));
    }
    *///?}

    // what did I just create
    // I love it when I just mash together a bunch of code
    protected CompletableFuture<Map<ResourceLocation, T>> reloadAndFind(/*? >=1.21.9 {*/ /*SharedState sharedState*//*?} else {*/ ResourceManager resourceManager /*?}*/, Executor executor) {
        /*? >=1.21.9 {*/ /*ResourceManager resourceManager = sharedState.resourceManager(); *//*?}*/
        return CompletableFuture.supplyAsync(() -> this.resourceFinder.listMatchingResources(resourceManager), executor)
                .thenCompose(
                        resources -> {
                            List<CompletableFuture<Pair<ResourceLocation, T>>> list = new ArrayList<>(resources.size());
                            for (Map.Entry<ResourceLocation, Resource> entry : resources.entrySet()) {
                                list.add(CompletableFuture.supplyAsync(() -> {
                                    try {
                                        JsonReader reader = new JsonReader(entry.getValue().openAsReader());
                                        reader./*? >=1.21.8 {*/ setStrictness(this.strictness) /*?} else {*/ /*setLenient(this.lenient) *//*?}*/;
                                        Pair<ResourceLocation, T> pair;
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
                            return Util.sequence(list)
                                    .thenApply(resourcesx -> resourcesx.stream().filter(Objects::nonNull).collect(Collectors.toUnmodifiableMap(Pair::getFirst, Pair::getSecond)));
                        }
                );
    }
}