package org.sashaiolh.iolhcrutches.Utils;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.minecraft.server.level.ServerPlayer;

public class PermissionUtils {
    public static boolean hasPermission(ServerPlayer player, String permission) {
        User user = LuckPermsProvider.get().getPlayerAdapter(ServerPlayer.class).getUser(player);
        return user.getCachedData().getPermissionData().checkPermission(permission).asBoolean();
    }
}
