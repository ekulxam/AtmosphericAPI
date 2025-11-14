package survivalblock.atmosphere.atmospheric_api.mixin.registry;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;
import java.util.function.Supplier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

@Mixin(BuiltInRegistries.class)
public interface RegistriesAccessor {

    @SuppressWarnings("unused")
    @Accessor("LOADERS")
    static Map<ResourceLocation, Supplier<?>> atmospheric_api$getLoaders() {
        throw new UnsupportedOperationException();
    }
}
