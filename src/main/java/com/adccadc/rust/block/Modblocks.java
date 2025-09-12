package com.adccadc.rust.block;

import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.block.*;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class Modblocks {
    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem) {

        RegistryKey<Block> blockKey = keyOfBlock(name);

        Block block = blockFactory.apply(settings.registryKey(blockKey));

        if (shouldRegisterItem) {
            RegistryKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of("rust", name));
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of("rust", name));
    }

    // 铁块系列
    public static final Block EXPOSED_IRON_BLOCK = register("exposed_iron_block", (settings) -> new OxidizableBlock(Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.IRON_BLOCK), true);
    public static final Block WEATHERED_IRON_BLOCK = register("weathered_iron_block", (settings) -> new OxidizableBlock(Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.IRON_BLOCK), true);
    public static final Block OXIDIZED_IRON_BLOCK = register("oxidized_iron_block", (settings) -> new OxidizableBlock(Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.IRON_BLOCK), true);

    public static final Block WAXED_IRON_BLOCK = register("waxed_iron_block", Block::new, AbstractBlock.Settings.copy(Blocks.IRON_BLOCK), true);
    public static final Block WAXED_EXPOSED_IRON_BLOCK = register("waxed_exposed_iron_block", Block::new, AbstractBlock.Settings.copy(Blocks.IRON_BLOCK), true);
    public static final Block WAXED_WEATHERED_IRON_BLOCK = register("waxed_weathered_iron_block", Block::new, AbstractBlock.Settings.copy(Blocks.IRON_BLOCK), true);
    public static final Block WAXED_OXIDIZED_IRON_BLOCK = register("waxed_oxidized_iron_block", Block::new, AbstractBlock.Settings.copy(Blocks.IRON_BLOCK), true);

    // 铁栏杆系列
    public static final Block EXPOSED_IRON_BARS = register("exposed_iron_bars", (settings) -> new OxidizablePaneBlock(Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.IRON_BARS), true);
    public static final Block WEATHERED_IRON_BARS = register("weathered_iron_bars", (settings) -> new OxidizablePaneBlock(Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.IRON_BARS), true);
    public static final Block OXIDIZED_IRON_BARS = register("oxidized_iron_bars", (settings) -> new OxidizablePaneBlock(Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.IRON_BARS), true);

    public static final Block WAXED_IRON_BARS = register("waxed_iron_bars", PaneBlock::new, AbstractBlock.Settings.copy(Blocks.IRON_BARS), true);
    public static final Block WAXED_EXPOSED_IRON_BARS = register("waxed_exposed_iron_bars", PaneBlock::new, AbstractBlock.Settings.copy(Blocks.IRON_BARS), true);
    public static final Block WAXED_WEATHERED_IRON_BARS = register("waxed_weathered_iron_bars", PaneBlock::new, AbstractBlock.Settings.copy(Blocks.IRON_BARS), true);
    public static final Block WAXED_OXIDIZED_IRON_BARS = register("waxed_oxidized_iron_bars", PaneBlock::new, AbstractBlock.Settings.copy(Blocks.IRON_BARS), true);

    // 铁活板门系列
    public static final Block EXPOSED_IRON_TRAPDOOR = register("exposed_iron_trapdoor", (settings) -> new OxidizableTrapdoorBlock(BlockSetType.IRON, Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.IRON_TRAPDOOR), true);
    public static final Block WEATHERED_IRON_TRAPDOOR = register("weathered_iron_trapdoor", (settings) -> new OxidizableTrapdoorBlock(BlockSetType.IRON, Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.IRON_TRAPDOOR), true);
    public static final Block OXIDIZED_IRON_TRAPDOOR = register("oxidized_iron_trapdoor", (settings) -> new OxidizableTrapdoorBlock(BlockSetType.IRON, Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.IRON_TRAPDOOR), true);

    public static final Block WAXED_IRON_TRAPDOOR = register("waxed_iron_trapdoor", (settings) -> new TrapdoorBlock(BlockSetType.IRON, settings), AbstractBlock.Settings.copy(Blocks.IRON_TRAPDOOR), true);
    public static final Block WAXED_EXPOSED_IRON_TRAPDOOR = register("waxed_exposed_iron_trapdoor", (settings) -> new TrapdoorBlock(BlockSetType.IRON, settings), AbstractBlock.Settings.copy(Blocks.IRON_TRAPDOOR), true);
    public static final Block WAXED_WEATHERED_IRON_TRAPDOOR = register("waxed_weathered_iron_trapdoor", (settings) -> new TrapdoorBlock(BlockSetType.IRON, settings), AbstractBlock.Settings.copy(Blocks.IRON_TRAPDOOR), true);
    public static final Block WAXED_OXIDIZED_IRON_TRAPDOOR = register("waxed_oxidized_iron_trapdoor", (settings) -> new TrapdoorBlock(BlockSetType.IRON, settings), AbstractBlock.Settings.copy(Blocks.IRON_TRAPDOOR), true);

    // 铁门系列
    public static final Block EXPOSED_IRON_DOOR = register("exposed_iron_door", (settings) -> new OxidizableDoorBlock(BlockSetType.IRON, Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.IRON_DOOR), true);
    public static final Block WEATHERED_IRON_DOOR = register("weathered_iron_door", (settings) -> new OxidizableDoorBlock(BlockSetType.IRON, Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.IRON_DOOR), true);
    public static final Block OXIDIZED_IRON_DOOR = register("oxidized_iron_door", (settings) -> new OxidizableDoorBlock(BlockSetType.IRON, Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.IRON_DOOR), true);

    public static final Block WAXED_IRON_DOOR = register("waxed_iron_door", (settings) -> new DoorBlock(BlockSetType.IRON, settings), AbstractBlock.Settings.copy(Blocks.IRON_DOOR), true);
    public static final Block WAXED_EXPOSED_IRON_DOOR = register("waxed_exposed_iron_door", (settings) -> new DoorBlock(BlockSetType.IRON, settings), AbstractBlock.Settings.copy(Blocks.IRON_DOOR), true);
    public static final Block WAXED_WEATHERED_IRON_DOOR = register("waxed_weathered_iron_door", (settings) -> new DoorBlock(BlockSetType.IRON, settings), AbstractBlock.Settings.copy(Blocks.IRON_DOOR), true);
    public static final Block WAXED_OXIDIZED_IRON_DOOR = register("waxed_oxidized_iron_door", (settings) -> new DoorBlock(BlockSetType.IRON, settings), AbstractBlock.Settings.copy(Blocks.IRON_DOOR), true);

    // 锁链系列
    public static final Block EXPOSED_IRON_CHAIN = register("exposed_iron_chain", (settings) -> new OxidizableChainBlock(Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.CHAIN), true);
    public static final Block WEATHERED_IRON_CHAIN = register("weathered_iron_chain", (settings) -> new OxidizableChainBlock(Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.CHAIN), true);
    public static final Block OXIDIZED_IRON_CHAIN = register("oxidized_iron_chain", (settings) -> new OxidizableChainBlock(Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.CHAIN), true);

    public static final Block WAXED_IRON_CHAIN = register("waxed_iron_chain", ChainBlock::new, AbstractBlock.Settings.copy(Blocks.CHAIN), true);
    public static final Block WAXED_EXPOSED_IRON_CHAIN = register("waxed_exposed_iron_chain", ChainBlock::new, AbstractBlock.Settings.copy(Blocks.CHAIN), true);
    public static final Block WAXED_WEATHERED_IRON_CHAIN = register("waxed_weathered_iron_chain", ChainBlock::new, AbstractBlock.Settings.copy(Blocks.CHAIN), true);
    public static final Block WAXED_OXIDIZED_IRON_CHAIN = register("waxed_oxidized_iron_chain", ChainBlock::new, AbstractBlock.Settings.copy(Blocks.CHAIN), true);

    // 漏斗系列 -能力不足 放弃实现

    // 活塞系列 -材质改和没改差不多 不打算实现

    // 炼药锅系列 -有手动或下雨等方式使炼药锅状态改变会变回原版炼药锅等bug 弃用
    public static final Block EXPOSED_CAULDRON = register("exposed_cauldron", CauldronBlock::new, AbstractBlock.Settings.copy(Blocks.CAULDRON), true);
    public static final Block WEATHERED_CAULDRON = register("weathered_cauldron", CauldronBlock::new, AbstractBlock.Settings.copy(Blocks.CAULDRON), true);
    public static final Block OXIDIZED_CAULDRON = register("oxidized_cauldron", CauldronBlock::new, AbstractBlock.Settings.copy(Blocks.CAULDRON), true);

    public static final Block WAXED_CAULDRON = register("waxed_cauldron", CauldronBlock::new, AbstractBlock.Settings.copy(Blocks.CAULDRON), true);
    public static final Block WAXED_EXPOSED_CAULDRON = register("waxed_exposed_cauldron", CauldronBlock::new, AbstractBlock.Settings.copy(Blocks.CAULDRON), true);
    public static final Block WAXED_WEATHERED_CAULDRON = register("waxed_weathered_cauldron", CauldronBlock::new, AbstractBlock.Settings.copy(Blocks.CAULDRON), true);
    public static final Block WAXED_OXIDIZED_CAULDRON = register("waxed_oxidized_cauldron", CauldronBlock::new, AbstractBlock.Settings.copy(Blocks.CAULDRON), true);

    /*// 含水炼药锅系列 -能力不足 放弃实现
    public static final Block EXPOSED_WATER_CAULDRON = register("exposed_water_cauldron", CustomWaterCauldronBlock::new, AbstractBlock.Settings.copy(Blocks.WATER_CAULDRON), true);
    public static final Block WEATHERED_WATER_CAULDRON = register("weathered_water_cauldron", CustomWaterCauldronBlock::new, AbstractBlock.Settings.copy(Blocks.WATER_CAULDRON), true);
    public static final Block OXIDIZED_WATER_CAULDRON = register("oxidized_water_cauldron", CustomWaterCauldronBlock::new, AbstractBlock.Settings.copy(Blocks.WATER_CAULDRON), true);

    public static final Block WAXED_WATER_CAULDRON = register("waxed_water_cauldron", CustomWaterCauldronBlock::new, AbstractBlock.Settings.copy(Blocks.WATER_CAULDRON), true);
    public static final Block WAXED_WATER_EXPOSED_CAULDRON = register("waxed_exposed_water_cauldron", CustomWaterCauldronBlock::new, AbstractBlock.Settings.copy(Blocks.WATER_CAULDRON), true);
    public static final Block WAXED_WATER_WEATHERED_CAULDRON = register("waxed_weathered_water_cauldron", CustomWaterCauldronBlock::new, AbstractBlock.Settings.copy(Blocks.WATER_CAULDRON), true);
    public static final Block WAXED_WATER_OXIDIZED_CAULDRON = register("waxed_oxidized_water_cauldron", CustomWaterCauldronBlock::new, AbstractBlock.Settings.copy(Blocks.WATER_CAULDRON), true);*/

    // 铁质压力板系列
    public static final Block EXPOSED_HEAVY_WEIGHTED_PRESSURE_PLATE = register("exposed_heavy_weighted_pressure_plate", (settings) -> new OxidizableWeightedPressurePlateBlock(150, BlockSetType.IRON, Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE), true);
    public static final Block WEATHERED_HEAVY_WEIGHTED_PRESSURE_PLATE = register("weathered_heavy_weighted_pressure_plate", (settings) -> new OxidizableWeightedPressurePlateBlock(150, BlockSetType.IRON, Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE), true);
    public static final Block OXIDIZED_HEAVY_WEIGHTED_PRESSURE_PLATE = register("oxidized_heavy_weighted_pressure_plate", (settings) -> new OxidizableWeightedPressurePlateBlock(150, BlockSetType.IRON, Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE), true);

    public static final Block WAXED_HEAVY_WEIGHTED_PRESSURE_PLATE = register("waxed_heavy_weighted_pressure_plate", (settings) -> new WeightedPressurePlateBlock(150, BlockSetType.IRON, settings), AbstractBlock.Settings.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE), true);
    public static final Block WAXED_EXPOSED_HEAVY_WEIGHTED_PRESSURE_PLATE = register("waxed_exposed_heavy_weighted_pressure_plate", (settings) -> new WeightedPressurePlateBlock(150, BlockSetType.IRON, settings), AbstractBlock.Settings.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE), true);
    public static final Block WAXED_WEATHERED_HEAVY_WEIGHTED_PRESSURE_PLATE = register("waxed_weathered_heavy_weighted_pressure_plate", (settings) -> new WeightedPressurePlateBlock(150, BlockSetType.IRON, settings), AbstractBlock.Settings.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE), true);
    public static final Block WAXED_OXIDIZED_HEAVY_WEIGHTED_PRESSURE_PLATE = register("waxed_oxidized_heavy_weighted_pressure_plate", (settings) -> new WeightedPressurePlateBlock(150, BlockSetType.IRON, settings), AbstractBlock.Settings.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE), true);

    // 铁轨系列 -也不会 注册出来放不了矿车

    // 铁砧系列 -也不会 估计和炼药锅bug一样

    // 灯笼系列
    public static final Block EXPOSED_LANTERN = register("exposed_lantern", (settings) -> new OxidizableLanternBlock(Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.LANTERN), true);
    public static final Block WEATHERED_LANTERN = register("weathered_lantern", (settings) -> new OxidizableLanternBlock(Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.LANTERN), true);
    public static final Block OXIDIZED_LANTERN = register("oxidized_lantern", (settings) -> new OxidizableLanternBlock(Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.LANTERN), true);

    public static final Block WAXED_LANTERN = register("waxed_lantern", LanternBlock::new, AbstractBlock.Settings.copy(Blocks.LANTERN), true);
    public static final Block WAXED_EXPOSED_LANTERN = register("waxed_exposed_lantern", LanternBlock::new, AbstractBlock.Settings.copy(Blocks.LANTERN), true);
    public static final Block WAXED_WEATHERED_LANTERN = register("waxed_weathered_lantern", LanternBlock::new, AbstractBlock.Settings.copy(Blocks.LANTERN), true);
    public static final Block WAXED_OXIDIZED_LANTERN = register("waxed_oxidized_lantern", LanternBlock::new, AbstractBlock.Settings.copy(Blocks.LANTERN), true);

    // 灵魂灯笼系列
    public static final Block EXPOSED_SOUL_LANTERN = register("exposed_soul_lantern", (settings) -> new OxidizableLanternBlock(Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.LANTERN), true);
    public static final Block WEATHERED_SOUL_LANTERN = register("weathered_soul_lantern", (settings) -> new OxidizableLanternBlock(Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.LANTERN), true);
    public static final Block OXIDIZED_SOUL_LANTERN = register("oxidized_soul_lantern", (settings) -> new OxidizableLanternBlock(Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.LANTERN), true);

    public static final Block WAXED_SOUL_LANTERN = register("waxed_soul_lantern", LanternBlock::new, AbstractBlock.Settings.copy(Blocks.SOUL_LANTERN), true);
    public static final Block WAXED_EXPOSED_SOUL_LANTERN = register("waxed_exposed_soul_lantern", LanternBlock::new, AbstractBlock.Settings.copy(Blocks.SOUL_LANTERN), true);
    public static final Block WAXED_WEATHERED_SOUL_LANTERN = register("waxed_weathered_soul_lantern", LanternBlock::new, AbstractBlock.Settings.copy(Blocks.SOUL_LANTERN), true);
    public static final Block WAXED_OXIDIZED_SOUL_LANTERN = register("waxed_oxidized_soul_lantern", LanternBlock::new, AbstractBlock.Settings.copy(Blocks.SOUL_LANTERN), true);

    public static void initialize() {
        BlockRenderLayerMap.putBlock(Modblocks.EXPOSED_IRON_BARS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.WEATHERED_IRON_BARS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.OXIDIZED_IRON_BARS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.WAXED_IRON_BARS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.WAXED_EXPOSED_IRON_BARS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.WAXED_WEATHERED_IRON_BARS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.WAXED_OXIDIZED_IRON_BARS, BlockRenderLayer.CUTOUT);

        BlockRenderLayerMap.putBlock(Modblocks.EXPOSED_IRON_TRAPDOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.WEATHERED_IRON_TRAPDOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.OXIDIZED_IRON_TRAPDOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.WAXED_IRON_TRAPDOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.WAXED_EXPOSED_IRON_TRAPDOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.WAXED_WEATHERED_IRON_TRAPDOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.WAXED_OXIDIZED_IRON_TRAPDOOR, BlockRenderLayer.CUTOUT);

        BlockRenderLayerMap.putBlock(Modblocks.EXPOSED_IRON_CHAIN, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.WEATHERED_IRON_CHAIN, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.OXIDIZED_IRON_CHAIN, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.WAXED_IRON_CHAIN, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.WAXED_EXPOSED_IRON_CHAIN, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.WAXED_WEATHERED_IRON_CHAIN, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.WAXED_OXIDIZED_IRON_CHAIN, BlockRenderLayer.CUTOUT);

        BlockRenderLayerMap.putBlock(Modblocks.EXPOSED_IRON_DOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.WEATHERED_IRON_DOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.OXIDIZED_IRON_DOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.WAXED_IRON_DOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.WAXED_EXPOSED_IRON_DOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.WAXED_WEATHERED_IRON_DOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.WAXED_OXIDIZED_IRON_DOOR, BlockRenderLayer.CUTOUT);

        BlockRenderLayerMap.putBlock(Modblocks.EXPOSED_LANTERN, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.WEATHERED_LANTERN, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.OXIDIZED_LANTERN, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.WAXED_LANTERN, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.WAXED_EXPOSED_LANTERN, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.WAXED_WEATHERED_LANTERN, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.WAXED_OXIDIZED_LANTERN, BlockRenderLayer.CUTOUT);

        BlockRenderLayerMap.putBlock(Modblocks.EXPOSED_SOUL_LANTERN, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.WEATHERED_SOUL_LANTERN, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.OXIDIZED_SOUL_LANTERN, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.WAXED_SOUL_LANTERN, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.WAXED_EXPOSED_SOUL_LANTERN, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.WAXED_WEATHERED_SOUL_LANTERN, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Modblocks.WAXED_OXIDIZED_SOUL_LANTERN, BlockRenderLayer.CUTOUT);
    }
}
