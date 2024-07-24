package org.sashaiolh.iolhcrutches.mixins.forbidden_arcanus;

import com.stal111.forbidden_arcanus.common.item.QuantumCatcherItem;
import net.minecraft.core.Registry;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.sashaiolh.iolhcrutches.ModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.regex.Pattern;

@Mixin(value = QuantumCatcherItem.class, remap = false)
public class QuantumCatcherItemMixin {
    @Inject(method = "onEntityInteract", at = @At("HEAD"), cancellable = true)
    public void onEntityInteract(ItemStack stack, Player player, LivingEntity target, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir){
        for (String blackListedEntity : ModConfig.COMMON.forbidden_arcanus_quantum_catcher_blacklist_entities.get()) {
            if(Pattern.matches(blackListedEntity, Registry.ENTITY_TYPE.getKey(target.getType()).toString())){
                cir.setReturnValue(InteractionResult.PASS);
                return;
            }
        }
    }
}