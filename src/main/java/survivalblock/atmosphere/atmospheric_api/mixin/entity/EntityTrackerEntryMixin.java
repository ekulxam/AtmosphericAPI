package survivalblock.atmosphere.atmospheric_api.mixin.entity;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntityAttributesS2CPacket;
import net.minecraft.server.network.EntityTrackerEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;
import survivalblock.atmosphere.atmospheric_api.not_mixin.entity.EntityWithAttributes;

import java.lang.reflect.Method;
import java.util.Collection;

@Mixin(EntityTrackerEntry.class)
public class EntityTrackerEntryMixin {

	@Shadow @Final private Entity entity;

	@Inject(method = "sendPackets", at = @At("HEAD"))
	private void renderCustomThing(ServerPlayerEntity player, @Coerce Object sender, CallbackInfo ci, @Share("sender") LocalRef<Object> senderRef) {
		senderRef.set(sender);
	}

	@WrapOperation(method = "sendPackets", constant = @Constant(classValue = LivingEntity.class, ordinal = 0))
	private boolean updateTungstenRodAttributes(Object object, Operation<Boolean> original, @Share("sender") LocalRef<Object> senderRef) {
		if (this.entity instanceof EntityWithAttributes entityWithAttributes && entityWithAttributes.shouldAutoSyncAttributes()) {
			Collection<EntityAttributeInstance> collection = entityWithAttributes.getAttributes().getAttributesToSend();
			if (!collection.isEmpty()) {
				try {
					Object sender = senderRef.get();
					Class<?> clazz = sender.getClass();
					String methodName = "accept";
					Method acceptMethod = clazz.getMethod(methodName, AtmosphericAPI.isConnectorLoaded ? Packet.class : Object.class);
					acceptMethod.invoke(sender, new EntityAttributesS2CPacket(this.entity.getId(), collection));
				} catch (Throwable throwable) {
					AtmosphericAPI.LOGGER.error("Error while doing reflection to get a non-living entity's attributes!", throwable);
				}
			}
		}
		return original.call(object);
	}
}
