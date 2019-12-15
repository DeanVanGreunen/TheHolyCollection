package com.x3r0.theholycollection.commands;


import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.x3r0.theholycollection.dimension.ModDimensions;
import com.x3r0.theholycollection.tools.TeleportationTools;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;

public class CommandTpDim implements Command<CommandSource> {

    private static final CommandTpDim CMD = new CommandTpDim();

    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {
        return Commands.literal("dim")
                .requires(cs -> cs.hasPermissionLevel(0))
                .executes(CMD);
    }

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().asPlayer();
        if (player.dimension.equals(ModDimensions.DIMENSION_TYPE)) {
            TeleportationTools.teleport(player, DimensionType.OVERWORLD, new BlockPos(player.posX, 200, player.posZ));
        } else {
            TeleportationTools.teleport(player, ModDimensions.DIMENSION_TYPE, new BlockPos(player.posX, 200, player.posZ));
        }
        return 0;
    }
}