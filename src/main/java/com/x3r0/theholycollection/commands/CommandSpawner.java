package com.x3r0.theholycollection.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.x3r0.theholycollection.networking.Networking;
import com.x3r0.theholycollection.networking.PacketOpenGui;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.network.NetworkDirection;

public class CommandSpawner implements Command<CommandSource> {

    private static final CommandSpawner CMD = new CommandSpawner();

    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {
        return Commands.literal("spawn")
                .requires(cs -> cs.hasPermissionLevel(0))
                .executes(CMD);
    }

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().asPlayer();
        Networking.INSTANCE.sendTo(new PacketOpenGui(), player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
        return 0;
    }
}