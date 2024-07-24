package org.sashaiolh.iolhcrutches.Minecraft;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import org.sashaiolh.iolhcrutches.IolhCrutches;

import java.util.List;
import java.util.Map;

public class GameruleCommandCrutcher {
    // SuggestionProvider для автодополнения по никнеймам игроков
    private static final SuggestionProvider<CommandSourceStack> PLAYER_SUGGESTIONS = (context, builder) -> {
        List<ServerPlayer> players = context.getSource().getServer().getPlayerList().getPlayers();
        for (ServerPlayer player : players) {
            builder.suggest(player.getName().getString());
        }
        return builder.buildFuture();
    };

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("gamerule")
                        .requires(source -> source.hasPermission(4))
                        .then(
                                Commands.literal("alwaysDay")
                                        .then(
                                                Commands.literal("true")
                                                        .executes(context -> {
                                                            ServerData.setAlwaysDayState(context.getSource().getServer(), true);
                                                            sendMessage(context.getSource(), "Done!");
                                                            return 1;
                                                        })
                                        )
                                        .then(
                                                Commands.literal("false")
                                                        .executes(context -> {
                                                            ServerData.setAlwaysDayState(context.getSource().getServer(), false);
                                                            sendMessage(context.getSource(), "Done!");
                                                            return 1;
                                                        })
                                        )
                        )
                        .then(
                                Commands.literal("alwaysClear")
                                        .then(
                                                Commands.literal("true")
                                                        .executes(context -> {
                                                            ServerData.setAlwaysClearState(context.getSource().getServer(), true);
                                                            sendMessage(context.getSource(), "Done!");
                                                            return 1;
                                                        })
                                        )
                                        .then(
                                                Commands.literal("false")
                                                        .executes(context -> {
                                                            ServerData.setAlwaysClearState(context.getSource().getServer(), false);
                                                            sendMessage(context.getSource(), "Done!");
                                                            return 1;
                                                        })
                                        )
                        )
        );
    }


    private static void sendMessage(CommandSourceStack src, String message) {
        ServerPlayer player = src.getPlayer();
        if(player==null){
            System.out.println("Done!");
        }
        else {
            player.displayClientMessage(Component.literal(message), false);
        }

    }
}
