package org.sashaiolh.iolhcrutches.EnigmaticLegacy;


import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.sashaiolh.iolhcrutches.Utils.NbtUtils;


public class ModEventHandler {

    private static final String NBT_KEY_ENIGMATICGIFT = "enigmaticlegacy.firstjoin";
    private static final String NBT_KEY_CURSEDGIFT = "enigmaticlegacy.cursedgift";

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void playerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!NbtUtils.hasPersistentTag(event.getEntity(), NBT_KEY_ENIGMATICGIFT)) {
            NbtUtils.setPersistentBoolean(event.getEntity(), NBT_KEY_ENIGMATICGIFT, true);
            NbtUtils.setPersistentBoolean(event.getEntity(), NBT_KEY_CURSEDGIFT, true);
        }
    }
}

