/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.mixin.entity;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
//? if <=1.21.7
/*import org.jetbrains.annotations.Nullable;*/
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
//? if <=1.21.7
/*import org.spongepowered.asm.mixin.Unique;*/
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//? if <=1.21.7
/*import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;*/
import survivalblock.atmosphere.atmospheric_api.not_mixin.entity.EntityWithAttributes;
//? if <=1.21.7
/*import survivalblock.atmosphere.atmospheric_api.not_mixin.util.Reflector;*/

//? if <=1.21.7
/*import java.lang.invoke.MethodHandle;*/
import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;

@Mixin(ServerEntity.class)
public abstract class EntityTrackerEntryMixin {

	@Shadow @Final private Entity entity;

    //? if >=1.21.9 {
    /*@Shadow @Final private ServerEntity.Synchronizer synchronizer;
    *///?} else {
    @Shadow protected abstract void broadcastAndSend(Packet<?> packet);
    //?}

    //? if <=1.21.7 {
    /*@Nullable
    @Unique
    private static final MethodHandle atmospheric_api$PACKET_ACCEPTOR;

    static {
        boolean consumer = true;
        Class<?> clazz = Consumer.class;
        if (AtmosphericAPI.isConnectorLoaded) {
            try {
                clazz = Class.forName("net.neoforged.neoforge.network.bundle.PacketAndPayloadAcceptor");
                consumer = false;
            } catch (ClassNotFoundException ignored) {
            }
        }
        if (consumer) {
            atmospheric_api$PACKET_ACCEPTOR = null;
        } else {
            atmospheric_api$PACKET_ACCEPTOR = Reflector.methodHandle(
                    new Reflector.MethodDescription(
                            clazz,
                            "accept",
                            clazz, // returns itself on neo
                            new Class[]{Packet.class}
                    )
            );
        }
    }
    *///?}

    @Inject(method = "sendPairingData", at = @At("HEAD"))
	private void captureSender(ServerPlayer player, @Coerce Object sender, CallbackInfo ci, @Share("sender") LocalRef<Object> senderRef) {
		senderRef.set(sender);
	}

	@WrapOperation(method = "sendPairingData", constant = @Constant(classValue = LivingEntity.class, ordinal = 0))
	private boolean updateEntityWithAttributesAttributes(Object object, Operation<Boolean> original, @Share("sender") LocalRef<Object> senderRef) {
		if (original.call(object)) {
			return true;
		}
		if (this.entity instanceof EntityWithAttributes entityWithAttributes && entityWithAttributes.shouldAutoSyncAttributes()) {
			Collection<AttributeInstance> collection = entityWithAttributes.getAttributes().getSyncableAttributes();
			if (!collection.isEmpty()) {
                ClientboundUpdateAttributesPacket packet = new ClientboundUpdateAttributesPacket(this.entity.getId(), collection);
                //? if <=1.21.7 {
                /*if (AtmosphericAPI.isConnectorLoaded) {
                    try {
                        atmospheric_api$PACKET_ACCEPTOR.invokeExact(senderRef.get(), packet);
                    } catch (Throwable throwable) {
                        AtmosphericAPI.LOGGER.error("Error while doing reflection to get a EntityWithAttributes's attributes!", throwable);
                    }
                } else {
                *///?}
                    //noinspection unchecked
                    ((Consumer<Packet<?>>) senderRef.get()).accept(packet);
                //? if <=1.21.7
                /*}*/
			}
		}
		return false;
	}

	@WrapOperation(method = "sendDirtyEntityData", constant = @Constant(classValue = LivingEntity.class, ordinal = 0))
	private boolean updateEntityWithAttributesAttributes(Object object, Operation<Boolean> original) {
		if (original.call(object)) {
			return true;
		}
		if (this.entity instanceof EntityWithAttributes entityWithAttributes && entityWithAttributes.shouldAutoSyncAttributes()) {
			Set<AttributeInstance> set = entityWithAttributes.getAttributes().getAttributesToSync();
			if (!set.isEmpty()) {
				this./*? >=1.21.9 {*/ /*synchronizer.sendToTrackingPlayersAndSelf *//*?} else {*/ broadcastAndSend /*?}*/(new ClientboundUpdateAttributesPacket(this.entity.getId(), set));
			}
			set.clear();
		}
		return false;
	}
}
