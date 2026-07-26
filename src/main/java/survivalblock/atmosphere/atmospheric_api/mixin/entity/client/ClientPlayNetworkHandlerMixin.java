/*
 * Copyright (c) 21:56 UTC 26 July 2026 - present ekulxam
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package survivalblock.atmosphere.atmospheric_api.mixin.entity.client;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.atmospheric_api.not_mixin.entity.EntityWithAttributes;

@Mixin(ClientPacketListener.class)
public abstract class ClientPlayNetworkHandlerMixin {

    @Shadow @Final private static Logger LOGGER;

    @Inject(method = "handleUpdateAttributes", at = @At(value = "INVOKE", target = "Ljava/lang/IllegalStateException;<init>(Ljava/lang/String;)V", shift = At.Shift.BEFORE), cancellable = true)
    private void doNotCrashIfEntityWithAttributes(ClientboundUpdateAttributesPacket packet, CallbackInfo ci, @Local Entity entity) {
        if (!(entity instanceof EntityWithAttributes entityWithAttributes)) {
            return;
        }
        AttributeMap attributeContainer = entityWithAttributes.getAttributes();
        for (ClientboundUpdateAttributesPacket.AttributeSnapshot entry : packet.getValues()) {
            AttributeInstance entityAttributeInstance = attributeContainer.getInstance(entry.attribute());
            if (entityAttributeInstance == null) {
                LOGGER.warn("Entity {} does not have attribute {}", entity, entry.attribute().getRegisteredName());
            } else {
                entityAttributeInstance.setBaseValue(entry.base());
                entityAttributeInstance.removeModifiers();

                for (AttributeModifier entityAttributeModifier : entry.modifiers()) {
                    entityAttributeInstance.addTransientModifier(entityAttributeModifier);
                }
            }
        }
        ci.cancel();
    }
}
