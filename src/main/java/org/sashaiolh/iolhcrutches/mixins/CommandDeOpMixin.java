package org.sashaiolh.iolhcrutches.mixins;

import com.mojang.authlib.GameProfile;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.commands.DeOpCommands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import org.sashaiolh.iolhcrutches.IolhCrutches;
import org.sashaiolh.iolhcrutches.Utils.PermissionUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;

@Mixin(DeOpCommands.class)
public class CommandDeOpMixin {
    @Inject(method = "deopPlayers", at = @At("HEAD"), cancellable = true)
    private static void onDeOpPlayers(CommandSourceStack p_138089_, Collection<GameProfile> p_138090_, CallbackInfoReturnable<Integer> cir) {
        ServerPlayer player = p_138089_.getPlayer();
        ServerPlayer targetPlayer = null;

        PlayerList playerList = p_138089_.getServer().getPlayerList();
        for (GameProfile profile : p_138090_) {
            targetPlayer = playerList.getPlayer(profile.getId());
        }

        if (player != null) {
            if(PermissionUtils.hasPermission(player, "allow.deop.self") && targetPlayer != null && targetPlayer.getUUID().equals(player.getUUID())) {
                IolhCrutches.LOGGER.info(player.getDisplayName().getString()+" деопнул себя");
            }
            else if(PermissionUtils.hasPermission(player, "allow.deop.other")){
                if(targetPlayer!=null){
                    IolhCrutches.LOGGER.info(player.getDisplayName().getString()+" деопнул "+targetPlayer.getDisplayName().getString());
                }
                else {
                    IolhCrutches.LOGGER.info(player.getDisplayName().getString()+" попытался кого-то деопнуть, targetPlayer=null");
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
