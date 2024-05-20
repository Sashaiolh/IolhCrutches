package org.sashaiolh.iolhcrutches.Minecraft;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class GameRulesRegistry {
    public static final GameRules.Key<GameRules.BooleanValue> ALWAYS_CLEAR_RULE = GameruleUtilities.registerBooleanRule("alwaysClear", GameRules.Category.MISC, false);
    public static final GameRules.Key<GameRules.BooleanValue> ALWAYS_DAY_RULE = GameruleUtilities.registerBooleanRule("alwaysDay", GameRules.Category.MISC, false);

    // Обработка серверного тика для установки ясной погоды
    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            for (ServerLevel level : event.getServer().getAllLevels()) {
                // Проверка и установка ясной погоды
                boolean alwaysClear = GameruleUtilities.getBooleanGamerule(level, ALWAYS_CLEAR_RULE);
                if (alwaysClear) {
                    level.setWeatherParameters(6000, 0, false, false);
                }

                // Проверка и установка постоянного дня
                boolean alwaysDay = GameruleUtilities.getBooleanGamerule(level, ALWAYS_DAY_RULE);
                if (alwaysDay) {
                    level.setDayTime(1000); // Устанавливаем время на утро
                }
            }
        }
    }
}
