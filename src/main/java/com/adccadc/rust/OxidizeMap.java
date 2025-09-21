package com.adccadc.rust;

import com.adccadc.rust.block.Modblocks;
import com.adccadc.rust.item.Moditems;
import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class OxidizeMap {
    // 方块氧化
    public static Supplier<BiMap<Block, Block>> MOD_OXIDATION_LEVEL_INCREASES = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(Blocks.COPPER_BLOCK, Blocks.EXPOSED_COPPER).put(Blocks.EXPOSED_COPPER, Blocks.WEATHERED_COPPER).put(Blocks.WEATHERED_COPPER, Blocks.OXIDIZED_COPPER)
            .put(Blocks.CUT_COPPER, Blocks.EXPOSED_CUT_COPPER).put(Blocks.EXPOSED_CUT_COPPER, Blocks.WEATHERED_CUT_COPPER).put(Blocks.WEATHERED_CUT_COPPER, Blocks.OXIDIZED_CUT_COPPER)
            .put(Blocks.CHISELED_COPPER, Blocks.EXPOSED_CHISELED_COPPER).put(Blocks.EXPOSED_CHISELED_COPPER, Blocks.WEATHERED_CHISELED_COPPER).put(Blocks.WEATHERED_CHISELED_COPPER, Blocks.OXIDIZED_CHISELED_COPPER)
            .put(Blocks.CUT_COPPER_SLAB, Blocks.EXPOSED_CUT_COPPER_SLAB).put(Blocks.EXPOSED_CUT_COPPER_SLAB, Blocks.WEATHERED_CUT_COPPER_SLAB).put(Blocks.WEATHERED_CUT_COPPER_SLAB, Blocks.OXIDIZED_CUT_COPPER_SLAB)
            .put(Blocks.CUT_COPPER_STAIRS, Blocks.EXPOSED_CUT_COPPER_STAIRS).put(Blocks.EXPOSED_CUT_COPPER_STAIRS, Blocks.WEATHERED_CUT_COPPER_STAIRS).put(Blocks.WEATHERED_CUT_COPPER_STAIRS, Blocks.OXIDIZED_CUT_COPPER_STAIRS)
            .put(Blocks.COPPER_DOOR, Blocks.EXPOSED_COPPER_DOOR).put(Blocks.EXPOSED_COPPER_DOOR, Blocks.WEATHERED_COPPER_DOOR).put(Blocks.WEATHERED_COPPER_DOOR, Blocks.OXIDIZED_COPPER_DOOR)
            .put(Blocks.COPPER_TRAPDOOR, Blocks.EXPOSED_COPPER_TRAPDOOR).put(Blocks.EXPOSED_COPPER_TRAPDOOR, Blocks.WEATHERED_COPPER_TRAPDOOR).put(Blocks.WEATHERED_COPPER_TRAPDOOR, Blocks.OXIDIZED_COPPER_TRAPDOOR)
            .put(Blocks.COPPER_GRATE, Blocks.EXPOSED_COPPER_GRATE).put(Blocks.EXPOSED_COPPER_GRATE, Blocks.WEATHERED_COPPER_GRATE).put(Blocks.WEATHERED_COPPER_GRATE, Blocks.OXIDIZED_COPPER_GRATE)
            .put(Blocks.COPPER_BULB, Blocks.EXPOSED_COPPER_BULB).put(Blocks.EXPOSED_COPPER_BULB, Blocks.WEATHERED_COPPER_BULB).put(Blocks.WEATHERED_COPPER_BULB, Blocks.OXIDIZED_COPPER_BULB)
            .put(Blocks.IRON_BLOCK, Modblocks.EXPOSED_IRON_BLOCK).put(Modblocks.EXPOSED_IRON_BLOCK, Modblocks.WEATHERED_IRON_BLOCK).put(Modblocks.WEATHERED_IRON_BLOCK, Modblocks.OXIDIZED_IRON_BLOCK)
            .put(Blocks.IRON_BARS, Modblocks.EXPOSED_IRON_BARS).put(Modblocks.EXPOSED_IRON_BARS, Modblocks.WEATHERED_IRON_BARS).put(Modblocks.WEATHERED_IRON_BARS, Modblocks.OXIDIZED_IRON_BARS)
            .put(Blocks.IRON_TRAPDOOR, Modblocks.EXPOSED_IRON_TRAPDOOR).put(Modblocks.EXPOSED_IRON_TRAPDOOR, Modblocks.WEATHERED_IRON_TRAPDOOR).put(Modblocks.WEATHERED_IRON_TRAPDOOR, Modblocks.OXIDIZED_IRON_TRAPDOOR)
            .put(Blocks.IRON_DOOR, Modblocks.EXPOSED_IRON_DOOR).put(Modblocks.EXPOSED_IRON_DOOR, Modblocks.WEATHERED_IRON_DOOR).put(Modblocks.WEATHERED_IRON_DOOR, Modblocks.OXIDIZED_IRON_DOOR)
            .put(Blocks.CHAIN, Modblocks.EXPOSED_IRON_CHAIN).put(Modblocks.EXPOSED_IRON_CHAIN, Modblocks.WEATHERED_IRON_CHAIN).put(Modblocks.WEATHERED_IRON_CHAIN, Modblocks.OXIDIZED_IRON_CHAIN)
            .put(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE, Modblocks.EXPOSED_HEAVY_WEIGHTED_PRESSURE_PLATE).put(Modblocks.EXPOSED_HEAVY_WEIGHTED_PRESSURE_PLATE, Modblocks.WEATHERED_HEAVY_WEIGHTED_PRESSURE_PLATE).put(Modblocks.WEATHERED_HEAVY_WEIGHTED_PRESSURE_PLATE, Modblocks.OXIDIZED_HEAVY_WEIGHTED_PRESSURE_PLATE)
            .put(Blocks.LANTERN, Modblocks.EXPOSED_LANTERN).put(Modblocks.EXPOSED_LANTERN, Modblocks.WEATHERED_LANTERN).put(Modblocks.WEATHERED_LANTERN, Modblocks.OXIDIZED_LANTERN)
            .put(Blocks.SOUL_LANTERN, Modblocks.EXPOSED_SOUL_LANTERN).put(Modblocks.EXPOSED_SOUL_LANTERN, Modblocks.WEATHERED_SOUL_LANTERN).put(Modblocks.WEATHERED_SOUL_LANTERN, Modblocks.OXIDIZED_SOUL_LANTERN)
            .put(Blocks.CAULDRON, Modblocks.EXPOSED_CAULDRON).put(Modblocks.EXPOSED_CAULDRON, Modblocks.WEATHERED_CAULDRON).put(Modblocks.WEATHERED_CAULDRON, Modblocks.OXIDIZED_CAULDRON)
            .put(Blocks.WATER_CAULDRON, Modblocks.EXPOSED_WATER_CAULDRON).put(Modblocks.EXPOSED_WATER_CAULDRON, Modblocks.WEATHERED_WATER_CAULDRON).put(Modblocks.WEATHERED_WATER_CAULDRON, Modblocks.OXIDIZED_WATER_CAULDRON)
            .put(Blocks.LAVA_CAULDRON, Modblocks.EXPOSED_LAVA_CAULDRON).put(Modblocks.EXPOSED_LAVA_CAULDRON, Modblocks.WEATHERED_LAVA_CAULDRON).put(Modblocks.WEATHERED_LAVA_CAULDRON, Modblocks.OXIDIZED_LAVA_CAULDRON)
            .put(Blocks.POWDER_SNOW_CAULDRON, Modblocks.EXPOSED_POWDER_SNOW_CAULDRON).put(Modblocks.EXPOSED_POWDER_SNOW_CAULDRON, Modblocks.WEATHERED_POWDER_SNOW_CAULDRON).put(Modblocks.WEATHERED_POWDER_SNOW_CAULDRON, Modblocks.OXIDIZED_POWDER_SNOW_CAULDRON)
            .build());

    // 铁类集合
    public static List<?> IRON_TYPE = new ArrayList<>(Arrays.asList(
            Blocks.IRON_BLOCK, Modblocks.EXPOSED_IRON_BLOCK, Modblocks.WEATHERED_IRON_BLOCK, Modblocks.OXIDIZED_IRON_BLOCK,
            Blocks.IRON_BARS, Modblocks.EXPOSED_IRON_BARS, Modblocks.WEATHERED_IRON_BARS, Modblocks.OXIDIZED_IRON_BARS,
            Blocks.IRON_TRAPDOOR, Modblocks.EXPOSED_IRON_TRAPDOOR, Modblocks.WEATHERED_IRON_TRAPDOOR, Modblocks.OXIDIZED_IRON_TRAPDOOR,
            Blocks.IRON_DOOR, Modblocks.EXPOSED_IRON_DOOR, Modblocks.WEATHERED_IRON_DOOR, Modblocks.OXIDIZED_IRON_DOOR,
            Blocks.CHAIN, Modblocks.EXPOSED_IRON_CHAIN, Modblocks.WEATHERED_IRON_CHAIN, Modblocks.OXIDIZED_IRON_CHAIN,
            Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE, Modblocks.EXPOSED_HEAVY_WEIGHTED_PRESSURE_PLATE, Modblocks.WEATHERED_HEAVY_WEIGHTED_PRESSURE_PLATE, Modblocks.OXIDIZED_HEAVY_WEIGHTED_PRESSURE_PLATE,
            Blocks.LANTERN, Modblocks.EXPOSED_LANTERN, Modblocks.WEATHERED_LANTERN, Modblocks.OXIDIZED_LANTERN,
            Blocks.SOUL_LANTERN, Modblocks.EXPOSED_SOUL_LANTERN, Modblocks.WEATHERED_SOUL_LANTERN, Modblocks.OXIDIZED_SOUL_LANTERN,
            Blocks.CAULDRON, Modblocks.EXPOSED_CAULDRON, Modblocks.WEATHERED_CAULDRON, Modblocks.OXIDIZED_CAULDRON,
            Blocks.WATER_CAULDRON, Modblocks.EXPOSED_WATER_CAULDRON, Modblocks.WEATHERED_WATER_CAULDRON, Modblocks.OXIDIZED_WATER_CAULDRON,
            Blocks.LAVA_CAULDRON, Modblocks.EXPOSED_LAVA_CAULDRON, Modblocks.WEATHERED_LAVA_CAULDRON, Modblocks.OXIDIZED_LAVA_CAULDRON,
            Blocks.POWDER_SNOW_CAULDRON, Modblocks.EXPOSED_POWDER_SNOW_CAULDRON, Modblocks.WEATHERED_POWDER_SNOW_CAULDRON, Modblocks.OXIDIZED_POWDER_SNOW_CAULDRON
            ));

    // 铜类集合
    public static List<?> COPPER_TYPE = new ArrayList<>(Arrays.asList(
            Blocks.COPPER_BLOCK, Blocks.EXPOSED_COPPER, Blocks.WEATHERED_COPPER, Blocks.OXIDIZED_COPPER,
            Blocks.CUT_COPPER, Blocks.EXPOSED_CUT_COPPER, Blocks.WEATHERED_CUT_COPPER, Blocks.OXIDIZED_CUT_COPPER,
            Blocks.CHISELED_COPPER, Blocks.EXPOSED_CHISELED_COPPER, Blocks.WEATHERED_CHISELED_COPPER, Blocks.OXIDIZED_CHISELED_COPPER,
            Blocks.CUT_COPPER_SLAB, Blocks.EXPOSED_CUT_COPPER_SLAB, Blocks.WEATHERED_CUT_COPPER_SLAB, Blocks.OXIDIZED_CUT_COPPER_SLAB,
            Blocks.CUT_COPPER_STAIRS, Blocks.EXPOSED_CUT_COPPER_STAIRS, Blocks.WEATHERED_CUT_COPPER_STAIRS, Blocks.OXIDIZED_CUT_COPPER_STAIRS,
            Blocks.COPPER_DOOR, Blocks.EXPOSED_COPPER_DOOR, Blocks.WEATHERED_COPPER_DOOR, Blocks.OXIDIZED_COPPER_DOOR,
            Blocks.COPPER_TRAPDOOR, Blocks.EXPOSED_COPPER_TRAPDOOR, Blocks.WEATHERED_COPPER_TRAPDOOR, Blocks.OXIDIZED_COPPER_TRAPDOOR,
            Blocks.COPPER_GRATE, Blocks.EXPOSED_COPPER_GRATE, Blocks.WEATHERED_COPPER_GRATE, Blocks.OXIDIZED_COPPER_GRATE,
            Blocks.COPPER_BULB, Blocks.EXPOSED_COPPER_BULB, Blocks.WEATHERED_COPPER_BULB, Blocks.OXIDIZED_COPPER_BULB
            ));

    // 方块除锈
    public static Supplier<BiMap<Block, Block>> MOD_OXIDATION_LEVEL_DECREASES = Suppliers.memoize(() -> ((BiMap)MOD_OXIDATION_LEVEL_INCREASES.get()).inverse());

    // 物品氧化
    public static Supplier<BiMap<Item, Item>> MOD_OXIDATION_LEVEL_INCREASES_ITEM = Suppliers.memoize(() -> ImmutableBiMap.<Item, Item>builder()
            .put(Items.IRON_AXE, Moditems.RUSTY_IRON_AXE)
            .put(Items.IRON_SWORD, Moditems.RUSTY_IRON_SWORD)
            .put(Items.IRON_SHOVEL, Moditems.RUSTY_IRON_SHOVEL)
            .put(Items.IRON_HOE, Moditems.RUSTY_IRON_HOE)
            .put(Items.IRON_PICKAXE, Moditems.RUSTY_IRON_PICKAXE)
            .put(Items.IRON_HELMET, Moditems.RUSTY_IRON_HELMET)
            .put(Items.IRON_CHESTPLATE, Moditems.RUSTY_IRON_CHESTPLATE)
            .put(Items.IRON_LEGGINGS, Moditems.RUSTY_IRON_LEGGINGS)
            .put(Items.IRON_BOOTS, Moditems.RUSTY_IRON_BOOTS)
            .build());

}
