package survivalblock.atmosphere.atmospheric_api.not_mixin.compat;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

@SuppressWarnings("unused")
public interface AtmosphericMixinConfigPlugin extends IMixinConfigPlugin {

    @Override
    default void onLoad(String mixinPackage) {
    }

    @Override
    default String getRefMapperConfig() {
        return null;
    }

    @Override
    boolean shouldApplyMixin(String targetClassName, String mixinClassName);

    @Override
    default void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    default List<String> getMixins() {
        return null;
    }

    @Override
    default void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    default void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }
}
