package org.sashaiolh.iolhcrutches.mixins;

import com.mojang.authlib.GameProfile;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.commands.DeOpCommands;
import net.minecraft.server.commands.OpCommand;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraftforge.server.permission.PermissionAPI;
import net.minecraftforge.server.permission.nodes.PermissionNode;
import org.sashaiolh.iolhcrutches.IolhCrutches;
import org.sashaiolh.iolhcrutches.Utils.PermissionUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;


@Mixin(OpCommand.class)
public class CommandOpMixin {
    @Inject(method = "opPlayers", at = @At("HEAD"), cancellable = true)
    private static void onOpPlayers(CommandSourceStack p_138089_, Collection<GameProfile> p_138090_, CallbackInfoReturnable<Integer> cir) {
        ServerPlayer player = p_138089_.getPlayer();
        ServerPlayer targetPlayer = null;

        PlayerList playerList = p_138089_.getServer().getPlayerList();
        for (GameProfile profile : p_138090_) {
            targetPlayer = playerList.getPlayer(profile.getId());
        }

        if (player != null) {
            if(PermissionUtils.hasPermission(player, "allow.op.self") && targetPlayer != null && targetPlayer.getUUID().equals(player.getUUID())) {
                IolhCrutches.LOGGER.info(player.getDisplayName().getString()+" опнул себя");
            }
            else if(PermissionUtils.hasPermission(player, "allow.op.other")){
                if(targetPlayer!=null){
                    IolhCrutches.LOGGER.info(player.getDisplayName().getString()+" опнул "+targetPlayer.getDisplayName().getString());
                }
                else {
                    IolhCrutches.LOGGER.info(player.getDisplayName().getString()+" попытался кого-то опнуть, targetPlayer=null");
                }
            }
            else {
                player.displayClientMessage(Component.literal("РУКИ УБРАЛ"), false);
                cir.cancel();
                return;
            }
        }
        else {
            IolhCrutches.LOGGER.info("КУДА ЛЕЗЕМ");
            cir.cancel();
            return;
        }
    }
}
