package org.sashaiolh.iolhcrutches.Minecraft;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameRules.BooleanValue;
import net.minecraft.world.level.GameRules.IntegerValue;
import net.minecraft.world.level.GameRules.Category;
import net.minecraft.world.level.GameRules.Key;
import net.minecraft.world.level.GameRules.Type;
import net.minecraft.world.level.GameRules.Value;

/**
 * A collection of utilities for interacting with Minecraft's gamerules.
 */
public final class GameruleUtilities {
    /**
     * A map containing the gamerule keys and their IDs.
     */
    private static final Map<String, Key<?>> ruleIDMap = new HashMap<>();

    private static Type<BooleanValue> createBooleanType(boolean defaultValue) {
        try {
            Method createMethod = BooleanValue.class.getDeclaredMethod("create", boolean.class);
            createMethod.setAccessible(true);
            return (Type<BooleanValue>) createMethod.invoke(null, defaultValue);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Failed to create boolean type", e);
        }
    }

    private static Type<IntegerValue> createIntegerType(int defaultValue) {
        try {
            Method createMethod = IntegerValue.class.getDeclaredMethod("create", int.class);
            createMethod.setAccessible(true);
            return (Type<IntegerValue>) createMethod.invoke(null, defaultValue);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Failed to create integer type", e);
        }
    }

    /**
     * Register a new boolean gamerule.
     *
     * @param name         The name of the gamerule to register. Must be unique.
     * @param category     The category to put the gamerule under.
     * @param defaultValue The boolean value to set the gamerule to by default.
     *
     * @return A key that can be used to access the gamerule.
     */
    public static Key<BooleanValue> registerBooleanRule(String name, Category category, boolean defaultValue) {
        Type<BooleanValue> type = createBooleanType(defaultValue);
        Key<BooleanValue> key = GameRules.register(name, category, type);
        ruleIDMap.put(name, key);
        return key;
    }

    /**
     * Register a new integer gamerule.
     *
     * @param name         The name of the gamerule to register. Must be unique.
     * @param category     The category to put the gamerule under.
     * @param defaultValue The integer value to set the gamerule to by default.
     *
     * @return A key that can be used to access the gamerule.
     */
    public static Key<IntegerValue> registerIntegerRule(String name, Category category, int defaultValue) {
        Type<IntegerValue> type = createIntegerType(defaultValue);
        Key<IntegerValue> key = GameRules.register(name, category, type);
        ruleIDMap.put(name, key);
        return key;
    }

    /**
     * Get the value associated with a gamerule.
     *
     * @param <T>   The type of the value held in the gamerule.
     * @param level The level to retrieve the gamerule value from.
     * @param key   The key to retrieve the gamerule for.
     *
     * @return The value wrapper object for the gamerule.
     */
    public static <T extends Value<T>> T getGamerule(Level level, Key<T> key) {
        return level.getGameRules().getRule(key);
    }

    /**
     * Get the value associated with a gamerule.
     *
     * @param <T>   The type of the value held in the gamerule.
     * @param level The level to retrieve the gamerule value from.
     * @param id    The id to retrieve the gamerule for.
     *
     * @return The value wrapper object for the gamerule.
     */
    public static <T extends Value<T>> T getGamerule(Level level, String id) {
        @SuppressWarnings("unchecked")
        var key = (Key<T>) ruleIDMap.get(id);
        return level.getGameRules().getRule(key);
    }

    /**
     * Get the boolean value associated with a gamerule.
     *
     * @param level The level to retrieve the gamerule value from.
     * @param key   The key to retrieve the gamerule for.
     *
     * @return The boolean value for the gamerule.
     */
    public static boolean getBooleanGamerule(Level level, Key<BooleanValue> key) {
        return getGamerule(level, key).get();
    }

    /**
     * Get the boolean value associated with a gamerule.
     *
     * @param level The level to retrieve the gamerule value from.
     * @param id    The id to retrieve the gamerule for.
     *
     * @return The boolean value for the gamerule.
     */
    public static boolean getBooleanGamerule(Level level, String id) {
        return GameruleUtilities.<BooleanValue>getGamerule(level, id).get();
    }

    /**
     * Get the integer value associated with a gamerule.
     *
     * @param level The level to retrieve the gamerule value from.
     * @param key   The key to retrieve the gamerule for.
     *
     * @return The integer value for the gamerule.
     */
    public static int getIntegerGamerule(Level level, Key<IntegerValue> key) {
        return getGamerule(level, key).get();
    }

    /**
     * Get the integer value associated with a gamerule.
     *
     * @param level The level to retrieve the gamerule value from.
     * @param id    The id to retrieve the gamerule for.
     *
     * @return The integer value for the gamerule.
     */
    public static int getIntegerGamerule(Level level, String id) {
        return GameruleUtilities.<IntegerValue>getGamerule(level, id).get();
    }
}
