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

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
//? if >=1.21.3
import net.minecraft.client.player.ClientInput;
//? if <=1.21.2
//import net.minecraft.client.player.Input;
import net.minecraft.client.player.LocalPlayer;
//~ if >=26 'net.minecraft.world.entity.vehicle.AbstractBoat' -> 'net.minecraft.world.entity.vehicle.boat.AbstractBoat' {
//? if >=1.21.3
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
//~}
//? if <=1.21.2
//import net.minecraft.world.entity.vehicle.Boat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import survivalblock.atmosphere.atmospheric_api.not_mixin.entity.ControlBoarder;

@Mixin(LocalPlayer.class)
public class ClientPlayerEntityMixin {

    @Shadow
    public /*? <=1.21.2 {*/ /*Input *//*?} else {*/ ClientInput /*?}*/ input;

    //~ if >1.21.2 'Boat' -> 'AbstractBoat'
    @WrapOperation(method = "rideTick", constant = @Constant(classValue = AbstractBoat.class, ordinal = 0))
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
