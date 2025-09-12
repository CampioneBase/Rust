package com.adccadc.rust.block;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class OxidizableWeightedPressurePlateBlock extends WeightedPressurePlateBlock implements Oxidizable {
    //public static final MapCodec<OxidizableBlock> CODEC =
    private final OxidationLevel oxidationLevel;

    public OxidizableWeightedPressurePlateBlock(Integer weight, BlockSetType type, OxidationLevel oxidationLevel, Settings settings) {
        super(weight, type, settings);
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
}
