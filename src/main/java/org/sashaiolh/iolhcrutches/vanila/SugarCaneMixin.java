package org.sashaiolh.iolhcrutches.vanila;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SugarCaneBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SugarCaneBlock.class)
public class SugarCaneMixin {
    @Inject(at = @At("HEAD"), method = "randomTick")
    public void onRandomTick(BlockState p_222548_, ServerLevel p_222549_, BlockPos p_222550_, RandomSource p_222551_, CallbackInfo ci) {
        int height = 0;
        while (p_222549_.getBlockState(p_222550_).getBlock() == Blocks.SUGAR_CANE) {
            height++;
            p_222550_ = p_222550_.below();
        }
        if (height >= 3) {
            ci.cancel();
        }
    }
}
