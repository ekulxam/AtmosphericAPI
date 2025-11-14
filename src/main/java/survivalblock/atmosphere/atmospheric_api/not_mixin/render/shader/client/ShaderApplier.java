//? if >1.21.1 {
/*package survivalblock.atmosphere.atmospheric_api.not_mixin.render.shader.client;

import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ShaderApplier implements Comparable<ShaderApplier> {

    protected final Identifier postEffectId;
    protected final PostEffectCondition condition;
    protected final int priority;

    public ShaderApplier(Identifier id, PostEffectCondition condition) {
        this(id, condition, 1000);
    }

    public ShaderApplier(Identifier id, PostEffectCondition condition, int priority) {
        this.postEffectId = id;
        this.condition = condition;
        this.priority = priority;
    }

    public Identifier getPostEffectId() {
        return this.postEffectId;
    }

    public boolean shouldRender(RenderTickCounter renderTickCounter, boolean tick) {
        return this.condition.shouldRender(renderTickCounter, tick);
    }

    @Override
    public int compareTo(@NotNull ShaderApplier other) {
        return other.priority - this.priority;
    }

    @FunctionalInterface
    public interface PostEffectCondition {

        boolean shouldRender(RenderTickCounter renderTickCounter, boolean tick);
    }
}
*///?}