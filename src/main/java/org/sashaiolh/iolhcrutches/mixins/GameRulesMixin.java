//package org.sashaiolh.iolhcrutches.mixins;
//
//import net.minecraft.world.level.GameRules;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Mutable;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.gen.Invoker;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//import java.util.Map;
//
//@Mixin(GameRules.class)
//public abstract class GameRulesMixin {
//
//    @Shadow @Mutable
//    private static Map<GameRules.Key<?>, GameRules.Type<?>> GAME_RULE_TYPES;
//
//    @Invoker("create")
//    private static <T extends GameRules.Value<T>> GameRules.Type<T> invokeCreate(boolean defaultValue) {
//        throw new AssertionError();
//    }
//
//    @Invoker("register")
//    private static <T extends GameRules.Value<T>> GameRules.Key<T> invokeRegister(String name, GameRules.Category category, GameRules.Type<T> type) {
//        throw new AssertionError();
//    }
//
//    @Inject(method = "<clinit>", at = @At("RETURN"))
//    private static void registerCustomGameRules(CallbackInfo ci) {
//        invokeRegister("alwaysClear", GameRules.Category.UPDATES, invokeCreate(false));
//    }
//}
