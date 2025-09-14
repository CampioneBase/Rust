package com.adccadc.rust.mixin;

import com.adccadc.rust.RustConfig;
import com.adccadc.rust.block.OxidizableChainBlock;
import com.adccadc.rust.block.OxidizableLanternBlock;
import com.adccadc.rust.block.OxidizablePaneBlock;
import com.adccadc.rust.block.OxidizableWeightedPressurePlateBlock;
import net.minecraft.block.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Function;

@Mixin(Blocks.class)
public class BlocksMixin {

    @Unique
    private static Block register1(RegistryKey<Block> key, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        Block block = (Block)factory.apply(settings.registryKey(key));
        return (Block) Registry.register(Registries.BLOCK, key, block);
    }

    @Unique
    private static Block register1(RegistryKey<Block> key, AbstractBlock.Settings settings) {
        return register1(key, Block::new, settings);
    }

    @Unique
    private static RegistryKey<Block> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.ofVanilla(id));
    }

    @Unique
    private static Block register1(String id, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        return register1(keyOf(id), factory, settings);
    }

    @Inject(
            method = "register(Ljava/lang/String;Lnet/minecraft/block/AbstractBlock$Settings;)Lnet/minecraft/block/Block;",
            at = @At("HEAD"),
            cancellable = true
    )

    private static void Register(String id, AbstractBlock.Settings settings, CallbackInfoReturnable<Block> cir) {
        if(!RustConfig.useLegacyOxidizeLogic()) {
            if ("iron_block".equals(id)) {
                cir.setReturnValue(register1(id, (Settings) -> new OxidizableBlock(Oxidizable.OxidationLevel.UNAFFECTED, Settings), settings));
                cir.cancel();
            }
        }
    }

    @Inject(
            method = "register(Ljava/lang/String;Ljava/util/function/Function;Lnet/minecraft/block/AbstractBlock$Settings;)Lnet/minecraft/block/Block;",
            at = @At("HEAD"),
            cancellable = true
    )

    private static void Register(String id, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings, CallbackInfoReturnable<Block> cir) {
        if(!RustConfig.useLegacyOxidizeLogic()) {
            if ("iron_bars".equals(id)) {
                cir.setReturnValue(register1(keyOf(id), (Settings) -> new OxidizablePaneBlock(Oxidizable.OxidationLevel.UNAFFECTED, Settings), settings));
                cir.cancel();
            }
            if ("iron_trapdoor".equals(id)) {
                cir.setReturnValue(register1(keyOf(id), (Settings) -> new OxidizableTrapdoorBlock(BlockSetType.IRON, Oxidizable.OxidationLevel.UNAFFECTED, Settings), settings));
                cir.cancel();
            }
            if ("iron_door".equals(id)) {
                cir.setReturnValue(register1(keyOf(id), (Settings) -> new OxidizableDoorBlock(BlockSetType.IRON, Oxidizable.OxidationLevel.UNAFFECTED, Settings), settings));
                cir.cancel();
            }
            if ("chain".equals(id)) {
                cir.setReturnValue(register1(keyOf(id), (Settings) -> new OxidizableChainBlock(Oxidizable.OxidationLevel.UNAFFECTED, Settings), settings));
                cir.cancel();
            }
            if ("heavy_weighted_pressure_plate".equals(id)) {
                cir.setReturnValue(register1(keyOf(id), (Settings) -> new OxidizableWeightedPressurePlateBlock(150, BlockSetType.IRON, Oxidizable.OxidationLevel.UNAFFECTED, Settings), settings));
                cir.cancel();
            }
            if ("lantern".equals(id)) {
                cir.setReturnValue(register1(keyOf(id), (Settings) -> new OxidizableLanternBlock(Oxidizable.OxidationLevel.UNAFFECTED, Settings), settings));
                cir.cancel();
            }
            if ("soul_lantern".equals(id)) {
                cir.setReturnValue(register1(keyOf(id), (Settings) -> new OxidizableLanternBlock(Oxidizable.OxidationLevel.UNAFFECTED, Settings), settings));
                cir.cancel();
            }
        }
    }
}
