/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.command;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

@SuppressWarnings("unused")
public final class AtmosphericCommandDirector {
    public static void runCommand(ServerLevel serverWorld, Entity entity, String command) {
        runCommand(serverWorld, entity,4, command);
    }

    public static void runCommand(ServerLevel serverWorld, Entity entity, int level, String command) {
        runCommand(serverWorld.getServer(), new CommandSourceStack(/*? =1.21.1 {*/  /*entity *//*?} else {*/ entity instanceof ServerPlayer serverPlayer ? serverPlayer.commandSource() : CommandSource.NULL /*?}*/, entity.position(), entity.getRotationVector(), serverWorld, level,
                entity.getName().getString(), entity.getDisplayName(), serverWorld.getServer(), entity), command);
    }

    public static void runCommand(MinecraftServer server, CommandSourceStack serverCommandSource, String command) {
        runCommand(server.getCommands(), serverCommandSource, command);
    }

    public static void runCommand(Commands commandManager, CommandSourceStack serverCommandSource, String command) {
        commandManager.performPrefixedCommand(serverCommandSource, command);
    }
}