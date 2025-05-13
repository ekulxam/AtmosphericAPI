package survivalblock.atmosphere.atmospheric_api.mixin.registry;

import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;
import java.util.function.Supplier;

@Mixin(Registries.class)
public interface RegistriesAccessor {

    @SuppressWarnings("unused")
    @Accessor("DEFAULT_ENTRIES")
    static Map<Identifier, Supplier<?>> atmospheric_api$getDEFAULT_ENTRIES() {
        throw new UnsupportedOperationException();
    }
}
