package com.adccadc.rust;

import com.adccadc.rust.block.Modblocks;
import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import java.util.function.Supplier;

public class HoneyMap {
    // 涂蜡
    public static Supplier<BiMap<Block, Block>> MOD_WAXING_LEVEL_INCREASES = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(Blocks.IRON_BLOCK, Modblocks.WAXED_IRON_BLOCK).put(Modblocks.EXPOSED_IRON_BLOCK, Modblocks.WAXED_EXPOSED_IRON_BLOCK).put(Modblocks.WEATHERED_IRON_BLOCK, Modblocks.WAXED_WEATHERED_IRON_BLOCK).put(Modblocks.OXIDIZED_IRON_BLOCK, Modblocks.WAXED_OXIDIZED_IRON_BLOCK)
            .put(Blocks.IRON_BARS, Modblocks.WAXED_IRON_BARS).put(Modblocks.EXPOSED_IRON_BARS, Modblocks.WAXED_EXPOSED_IRON_BARS).put(Modblocks.WEATHERED_IRON_BARS, Modblocks.WAXED_WEATHERED_IRON_BARS).put(Modblocks.OXIDIZED_IRON_BARS, Modblocks.WAXED_OXIDIZED_IRON_BARS)
            .put(Blocks.IRON_TRAPDOOR, Modblocks.WAXED_IRON_TRAPDOOR).put(Modblocks.EXPOSED_IRON_TRAPDOOR, Modblocks.WAXED_EXPOSED_IRON_TRAPDOOR).put(Modblocks.WEATHERED_IRON_TRAPDOOR, Modblocks.WAXED_WEATHERED_IRON_TRAPDOOR).put(Modblocks.OXIDIZED_IRON_TRAPDOOR, Modblocks.WAXED_OXIDIZED_IRON_TRAPDOOR)
            .put(Blocks.IRON_DOOR, Modblocks.WAXED_IRON_DOOR).put(Modblocks.EXPOSED_IRON_DOOR, Modblocks.WAXED_EXPOSED_IRON_DOOR).put(Modblocks.WEATHERED_IRON_DOOR, Modblocks.WAXED_WEATHERED_IRON_DOOR).put(Modblocks.OXIDIZED_IRON_DOOR, Modblocks.WAXED_OXIDIZED_IRON_DOOR)
            .put(Blocks.CHAIN, Modblocks.WAXED_IRON_CHAIN).put(Modblocks.EXPOSED_IRON_CHAIN, Modblocks.WAXED_EXPOSED_IRON_CHAIN).put(Modblocks.WEATHERED_IRON_CHAIN, Modblocks.WAXED_WEATHERED_IRON_CHAIN).put(Modblocks.OXIDIZED_IRON_CHAIN, Modblocks.WAXED_OXIDIZED_IRON_CHAIN)
            .put(Blocks.CAULDRON, Modblocks.WAXED_CAULDRON).put(Modblocks.EXPOSED_CAULDRON, Modblocks.WAXED_EXPOSED_CAULDRON).put(Modblocks.WEATHERED_CAULDRON, Modblocks.WAXED_WEATHERED_CAULDRON).put(Modblocks.OXIDIZED_CAULDRON, Modblocks.WAXED_OXIDIZED_CAULDRON)
            .put(Blocks.WATER_CAULDRON, Modblocks.WAXED_WATER_CAULDRON).put(Modblocks.EXPOSED_WATER_CAULDRON, Modblocks.WAXED_EXPOSED_WATER_CAULDRON).put(Modblocks.WEATHERED_WATER_CAULDRON, Modblocks.WAXED_WEATHERED_WATER_CAULDRON).put(Modblocks.OXIDIZED_WATER_CAULDRON, Modblocks.WAXED_OXIDIZED_WATER_CAULDRON)
            .put(Blocks.LAVA_CAULDRON, Modblocks.WAXED_LAVA_CAULDRON).put(Modblocks.EXPOSED_LAVA_CAULDRON, Modblocks.WAXED_EXPOSED_LAVA_CAULDRON).put(Modblocks.WEATHERED_LAVA_CAULDRON, Modblocks.WAXED_WEATHERED_LAVA_CAULDRON).put(Modblocks.OXIDIZED_LAVA_CAULDRON, Modblocks.WAXED_OXIDIZED_LAVA_CAULDRON)
            .put(Blocks.POWDER_SNOW_CAULDRON, Modblocks.WAXED_POWDER_SNOW_CAULDRON).put(Modblocks.EXPOSED_POWDER_SNOW_CAULDRON, Modblocks.WAXED_EXPOSED_POWDER_SNOW_CAULDRON).put(Modblocks.WEATHERED_POWDER_SNOW_CAULDRON, Modblocks.WAXED_WEATHERED_POWDER_SNOW_CAULDRON).put(Modblocks.OXIDIZED_POWDER_SNOW_CAULDRON, Modblocks.WAXED_OXIDIZED_POWDER_SNOW_CAULDRON)
            .put(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE, Modblocks.WAXED_HEAVY_WEIGHTED_PRESSURE_PLATE).put(Modblocks.EXPOSED_HEAVY_WEIGHTED_PRESSURE_PLATE, Modblocks.WAXED_EXPOSED_HEAVY_WEIGHTED_PRESSURE_PLATE).put(Modblocks.WEATHERED_HEAVY_WEIGHTED_PRESSURE_PLATE, Modblocks.WAXED_WEATHERED_HEAVY_WEIGHTED_PRESSURE_PLATE).put(Modblocks.OXIDIZED_HEAVY_WEIGHTED_PRESSURE_PLATE, Modblocks.WAXED_OXIDIZED_HEAVY_WEIGHTED_PRESSURE_PLATE)
            .put(Blocks.LANTERN, Modblocks.WAXED_LANTERN).put(Modblocks.EXPOSED_LANTERN, Modblocks.WAXED_EXPOSED_LANTERN).put(Modblocks.WEATHERED_LANTERN, Modblocks.WAXED_WEATHERED_LANTERN).put(Modblocks.OXIDIZED_LANTERN, Modblocks.WAXED_OXIDIZED_LANTERN)
            .put(Blocks.SOUL_LANTERN, Modblocks.WAXED_SOUL_LANTERN).put(Modblocks.EXPOSED_SOUL_LANTERN, Modblocks.WAXED_EXPOSED_SOUL_LANTERN).put(Modblocks.WEATHERED_SOUL_LANTERN, Modblocks.WAXED_WEATHERED_SOUL_LANTERN).put(Modblocks.OXIDIZED_SOUL_LANTERN, Modblocks.WAXED_OXIDIZED_SOUL_LANTERN)
            .build());

    // 除蜡
    public static Supplier<BiMap<Block, Block>> MOD_WAXING_LEVEL_DECREASES = Suppliers.memoize(() -> ((BiMap)MOD_WAXING_LEVEL_INCREASES.get()).inverse());

}
