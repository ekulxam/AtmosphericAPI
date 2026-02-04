/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.mixin.entity.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
//? if >=1.21.3
import net.minecraft.client.player.ClientInput;
//? if <=1.21.2
/*import net.minecraft.client.player.Input;*/
import net.minecraft.client.player.LocalPlayer;
//? if >=1.21.3
import net.minecraft.world.entity.vehicle.AbstractBoat;
import net.minecraft.world.entity.vehicle.Boat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import survivalblock.atmosphere.atmospheric_api.not_mixin.entity.ControlBoarder;

@Mixin(LocalPlayer.class)
public class ClientPlayerEntityMixin {

    @Shadow
    public /*? <=1.21.2 {*/ /*Input *//*?} else {*/ ClientInput /*?}*/ input;

    @WrapOperation(method = "rideTick", constant = @Constant(classValue = /*? <=1.21.2 {*/ /*Boat *//*?} else {*/ AbstractBoat /*?}*/.class, ordinal = 0))
    private boolean controlBoard(Object obj, Operation<Boolean> original) {
        if (original.call(obj)) {
            return true;
        }

        if (obj instanceof ControlBoarder controlBoarder) {
            controlBoarder.setInputs(this.input./*? <=1.21.2 {*/ /*left *//*?} else {*/ keyPresses.left() /*?}*/, this.input./*? <=1.21.2 {*/ /*right *//*?} else {*/ keyPresses.right() /*?}*/, this.input./*? <=1.21.2 {*/ /*up *//*?} else {*/ keyPresses.forward() /*?}*/, this.input./*? <=1.21.2 {*/ /*down *//*?} else {*/ keyPresses.backward() /*?}*/);
        }
        return false;
    }
}
