package org.sashaiolh.iolhcrutches.Minecraft;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.sashaiolh.iolhcrutches.IolhCrutches;

@Mod.EventBusSubscriber
public class GameRulesRegistry {

    public static boolean alwaysClearGameruleState;
    public static boolean alwaysDayGameruleState;

    private static boolean statesLoaded = false;

    @SubscribeEvent
    public static void onServerStart(ServerStartingEvent event){
        alwaysDayGameruleState = ServerData.getAlwaysDayState(event.getServer());
        alwaysClearGameruleState = ServerData.getAlwaysClearState(event.getServer());
        statesLoaded = true;
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {

            if(!statesLoaded){
                return;
            }

            for (ServerLevel level : event.getServer().getAllLevels()) {
                if (alwaysClearGameruleState) {
                    level.setWeatherParameters(6000, 0, false, false);
                }

                if (alwaysDayGameruleState) {
                    level.setDayTime(1000);
                }
            }
        }
    }
}
