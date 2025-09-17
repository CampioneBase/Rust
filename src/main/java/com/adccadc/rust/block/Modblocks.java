package com.adccadc.rust.block;

import com.adccadc.rust.RustConfig;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.*;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

import java.util.function.Function;

public class Modblocks {
    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, BlockRenderLayer blockRenderLayer) {

        RegistryKey<Block> blockKey = keyOfBlock(name);

        Block block = blockFactory.apply(settings.registryKey(blockKey));

        Block block2 = Registry.register(Registries.BLOCK, blockKey, block);
        if(blockRenderLayer != null) {BlockRenderLayerMap.putBlock(block2, blockRenderLayer);}
        return block2;
    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of("rust", name));
    }

    // 铁块系列
    public static final Block EXPOSED_IRON_BLOCK = register("exposed_iron_block", (settings) -> new OxidizableBlock(Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.IRON_BLOCK), null);
    public static final Block WEATHERED_IRON_BLOCK = register("weathered_iron_block", (settings) -> new OxidizableBlock(Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.IRON_BLOCK), null);
    public static final Block OXIDIZED_IRON_BLOCK = register("oxidized_iron_block", (settings) -> new OxidizableBlock(Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.IRON_BLOCK), null);

    public static final Block WAXED_IRON_BLOCK = register("waxed_iron_block", (settings) -> new OxidizableBlock(Oxidizable.OxidationLevel.UNAFFECTED, settings), AbstractBlock.Settings.copy(Blocks.IRON_BLOCK), null);
    public static final Block WAXED_EXPOSED_IRON_BLOCK = register("waxed_exposed_iron_block", (settings) -> new OxidizableBlock(Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.IRON_BLOCK), null);
    public static final Block WAXED_WEATHERED_IRON_BLOCK = register("waxed_weathered_iron_block", (settings) -> new OxidizableBlock(Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.IRON_BLOCK), null);
    public static final Block WAXED_OXIDIZED_IRON_BLOCK = register("waxed_oxidized_iron_block", (settings) -> new OxidizableBlock(Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.IRON_BLOCK), null);

    // 铁栏杆系列
    public static final Block EXPOSED_IRON_BARS = register("exposed_iron_bars", (settings) -> new OxidizablePaneBlock(Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.IRON_BARS), BlockRenderLayer.CUTOUT);
    public static final Block WEATHERED_IRON_BARS = register("weathered_iron_bars", (settings) -> new OxidizablePaneBlock(Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.IRON_BARS), BlockRenderLayer.CUTOUT);
    public static final Block OXIDIZED_IRON_BARS = register("oxidized_iron_bars", (settings) -> new OxidizablePaneBlock(Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.IRON_BARS), BlockRenderLayer.CUTOUT);

    public static final Block WAXED_IRON_BARS = register("waxed_iron_bars", (settings) -> new OxidizablePaneBlock(Oxidizable.OxidationLevel.UNAFFECTED, settings), AbstractBlock.Settings.copy(Blocks.IRON_BARS), BlockRenderLayer.CUTOUT);
    public static final Block WAXED_EXPOSED_IRON_BARS = register("waxed_exposed_iron_bars", (settings) -> new OxidizablePaneBlock(Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.IRON_BARS), BlockRenderLayer.CUTOUT);
    public static final Block WAXED_WEATHERED_IRON_BARS = register("waxed_weathered_iron_bars", (settings) -> new OxidizablePaneBlock(Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.IRON_BARS), BlockRenderLayer.CUTOUT);
    public static final Block WAXED_OXIDIZED_IRON_BARS = register("waxed_oxidized_iron_bars", (settings) -> new OxidizablePaneBlock(Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.IRON_BARS), BlockRenderLayer.CUTOUT);

    // 铁活板门系列
    public static final Block EXPOSED_IRON_TRAPDOOR = register("exposed_iron_trapdoor", (settings) -> new OxidizableTrapdoorBlock(BlockSetType.IRON, Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.IRON_TRAPDOOR), BlockRenderLayer.CUTOUT);
    public static final Block WEATHERED_IRON_TRAPDOOR = register("weathered_iron_trapdoor", (settings) -> new OxidizableTrapdoorBlock(BlockSetType.IRON, Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.IRON_TRAPDOOR), BlockRenderLayer.CUTOUT);
    public static final Block OXIDIZED_IRON_TRAPDOOR = register("oxidized_iron_trapdoor", (settings) -> new OxidizableTrapdoorBlock(BlockSetType.IRON, Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.IRON_TRAPDOOR), BlockRenderLayer.CUTOUT);

    public static final Block WAXED_IRON_TRAPDOOR = register("waxed_iron_trapdoor", (settings) -> new OxidizableTrapdoorBlock(BlockSetType.IRON, Oxidizable.OxidationLevel.UNAFFECTED, settings), AbstractBlock.Settings.copy(Blocks.IRON_TRAPDOOR), BlockRenderLayer.CUTOUT);
    public static final Block WAXED_EXPOSED_IRON_TRAPDOOR = register("waxed_exposed_iron_trapdoor", (settings) -> new OxidizableTrapdoorBlock(BlockSetType.IRON, Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.IRON_TRAPDOOR), BlockRenderLayer.CUTOUT);
    public static final Block WAXED_WEATHERED_IRON_TRAPDOOR = register("waxed_weathered_iron_trapdoor", (settings) -> new OxidizableTrapdoorBlock(BlockSetType.IRON, Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.IRON_TRAPDOOR), BlockRenderLayer.CUTOUT);
    public static final Block WAXED_OXIDIZED_IRON_TRAPDOOR = register("waxed_oxidized_iron_trapdoor", (settings) -> new OxidizableTrapdoorBlock(BlockSetType.IRON, Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.IRON_TRAPDOOR), BlockRenderLayer.CUTOUT);

    // 铁门系列
    public static final Block EXPOSED_IRON_DOOR = register("exposed_iron_door", (settings) -> new OxidizableDoorBlock(BlockSetType.IRON, Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.IRON_DOOR), BlockRenderLayer.CUTOUT);
    public static final Block WEATHERED_IRON_DOOR = register("weathered_iron_door", (settings) -> new OxidizableDoorBlock(BlockSetType.IRON, Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.IRON_DOOR), BlockRenderLayer.CUTOUT);
    public static final Block OXIDIZED_IRON_DOOR = register("oxidized_iron_door", (settings) -> new OxidizableDoorBlock(BlockSetType.IRON, Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.IRON_DOOR), BlockRenderLayer.CUTOUT);

    public static final Block WAXED_IRON_DOOR = register("waxed_iron_door", (settings) -> new OxidizableDoorBlock(BlockSetType.IRON, Oxidizable.OxidationLevel.UNAFFECTED, settings), AbstractBlock.Settings.copy(Blocks.IRON_DOOR), BlockRenderLayer.CUTOUT);
    public static final Block WAXED_EXPOSED_IRON_DOOR = register("waxed_exposed_iron_door", (settings) -> new OxidizableDoorBlock(BlockSetType.IRON, Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.IRON_DOOR), BlockRenderLayer.CUTOUT);
    public static final Block WAXED_WEATHERED_IRON_DOOR = register("waxed_weathered_iron_door", (settings) -> new OxidizableDoorBlock(BlockSetType.IRON, Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.IRON_DOOR), BlockRenderLayer.CUTOUT);
    public static final Block WAXED_OXIDIZED_IRON_DOOR = register("waxed_oxidized_iron_door", (settings) -> new OxidizableDoorBlock(BlockSetType.IRON, Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.IRON_DOOR), BlockRenderLayer.CUTOUT);

    // 锁链系列
    public static final Block EXPOSED_IRON_CHAIN = register("exposed_iron_chain", (settings) -> new OxidizableChainBlock(Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.CHAIN), BlockRenderLayer.CUTOUT);
    public static final Block WEATHERED_IRON_CHAIN = register("weathered_iron_chain", (settings) -> new OxidizableChainBlock(Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.CHAIN), BlockRenderLayer.CUTOUT);
    public static final Block OXIDIZED_IRON_CHAIN = register("oxidized_iron_chain", (settings) -> new OxidizableChainBlock(Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.CHAIN), BlockRenderLayer.CUTOUT);

    public static final Block WAXED_IRON_CHAIN = register("waxed_iron_chain", (settings) -> new OxidizableChainBlock(Oxidizable.OxidationLevel.UNAFFECTED, settings), AbstractBlock.Settings.copy(Blocks.CHAIN), BlockRenderLayer.CUTOUT);
    public static final Block WAXED_EXPOSED_IRON_CHAIN = register("waxed_exposed_iron_chain", (settings) -> new OxidizableChainBlock(Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.CHAIN), BlockRenderLayer.CUTOUT);
    public static final Block WAXED_WEATHERED_IRON_CHAIN = register("waxed_weathered_iron_chain", (settings) -> new OxidizableChainBlock(Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.CHAIN), BlockRenderLayer.CUTOUT);
    public static final Block WAXED_OXIDIZED_IRON_CHAIN = register("waxed_oxidized_iron_chain", (settings) -> new OxidizableChainBlock(Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.CHAIN), BlockRenderLayer.CUTOUT);

    // 漏斗系列 -能力不足 放弃实现

    // 活塞系列 -材质改和没改差不多 不打算实现

    // 空炼药锅系列
    public static final Block EXPOSED_CAULDRON = register("exposed_cauldron", (settings) -> new OxidizableCauldronBlock(Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.CAULDRON), null);
    public static final Block WEATHERED_CAULDRON = register("weathered_cauldron", (settings) -> new OxidizableCauldronBlock(Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.CAULDRON), null);
    public static final Block OXIDIZED_CAULDRON = register("oxidized_cauldron", (settings) -> new OxidizableCauldronBlock(Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.CAULDRON), null);

    public static final Block WAXED_CAULDRON = register("waxed_cauldron", (settings) -> new OxidizableCauldronBlock(Oxidizable.OxidationLevel.UNAFFECTED, settings), AbstractBlock.Settings.copy(Blocks.CAULDRON), null);
    public static final Block WAXED_EXPOSED_CAULDRON = register("waxed_exposed_cauldron", (settings) -> new OxidizableCauldronBlock(Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.CAULDRON), null);
    public static final Block WAXED_WEATHERED_CAULDRON = register("waxed_weathered_cauldron", (settings) -> new OxidizableCauldronBlock(Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.CAULDRON), null);
    public static final Block WAXED_OXIDIZED_CAULDRON = register("waxed_oxidized_cauldron", (settings) -> new OxidizableCauldronBlock(Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.CAULDRON), null);

    // 含水炼药锅系列
    public static final Block EXPOSED_WATER_CAULDRON = register("exposed_water_cauldron", (settings) -> new OxidizableLeveledCauldronBlock(Biome.Precipitation.RAIN, CauldronBehavior.WATER_CAULDRON_BEHAVIOR, Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.WATER_CAULDRON), null);
    public static final Block WEATHERED_WATER_CAULDRON = register("weathered_water_cauldron", (settings) -> new OxidizableLeveledCauldronBlock(Biome.Precipitation.RAIN, CauldronBehavior.WATER_CAULDRON_BEHAVIOR, Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.WATER_CAULDRON), null);
    public static final Block OXIDIZED_WATER_CAULDRON = register("oxidized_water_cauldron", (settings) -> new OxidizableLeveledCauldronBlock(Biome.Precipitation.RAIN, CauldronBehavior.WATER_CAULDRON_BEHAVIOR, Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.WATER_CAULDRON), null);

    public static final Block WAXED_WATER_CAULDRON = register("waxed_water_cauldron", (settings) -> new OxidizableLeveledCauldronBlock(Biome.Precipitation.RAIN, CauldronBehavior.WATER_CAULDRON_BEHAVIOR, Oxidizable.OxidationLevel.UNAFFECTED, settings), AbstractBlock.Settings.copy(Blocks.WATER_CAULDRON), null);
    public static final Block WAXED_EXPOSED_WATER_CAULDRON = register("waxed_exposed_water_cauldron", (settings) -> new OxidizableLeveledCauldronBlock(Biome.Precipitation.RAIN, CauldronBehavior.WATER_CAULDRON_BEHAVIOR, Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.WATER_CAULDRON), null);
    public static final Block WAXED_WEATHERED_WATER_CAULDRON = register("waxed_weathered_water_cauldron", (settings) -> new OxidizableLeveledCauldronBlock(Biome.Precipitation.RAIN, CauldronBehavior.WATER_CAULDRON_BEHAVIOR, Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.WATER_CAULDRON), null);
    public static final Block WAXED_OXIDIZED_WATER_CAULDRON = register("waxed_oxidized_water_cauldron", (settings) -> new OxidizableLeveledCauldronBlock(Biome.Precipitation.RAIN, CauldronBehavior.WATER_CAULDRON_BEHAVIOR, Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.WATER_CAULDRON), null);

    // 含熔岩炼药锅系列
    public static final Block EXPOSED_LAVA_CAULDRON = register("exposed_lava_cauldron", (settings) -> new OxidizableLavaCauldronBlock(Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.LAVA_CAULDRON), null);
    public static final Block WEATHERED_LAVA_CAULDRON = register("weathered_lava_cauldron", (settings) -> new OxidizableLavaCauldronBlock(Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.LAVA_CAULDRON), null);
    public static final Block OXIDIZED_LAVA_CAULDRON = register("oxidized_lava_cauldron", (settings) -> new OxidizableLavaCauldronBlock(Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.LAVA_CAULDRON), null);

    public static final Block WAXED_LAVA_CAULDRON = register("waxed_lava_cauldron", (settings) -> new OxidizableLavaCauldronBlock(Oxidizable.OxidationLevel.UNAFFECTED, settings), AbstractBlock.Settings.copy(Blocks.LAVA_CAULDRON), null);
    public static final Block WAXED_EXPOSED_LAVA_CAULDRON = register("waxed_exposed_lava_cauldron", (settings) -> new OxidizableLavaCauldronBlock(Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.LAVA_CAULDRON), null);
    public static final Block WAXED_WEATHERED_LAVA_CAULDRON = register("waxed_weathered_lava_cauldron", (settings) -> new OxidizableLavaCauldronBlock(Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.LAVA_CAULDRON), null);
    public static final Block WAXED_OXIDIZED_LAVA_CAULDRON = register("waxed_oxidized_lava_cauldron", (settings) -> new OxidizableLavaCauldronBlock(Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.LAVA_CAULDRON), null);

    // 含雪炼药锅系列
    public static final Block EXPOSED_POWDER_SNOW_CAULDRON = register("exposed_powder_snow_cauldron", (settings) -> new OxidizableLeveledCauldronBlock(Biome.Precipitation.SNOW, CauldronBehavior.POWDER_SNOW_CAULDRON_BEHAVIOR, Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.POWDER_SNOW_CAULDRON), null);
    public static final Block WEATHERED_POWDER_SNOW_CAULDRON = register("weathered_powder_snow_cauldron", (settings) -> new OxidizableLeveledCauldronBlock(Biome.Precipitation.SNOW, CauldronBehavior.POWDER_SNOW_CAULDRON_BEHAVIOR, Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.POWDER_SNOW_CAULDRON), null);
    public static final Block OXIDIZED_POWDER_SNOW_CAULDRON = register("oxidized_powder_snow_cauldron", (settings) -> new OxidizableLeveledCauldronBlock(Biome.Precipitation.SNOW, CauldronBehavior.POWDER_SNOW_CAULDRON_BEHAVIOR, Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.POWDER_SNOW_CAULDRON), null);

    public static final Block WAXED_POWDER_SNOW_CAULDRON = register("waxed_powder_snow_cauldron", (settings) -> new OxidizableLeveledCauldronBlock(Biome.Precipitation.SNOW, CauldronBehavior.POWDER_SNOW_CAULDRON_BEHAVIOR, Oxidizable.OxidationLevel.UNAFFECTED, settings), AbstractBlock.Settings.copy(Blocks.POWDER_SNOW_CAULDRON), null);
    public static final Block WAXED_EXPOSED_POWDER_SNOW_CAULDRON = register("waxed_exposed_powder_snow_cauldron", (settings) -> new OxidizableLeveledCauldronBlock(Biome.Precipitation.SNOW, CauldronBehavior.POWDER_SNOW_CAULDRON_BEHAVIOR, Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.POWDER_SNOW_CAULDRON), null);
    public static final Block WAXED_WEATHERED_POWDER_SNOW_CAULDRON = register("waxed_weathered_powder_snow_cauldron", (settings) -> new OxidizableLeveledCauldronBlock(Biome.Precipitation.SNOW, CauldronBehavior.POWDER_SNOW_CAULDRON_BEHAVIOR, Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.POWDER_SNOW_CAULDRON), null);
    public static final Block WAXED_OXIDIZED_POWDER_SNOW_CAULDRON = register("waxed_oxidized_powder_snow_cauldron", (settings) -> new OxidizableLeveledCauldronBlock(Biome.Precipitation.SNOW, CauldronBehavior.POWDER_SNOW_CAULDRON_BEHAVIOR, Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.POWDER_SNOW_CAULDRON), null);

    // 铁质压力板系列
    public static final Block EXPOSED_HEAVY_WEIGHTED_PRESSURE_PLATE = register("exposed_heavy_weighted_pressure_plate", (settings) -> new OxidizableWeightedPressurePlateBlock(RustConfig.getExposed_WPPB(), BlockSetType.IRON, Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE), null);
    public static final Block WEATHERED_HEAVY_WEIGHTED_PRESSURE_PLATE = register("weathered_heavy_weighted_pressure_plate", (settings) -> new OxidizableWeightedPressurePlateBlock(RustConfig.getWeathered_WPPB(), BlockSetType.IRON, Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE), null);
    public static final Block OXIDIZED_HEAVY_WEIGHTED_PRESSURE_PLATE = register("oxidized_heavy_weighted_pressure_plate", (settings) -> new OxidizableWeightedPressurePlateBlock(RustConfig.getOxidized_WPPB(), BlockSetType.IRON, Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE), null);

    public static final Block WAXED_HEAVY_WEIGHTED_PRESSURE_PLATE = register("waxed_heavy_weighted_pressure_plate", (settings) -> new OxidizableWeightedPressurePlateBlock(RustConfig.getWaxed_WPPB(), BlockSetType.IRON, Oxidizable.OxidationLevel.UNAFFECTED, settings), AbstractBlock.Settings.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE), null);
    public static final Block WAXED_EXPOSED_HEAVY_WEIGHTED_PRESSURE_PLATE = register("waxed_exposed_heavy_weighted_pressure_plate", (settings) -> new OxidizableWeightedPressurePlateBlock(RustConfig.getWaxed_exposed_WPPB(), BlockSetType.IRON, Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE), null);
    public static final Block WAXED_WEATHERED_HEAVY_WEIGHTED_PRESSURE_PLATE = register("waxed_weathered_heavy_weighted_pressure_plate", (settings) -> new OxidizableWeightedPressurePlateBlock(RustConfig.getWaxed_weathered_WPPB(), BlockSetType.IRON, Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE), null);
    public static final Block WAXED_OXIDIZED_HEAVY_WEIGHTED_PRESSURE_PLATE = register("waxed_oxidized_heavy_weighted_pressure_plate", (settings) -> new OxidizableWeightedPressurePlateBlock(RustConfig.getWaxed_oxidized_WPPB(), BlockSetType.IRON, Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE), null);

    // 铁轨系列 -也不会 注册出来放不了矿车

    // 铁砧系列 -也不会 估计和炼药锅bug一样

    // 灯笼系列
    public static final Block EXPOSED_LANTERN = register("exposed_lantern", (settings) -> new OxidizableLanternBlock(Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.LANTERN), BlockRenderLayer.CUTOUT);
    public static final Block WEATHERED_LANTERN = register("weathered_lantern", (settings) -> new OxidizableLanternBlock(Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.LANTERN), BlockRenderLayer.CUTOUT);
    public static final Block OXIDIZED_LANTERN = register("oxidized_lantern", (settings) -> new OxidizableLanternBlock(Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.LANTERN), BlockRenderLayer.CUTOUT);

    public static final Block WAXED_LANTERN = register("waxed_lantern", (settings) -> new OxidizableLanternBlock(Oxidizable.OxidationLevel.UNAFFECTED, settings), AbstractBlock.Settings.copy(Blocks.LANTERN), BlockRenderLayer.CUTOUT);
    public static final Block WAXED_EXPOSED_LANTERN = register("waxed_exposed_lantern", (settings) -> new OxidizableLanternBlock(Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.LANTERN), BlockRenderLayer.CUTOUT);
    public static final Block WAXED_WEATHERED_LANTERN = register("waxed_weathered_lantern", (settings) -> new OxidizableLanternBlock(Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.LANTERN), BlockRenderLayer.CUTOUT);
    public static final Block WAXED_OXIDIZED_LANTERN = register("waxed_oxidized_lantern", (settings) -> new OxidizableLanternBlock(Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.LANTERN), BlockRenderLayer.CUTOUT);

    // 灵魂灯笼系列
    public static final Block EXPOSED_SOUL_LANTERN = register("exposed_soul_lantern", (settings) -> new OxidizableLanternBlock(Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.LANTERN), BlockRenderLayer.CUTOUT);
    public static final Block WEATHERED_SOUL_LANTERN = register("weathered_soul_lantern", (settings) -> new OxidizableLanternBlock(Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.LANTERN), BlockRenderLayer.CUTOUT);
    public static final Block OXIDIZED_SOUL_LANTERN = register("oxidized_soul_lantern", (settings) -> new OxidizableLanternBlock(Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.LANTERN), BlockRenderLayer.CUTOUT);

    public static final Block WAXED_SOUL_LANTERN = register("waxed_soul_lantern", (settings) -> new OxidizableLanternBlock(Oxidizable.OxidationLevel.UNAFFECTED, settings), AbstractBlock.Settings.copy(Blocks.SOUL_LANTERN), BlockRenderLayer.CUTOUT);
    public static final Block WAXED_EXPOSED_SOUL_LANTERN = register("waxed_exposed_soul_lantern", (settings) -> new OxidizableLanternBlock(Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.SOUL_LANTERN), BlockRenderLayer.CUTOUT);
    public static final Block WAXED_WEATHERED_SOUL_LANTERN = register("waxed_weathered_soul_lantern", (settings) -> new OxidizableLanternBlock(Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.SOUL_LANTERN), BlockRenderLayer.CUTOUT);
    public static final Block WAXED_OXIDIZED_SOUL_LANTERN = register("waxed_oxidized_soul_lantern", (settings) -> new OxidizableLanternBlock(Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.SOUL_LANTERN), BlockRenderLayer.CUTOUT);

    public static void initialize() {
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> BiomeColors.getWaterColor(world, pos), EXPOSED_WATER_CAULDRON);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> BiomeColors.getWaterColor(world, pos), WEATHERED_WATER_CAULDRON);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> BiomeColors.getWaterColor(world, pos), OXIDIZED_WATER_CAULDRON);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> BiomeColors.getWaterColor(world, pos), WAXED_WATER_CAULDRON);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> BiomeColors.getWaterColor(world, pos), WAXED_EXPOSED_WATER_CAULDRON);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> BiomeColors.getWaterColor(world, pos), WAXED_WEATHERED_WATER_CAULDRON);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> BiomeColors.getWaterColor(world, pos), WAXED_OXIDIZED_WATER_CAULDRON);
    }
}
