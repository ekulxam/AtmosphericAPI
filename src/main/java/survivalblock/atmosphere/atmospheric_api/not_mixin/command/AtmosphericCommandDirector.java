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
package survivalblock.atmosphere.atmospheric_api.not_mixin.command;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
//? if >=1.21.11
import net.minecraft.server.permissions.PermissionSet;
import net.minecraft.world.entity.Entity;

@SuppressWarnings("unused")
public final class AtmosphericCommandDirector {
    public static void runCommand(ServerLevel serverWorld, Entity entity, String command) {
        runCommand(serverWorld, entity,/*? <1.21.11 {*/ /*4 *//*?} else {*/ PermissionSet.ALL_PERMISSIONS /*?}*/, command);
    }

    //~ if >=1.21.11 'int level' -> 'PermissionSet permissions' {
    //~ if >=1.21.11 'level' -> 'permissions' {
    public static void runCommand(ServerLevel serverWorld, Entity entity, PermissionSet permissions, String command) {
        runCommand(serverWorld.getServer(), new CommandSourceStack(/*? =1.21.1 {*/  /*entity *//*?} else {*/ entity instanceof ServerPlayer serverPlayer ? serverPlayer.commandSource() : CommandSource.NULL /*?}*/, entity.position(), entity.getRotationVector(), serverWorld, permissions,
                entity.getName().getString(), entity.getDisplayName(), serverWorld.getServer(), entity), command);
    }
    //~}
    //~}

    public static void runCommand(MinecraftServer server, CommandSourceStack serverCommandSource, String command) {
        runCommand(server.getCommands(), serverCommandSource, command);
    }

    public static void runCommand(Commands commandManager, CommandSourceStack serverCommandSource, String command) {
        commandManager.performPrefixedCommand(serverCommandSource, command);
    }
}