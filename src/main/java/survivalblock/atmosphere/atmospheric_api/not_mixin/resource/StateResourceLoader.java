//? if >=1.21.9 {
package survivalblock.atmosphere.atmospheric_api.not_mixin.resource;

import com.google.common.collect.Maps;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.PreparableReloadListener;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author cputnam-a11y
 * <p>
 * Used with permission (licensed under the Unlicense) and adapted by SkyNotTheLimit
 * @see <a href="https://discord.com/channels/507304429255393322/507982478276034570/1528553003974529064">Relevant discord message</a>
 */
public interface StateResourceLoader {
    Map<PackType, StateResourceLoader> INSTANCE = Maps.immutableEnumMap(Map.of(
            PackType.SERVER_DATA, (id, factory) -> ResourceLoader.get(PackType.SERVER_DATA).registerReloadListener(id, new StatefulReloadListener<>(new IdentityHashMap<>(), factory, id)),
            PackType.CLIENT_RESOURCES, (id, factory) -> ResourceLoader.get(PackType.CLIENT_RESOURCES).registerReloadListener(id, new StatefulReloadListener<>(new IdentityHashMap<>(), factory, id))
    ));

    static StateResourceLoader get(PackType packType) {
        return Objects.requireNonNull(INSTANCE.get(packType));
    }

    @SuppressWarnings("unused")
    void registerReloadListener(
            Identifier id,
            Function<PreparableReloadListener.SharedState, PreparableReloadListener> factory
    );
}
//?}