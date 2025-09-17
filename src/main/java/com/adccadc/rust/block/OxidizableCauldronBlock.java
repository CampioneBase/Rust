package com.adccadc.rust.block;

import com.adccadc.rust.OxidizeMap;
import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.event.GameEvent;

import java.util.function.Supplier;

public class OxidizableCauldronBlock extends CauldronBlock implements Oxidizable {
    private final OxidationLevel oxidationLevel;
    public static final MapCodec<CauldronBlock> CODEC = createCodec(CauldronBlock::new);
    private static final float FILL_WITH_RAIN_CHANCE = 0.05F;
    private static final float FILL_WITH_SNOW_CHANCE = 0.1F;

    public OxidizableCauldronBlock(OxidationLevel oxidationLevel, Settings settings) {
        super(settings);
        this.oxidationLevel = oxidationLevel;
    }

    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.tickDegradation(state, world, pos, random);
    }

    protected boolean hasRandomTicks(BlockState state) {
        return Oxidizable.getIncreasedOxidationBlock(state.getBlock()).isPresent();
    }

    public OxidationLevel getDegradationLevel() {
        return this.oxidationLevel;
    }

    public MapCodec<CauldronBlock> getCodec() {
        return CODEC;
    }

    public boolean isFull(BlockState state) {
        return false;
    }

    protected static BlockState whichCauldronBlock(BlockState state, String string) {
        BlockState blockState = null;
        if("water".equals(string)) {
            if (state == Modblocks.EXPOSED_CAULDRON.getDefaultState()) {
                blockState = Modblocks.EXPOSED_WATER_CAULDRON.getDefaultState();
            }
            if (state == Modblocks.WEATHERED_CAULDRON.getDefaultState()) {
                blockState = Modblocks.WEATHERED_WATER_CAULDRON.getDefaultState();
            }
            if (state == Modblocks.OXIDIZED_CAULDRON.getDefaultState()) {
                blockState = Modblocks.OXIDIZED_WATER_CAULDRON.getDefaultState();
            }
            if (state == Modblocks.WAXED_CAULDRON.getDefaultState()) {
                blockState = Modblocks.WAXED_WATER_CAULDRON.getDefaultState();
            }
            if (state == Modblocks.WAXED_EXPOSED_CAULDRON.getDefaultState()) {
                blockState = Modblocks.WAXED_EXPOSED_WATER_CAULDRON.getDefaultState();
            }
            if (state == Modblocks.WAXED_WEATHERED_CAULDRON.getDefaultState()) {
                blockState = Modblocks.WAXED_WEATHERED_WATER_CAULDRON.getDefaultState();
            }
            if (state == Modblocks.WAXED_OXIDIZED_CAULDRON.getDefaultState()) {
                blockState = Modblocks.WAXED_OXIDIZED_WATER_CAULDRON.getDefaultState();
            }
        } else if ("lava".equals(string)) {
            if (state == Modblocks.EXPOSED_CAULDRON.getDefaultState()) {
                blockState = Modblocks.EXPOSED_LAVA_CAULDRON.getDefaultState();
            }
            if (state == Modblocks.WEATHERED_CAULDRON.getDefaultState()) {
                blockState = Modblocks.WEATHERED_LAVA_CAULDRON.getDefaultState();
            }
            if (state == Modblocks.OXIDIZED_CAULDRON.getDefaultState()) {
                blockState = Modblocks.OXIDIZED_LAVA_CAULDRON.getDefaultState();
            }
            if (state == Modblocks.WAXED_CAULDRON.getDefaultState()) {
                blockState = Modblocks.WAXED_LAVA_CAULDRON.getDefaultState();
            }
            if (state == Modblocks.WAXED_EXPOSED_CAULDRON.getDefaultState()) {
                blockState = Modblocks.WAXED_EXPOSED_LAVA_CAULDRON.getDefaultState();
            }
            if (state == Modblocks.WAXED_WEATHERED_CAULDRON.getDefaultState()) {
                blockState = Modblocks.WAXED_WEATHERED_LAVA_CAULDRON.getDefaultState();
            }
            if (state == Modblocks.WAXED_OXIDIZED_CAULDRON.getDefaultState()) {
                blockState = Modblocks.WAXED_OXIDIZED_LAVA_CAULDRON.getDefaultState();
            }
        } else if ("snow".equals(string)) {
            if (state == Modblocks.EXPOSED_CAULDRON.getDefaultState()) {
                blockState = Modblocks.EXPOSED_POWDER_SNOW_CAULDRON.getDefaultState();
            }
            if (state == Modblocks.WEATHERED_CAULDRON.getDefaultState()) {
                blockState = Modblocks.WEATHERED_POWDER_SNOW_CAULDRON.getDefaultState();
            }
            if (state == Modblocks.OXIDIZED_CAULDRON.getDefaultState()) {
                blockState = Modblocks.OXIDIZED_POWDER_SNOW_CAULDRON.getDefaultState();
            }
            if (state == Modblocks.WAXED_CAULDRON.getDefaultState()) {
                blockState = Modblocks.WAXED_POWDER_SNOW_CAULDRON.getDefaultState();
            }
            if (state == Modblocks.WAXED_EXPOSED_CAULDRON.getDefaultState()) {
                blockState = Modblocks.WAXED_EXPOSED_POWDER_SNOW_CAULDRON.getDefaultState();
            }
            if (state == Modblocks.WAXED_WEATHERED_CAULDRON.getDefaultState()) {
                blockState = Modblocks.WAXED_WEATHERED_POWDER_SNOW_CAULDRON.getDefaultState();
            }
            if (state == Modblocks.WAXED_OXIDIZED_CAULDRON.getDefaultState()) {
                blockState = Modblocks.WAXED_OXIDIZED_POWDER_SNOW_CAULDRON.getDefaultState();
            }
        }
        return blockState;
    }

    protected static boolean canFillWithPrecipitation(World world, Biome.Precipitation precipitation) {
        if (precipitation == Biome.Precipitation.RAIN) {
            return world.getRandom().nextFloat() < 0.05F;
        } else if (precipitation == Biome.Precipitation.SNOW) {
            return world.getRandom().nextFloat() < 0.1F;
        } else {
            return false;
        }
    }

    public void precipitationTick(BlockState state, World world, BlockPos pos, Biome.Precipitation precipitation) {
        if (canFillWithPrecipitation(world, precipitation)) {
            if (precipitation == Biome.Precipitation.RAIN) {
                BlockState blockState = whichCauldronBlock(state, "water");
                world.setBlockState(pos, blockState);
                world.emitGameEvent((Entity)null, GameEvent.BLOCK_CHANGE, pos);
            } else if (precipitation == Biome.Precipitation.SNOW) {
                BlockState blockState = whichCauldronBlock(state, "snow");
                world.setBlockState(pos, blockState);
                world.emitGameEvent((Entity)null, GameEvent.BLOCK_CHANGE, pos);
            }

        }
    }

    protected boolean canBeFilledByDripstone(Fluid fluid) {
        return true;
    }

    protected void fillFromDripstone(BlockState state, World world, BlockPos pos, Fluid fluid) {
        if (fluid == Fluids.WATER) {
            BlockState blockState = whichCauldronBlock(state, "water");
            world.setBlockState(pos, blockState);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
            world.syncWorldEvent(1047, pos, 0);
        } else if (fluid == Fluids.LAVA) {
            BlockState blockState = whichCauldronBlock(state, "lava");
            world.setBlockState(pos, blockState);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
            world.syncWorldEvent(1046, pos, 0);
        }
    }

}
