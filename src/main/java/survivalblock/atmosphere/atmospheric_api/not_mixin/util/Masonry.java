package survivalblock.atmosphere.atmospheric_api.not_mixin.util;

import net.minecraft.server.MinecraftServer;
//? if >1.21.1
import net.minecraft.util.ARGB;
//? if =1.21.1
/*import net.minecraft.util.FastColor;*/
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

/**
 * Multiversion utilities for Atmospheric API
 * <p>
 * The name makes sense, right? Because stonecutter (multiversion) and masonry... never mind.
 */
@SuppressWarnings("unused")
public class Masonry {

    @Nullable
    public static MinecraftServer getEntityServer(Entity entity) {
        return entity/*? >=1.21.9 {*/ /*.level() *//*?}*/.getServer();
    }

    public static class ColorHelper {

        public static int getRed(int color) {
            return /*? =1.21.1 {*/ /*FastColor.ARGB32 *//*?} else {*/ ARGB /*?}*/.red(color);
        }

        public static int getGreen(int color) {
            return /*? =1.21.1 {*/ /*FastColor.ARGB32 *//*?} else {*/ ARGB /*?}*/.green(color);
        }

        public static int getBlue(int color) {
            return /*? =1.21.1 {*/ /*FastColor.ARGB32 *//*?} else {*/ ARGB /*?}*/.blue(color);
        }

        public static int getAlpha(int color) {
            return /*? =1.21.1 {*/ /*FastColor.ARGB32 *//*?} else {*/ ARGB /*?}*/.alpha(color);
        }

        public static int withAlpha(int alpha, int color) {
            return /*? =1.21.1 {*/ /*FastColor.ARGB32 *//*?} else {*/ ARGB /*?}*/.color(alpha, color);
        }

        public static int fullAlpha(int color) {
            return /*? =1.21.1 {*/ /*FastColor.ARGB32 *//*?} else {*/ ARGB /*?}*/.opaque(color);
        }

        public static int fromFloats(float alpha, float red, float green, float blue) {
            return /*? =1.21.1 {*/ /*FastColor.ARGB32 *//*?} else {*/ ARGB /*?}*/.colorFromFloat(alpha, red, green, blue);
        }

        public static int lerp(float delta, int color1, int color2) {
            return /*? =1.21.1 {*/ /*FastColor.ARGB32 *//*?} else {*/ ARGB /*?}*/.lerp(delta, color1, color2);
        }
    }

    public static class ItemCooldownManager {

        public static boolean isCoolingDown(ItemCooldowns manager, ItemStack stack) {
            return manager.isOnCooldown(stack/*? <1.21.2 {*/ /*.getItem() *//*?}*/);
        }

        public static float getCooldownProgress(ItemCooldowns manager, ItemStack stack, float tickProgress) {
            return manager.getCooldownPercent(stack/*? <1.21.2 {*/ /*.getItem() *//*?}*/, tickProgress);
        }

        public static void set(ItemCooldowns manager, ItemStack stack, int duration) {
            manager.addCooldown(stack/*? <1.21.2 {*/ /*.getItem() *//*?}*/, duration);
        }

        public static void remove(ItemCooldowns manager, ItemStack stack) {
            manager.removeCooldown(/*? <1.21.2 {*/ /*stack.getItem() *//*?} else {*/ manager.getCooldownGroup(stack) /*?}*/);
        }
    }
}
