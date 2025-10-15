package survivalblock.atmosphere.atmospheric_api.not_mixin.command;

import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.CommandBlockExecutor;

@SuppressWarnings("unused")
public final class AtmosphericCommandDirector {
    public static void runCommand(ServerWorld serverWorld, Entity entity, String command) {
        runCommand(serverWorld, entity,4, command);
    }

    public static void runCommand(ServerWorld serverWorld, Entity entity, int level, String command) {
        runCommand(serverWorld.getServer(), new ServerCommandSource(/*? =1.21.1 {*/  entity /*?} else {*/ /*entity instanceof ServerPlayerEntity serverPlayerEntity ? serverPlayerEntity.getCommandOutput() : CommandOutput.DUMMY *//*?}*/, entity.getPos(), entity.getRotationClient(), serverWorld, level,
                entity.getName().getString(), entity.getDisplayName(), serverWorld.getServer(), entity), command);
    }

    public static void runCommand(CommandBlockExecutor commandBlockExecutor) {
        runCommand(commandBlockExecutor.getWorld().getServer(), commandBlockExecutor.getSource(), commandBlockExecutor.getCommand());
    }

    public static void runCommand(ServerWorld serverWorld, CommandBlockExecutor commandBlockExecutor, String command) {
        runCommand(serverWorld.getServer(), commandBlockExecutor.getSource(), command);
    }

    public static void runCommand(MinecraftServer server, CommandBlockExecutor commandBlockExecutor, String command) {
        runCommand(server, commandBlockExecutor.getSource(), command);
    }

    public static void runCommand(MinecraftServer server, ServerCommandSource serverCommandSource, String command) {
        runCommand(server.getCommandManager(), serverCommandSource, command);
    }

    public static void runCommand(CommandManager commandManager, ServerCommandSource serverCommandSource, String command) {
        commandManager.executeWithPrefix(serverCommandSource, command);
    }
}