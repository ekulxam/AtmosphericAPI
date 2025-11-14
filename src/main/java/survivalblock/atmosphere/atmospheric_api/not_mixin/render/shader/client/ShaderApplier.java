//? if >1.21.1 {
package survivalblock.atmosphere.atmospheric_api.not_mixin.render.shader.client;

import net.minecraft.client.DeltaTracker;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("ClassCanBeRecord")
public class ShaderApplier implements Comparable<ShaderApplier> {

    protected final ResourceLocation postEffectId;
    protected final PostEffectCondition condition;
    protected final int priority;

    public ShaderApplier(ResourceLocation id, PostEffectCondition condition) {
        this(id, condition, 1000);
    }

    public ShaderApplier(ResourceLocation id, PostEffectCondition condition, int priority) {
        this.postEffectId = id;
        this.condition = condition;
        this.priority = priority;
    }

    public ResourceLocation getPostEffectId() {
        return this.postEffectId;
    }

    public boolean shouldRender(DeltaTracker renderTickCounter, boolean tick) {
        return this.condition.shouldRender(renderTickCounter, tick);
    }

    @Override
    public int compareTo(@NotNull ShaderApplier other) {
        return other.priority - this.priority;
    }

    @FunctionalInterface
    public interface PostEffectCondition {

        boolean shouldRender(DeltaTracker renderTickCounter, boolean tick);
    }
}
//?}