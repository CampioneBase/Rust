package com.adccadc.rust.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.CauldronBlock;
import net.minecraft.block.Oxidizable;
import net.minecraft.fluid.Fluid;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class OxidizableCauldronBlock extends CauldronBlock implements Oxidizable {
    //public static final MapCodec<OxidizableBlock> CODEC =
    private final OxidationLevel oxidationLevel;
    public static final MapCodec<CauldronBlock> CODEC = createCodec(CauldronBlock::new);
    private static final float FILL_WITH_RAIN_CHANCE = 0.05F;
    private static final float FILL_WITH_SNOW_CHANCE = 0.1F;
//CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR
    public OxidizableCauldronBlock(OxidationLevel oxidationLevel, Settings settings) {
        super(settings);
        this.oxidationLevel = oxidationLevel;
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.tickDegradation(state, world, pos, random);
    }

    @Override
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


    protected static boolean canFillWithPrecipitation(World world, Biome.Precipitation precipitation) {
        if (precipitation == Biome.Precipitation.RAIN) {
            return world.getRandom().nextFloat() < 0.05F;
        } else if (precipitation == Biome.Precipitation.SNOW) {
            return world.getRandom().nextFloat() < 0.1F;
        } else {
            return false;
        }
    }
/*
    @Override
    public void precipitationTick(BlockState state, World world, BlockPos pos, Biome.Precipitation precipitation) {
        if (canFillWithPrecipitation(world, precipitation)) {
            if (precipitation == Biome.Precipitation.RAIN) {
                world.setBlockState(pos, Modblocks.EXPOSED_WATER_CAULDRON.getDefaultState());
                world.emitGameEvent((Entity)null, GameEvent.BLOCK_CHANGE, pos);
            } else if (precipitation == Biome.Precipitation.SNOW) {
                world.setBlockState(pos, Blocks.POWDER_SNOW_CAULDRON.getDefaultState());
                world.emitGameEvent((Entity)null, GameEvent.BLOCK_CHANGE, pos);
            }

        }
    }
*/
    protected boolean canBeFilledByDripstone(Fluid fluid) {
        return true;
    }
/*
    @Override
    protected void fillFromDripstone(BlockState state, World world, BlockPos pos, Fluid fluid) {
        if (fluid == Fluids.WATER) {
            BlockState blockState = Modblocks.EXPOSED_WATER_CAULDRON.getDefaultState();
            world.setBlockState(pos, blockState);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
            world.syncWorldEvent(1047, pos, 0);
        } else if (fluid == Fluids.LAVA) {
            BlockState blockState = Blocks.LAVA_CAULDRON.getDefaultState();
            world.setBlockState(pos, blockState);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
            world.syncWorldEvent(1046, pos, 0);
        }

    }

*/
}
