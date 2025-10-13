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
import survivalblock.atmosphere.atmospheric_api.not_mixin.util.ReflectionCacher;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Set;

@Mixin(EntityTrackerEntry.class)
public abstract class EntityTrackerEntryMixin {

	@Shadow @Final private Entity entity;

	@Shadow protected abstract void sendSyncPacket(Packet<?> packet);

	@Inject(method = "sendPackets", at = @At("HEAD"))
	private void captureSender(ServerPlayerEntity player, @Coerce Object sender, CallbackInfo ci, @Share("sender") LocalRef<Object> senderRef) {
		senderRef.set(sender);
	}

	@WrapOperation(method = "sendPackets", constant = @Constant(classValue = LivingEntity.class, ordinal = 0))
	private boolean updateEntityWithAttributesAttributes(Object object, Operation<Boolean> original, @Share("sender") LocalRef<Object> senderRef) {
		if (original.call(object)) {
			return true;
		}
		if (this.entity instanceof EntityWithAttributes entityWithAttributes && entityWithAttributes.shouldAutoSyncAttributes()) {
			Collection<EntityAttributeInstance> collection = entityWithAttributes.getAttributes().getAttributesToSend();
			if (!collection.isEmpty()) {
				try {
					// neoforge changes the type of the sender parameter
					Object sender = senderRef.get();
					Class<?> clazz = sender.getClass();
					String methodName = "accept";
					Class<?>[] parameterTypes = new Class[]{AtmosphericAPI.isConnectorLoaded ? Packet.class : Object.class};
					ReflectionCacher.MethodDescription desc = new ReflectionCacher.MethodDescription(clazz, methodName, parameterTypes);
					Method acceptMethod = ReflectionCacher.METHODS.computeIfAbsent(desc, desc1 -> {
						try {
							return clazz.getMethod(methodName, parameterTypes);
						} catch (NoSuchMethodException noSuchMethodException) {
							return null;
						}
					});
					acceptMethod.invoke(sender, new EntityAttributesS2CPacket(this.entity.getId(), collection));
				} catch (Throwable throwable) {
					AtmosphericAPI.LOGGER.error("Error while doing reflection to get a EntityWithAttributes's attributes!", throwable);
				}
			}
		}
		return false;
	}

	@WrapOperation(method = "syncEntityData", constant = @Constant(classValue = LivingEntity.class, ordinal = 0))
	private boolean updateEntityWithAttributesAttributes(Object object, Operation<Boolean> original) {
		if (original.call(object)) {
			return true;
		}
		if (this.entity instanceof EntityWithAttributes entityWithAttributes && entityWithAttributes.shouldAutoSyncAttributes()) {
			Set<EntityAttributeInstance> set = entityWithAttributes.getAttributes().getTracked();
			if (!set.isEmpty()) {
				this.sendSyncPacket(new EntityAttributesS2CPacket(this.entity.getId(), set));
			}
			set.clear();
		}
		return false;
	}
}
