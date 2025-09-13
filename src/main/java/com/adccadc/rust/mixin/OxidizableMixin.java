package com.adccadc.rust.mixin;

import com.adccadc.rust.OxidizeMap;
import com.google.common.collect.BiMap;
import net.minecraft.block.Block;
import net.minecraft.block.Oxidizable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(value = Oxidizable.class, remap = false)
public interface OxidizableMixin {

    @Redirect(
            method = "getIncreasedOxidationBlock(Lnet/minecraft/block/Block;)Ljava/util/Optional;",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Optional;ofNullable(Ljava/lang/Object;)Ljava/util/Optional;"
            ),
            remap = false
    )

    private static Optional<Block> modifyGetIncreasedOxidationBlock(Object originalValue, Block block) {
        return Optional.ofNullable((Block) ((BiMap) OxidizeMap.MOD_OXIDATION_LEVEL_INCREASES.get()).get(block));
    }

    @Redirect(
            method = "getDecreasedOxidationBlock(Lnet/minecraft/block/Block;)Ljava/util/Optional;",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Optional;ofNullable(Ljava/lang/Object;)Ljava/util/Optional;"
            ),
            remap = false
    )

    private static Optional<Block> modifyGetDecreasedOxidationBlock(Object originalValue, Block block) {
        return Optional.ofNullable((Block) ((BiMap) OxidizeMap.MOD_OXIDATION_LEVEL_DECREASES.get()).get(block));
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