package org.sashaiolh.iolhcrutches.Minecraft;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.function.Function;
import java.util.function.Supplier;

public class ServerData extends SavedData {
    private static final String DATA_NAME = "server_data";

    private boolean alwaysDay;
    private boolean alwaysClear;

    public boolean getAlwaysDayState() {
        return alwaysDay;
    }

    public void setAlwaysDayState(boolean boolean1) {
        this.alwaysDay = boolean1;
        this.setDirty();
    }

    public boolean getAlwaysClearState() {
        return alwaysClear;
    }

    public void setAlwaysClearState(boolean boolean2) {
        this.alwaysClear = boolean2;
        this.setDirty();
    }

    public static ServerData load(CompoundTag nbt) {
        ServerData data = new ServerData();
        data.alwaysDay = nbt.getBoolean("alwaysDay");
        data.alwaysClear = nbt.getBoolean("alwaysClear");
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag nbt) {
        nbt.putBoolean("alwaysDay", this.alwaysDay);
        nbt.putBoolean("alwaysClear", this.alwaysClear);
        return nbt;
    }

    public static ServerData get(ServerLevel serverLevel) {
        return serverLevel.getDataStorage().computeIfAbsent(ServerData::load, ServerData::new, DATA_NAME);
    }

    // Utility methods to interact with the server data
    public static void setAlwaysDayState(MinecraftServer server, boolean value) {
        ServerLevel serverLevel = server.getLevel(ServerLevel.OVERWORLD);
        if (serverLevel != null) {
            ServerData data = ServerData.get(serverLevel);
            data.setAlwaysDayState(value);
            GameRulesRegistry.alwaysDayGameruleState = value;
        }
    }

    public static boolean getAlwaysDayState(MinecraftServer server) {
        ServerLevel serverLevel = server.getLevel(ServerLevel.OVERWORLD);
        if (serverLevel != null) {
            ServerData data = ServerData.get(serverLevel);
            return data.getAlwaysDayState();
        }
        return false;
    }

    public static void setAlwaysClearState(MinecraftServer server, boolean value) {
        ServerLevel serverLevel = server.getLevel(ServerLevel.OVERWORLD);
        if (serverLevel != null) {
            ServerData data = ServerData.get(serverLevel);
            data.setAlwaysClearState(value);
            GameRulesRegistry.alwaysClearGameruleState = value;
        }
    }

    public static boolean getAlwaysClearState(MinecraftServer server) {
        ServerLevel serverLevel = server.getLevel(ServerLevel.OVERWORLD);
        if (serverLevel != null) {
            ServerData data = ServerData.get(serverLevel);
            return data.getAlwaysClearState();
        }
        return false;
    }
}
