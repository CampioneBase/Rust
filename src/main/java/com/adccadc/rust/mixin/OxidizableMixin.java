package com.adccadc.rust.mixin;

import com.adccadc.rust.OxidizeMap;
import com.google.common.collect.BiMap;
import net.minecraft.block.Block;
import net.minecraft.block.Oxidizable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(value = Oxidizable.class)
public interface OxidizableMixin {

    @Inject(
            method = "getIncreasedOxidationBlock",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void modifyGetIncreasedOxidationBlock(Block block, CallbackInfoReturnable<Optional<Block>> cir) {
        cir.setReturnValue(Optional.ofNullable((Block) ((BiMap) OxidizeMap.MOD_OXIDATION_LEVEL_INCREASES.get()).get(block)));
        cir.cancel();
    }

    @Inject(
        method = "getDecreasedOxidationBlock",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void modifyGetDecreasedOxidationBlock(Block block, CallbackInfoReturnable<Optional<Block>> cir) {
        cir.setReturnValue(Optional.ofNullable((Block) ((BiMap) OxidizeMap.MOD_OXIDATION_LEVEL_DECREASES.get()).get(block)));
        cir.cancel();
    }

    @Inject(
            method = "getUnaffectedOxidationBlock",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void modifyGetUnaffectedOxidationBlock(Block block, CallbackInfoReturnable<Block> cir) {
        Block block2 = block;

        for(Block block3 = (Block)((BiMap) OxidizeMap.MOD_OXIDATION_LEVEL_DECREASES.get()).get(block); block3 != null; block3 = (Block)((BiMap) OxidizeMap.MOD_OXIDATION_LEVEL_DECREASES.get()).get(block3)) {
            block2 = block3;
        }

        cir.setReturnValue(block2);
        cir.cancel();
    }
}