package com.adccadc.rust.block;

import net.minecraft.block.*;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.CollisionEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.event.GameEvent;

public class OxidizableLeveledCauldronBlock extends LeveledCauldronBlock implements Oxidizable {
    //public static final MapCodec<OxidizableBlock> CODEC =
    private final OxidationLevel oxidationLevel;
    //public static final MapCodec<LeveledCauldronBlock> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(Biome.Precipitation.CODEC.fieldOf("precipitation").forGetter((block) -> block.precipitation), CauldronBehavior.CODEC.fieldOf("interactions").forGetter((block) -> block.behaviorMap), createSettingsCodec()).apply(instance, LeveledCauldronBlock::new));
    public static final int MIN_LEVEL = 1;
    public static final int MAX_LEVEL = 3;
    public static final IntProperty LEVEL;
    private static final int BASE_FLUID_HEIGHT = 6;
    private static final double FLUID_HEIGHT_PER_LEVEL = (double)3.0F;
    private static final VoxelShape[] INSIDE_COLLISION_SHAPE_BY_LEVEL;
    private final Biome.Precipitation precipitation;

    public OxidizableLeveledCauldronBlock(Biome.Precipitation precipitation, CauldronBehavior.CauldronBehaviorMap behaviorMap, OxidationLevel oxidationLevel, Settings settings) {
        super(precipitation, behaviorMap, settings);
        this.oxidationLevel = oxidationLevel;
        this.precipitation = precipitation;
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(LEVEL, 1));
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
/*
    public MapCodec<LeveledCauldronBlock> getCodec() {
        return CODEC;
    }
*/
    public boolean isFull(BlockState state) {
        return (Integer)state.get(LEVEL) == 3;
    }

    protected boolean canBeFilledByDripstone(Fluid fluid) {
        return fluid == Fluids.WATER && this.precipitation == Biome.Precipitation.RAIN;
    }

    protected double getFluidHeight(BlockState state) {
        return getFluidHeight((Integer)state.get(LEVEL)) / (double)16.0F;
    }

    private static double getFluidHeight(int level) {
        return (double)6.0F + (double)level * (double)3.0F;
    }

    protected VoxelShape getInsideCollisionShape(BlockState state, BlockView world, BlockPos pos, Entity entity) {
        return INSIDE_COLLISION_SHAPE_BY_LEVEL[(Integer)state.get(LEVEL) - 1];
    }

    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, EntityCollisionHandler handler) {
        if (world instanceof ServerWorld serverWorld) {
            BlockPos blockPos = pos.toImmutable();
            handler.addPreCallback(CollisionEvent.EXTINGUISH, (collidedEntity) -> {
                if (collidedEntity.isOnFire() && collidedEntity.canModifyAt(serverWorld, blockPos)) {
                    this.onFireCollision(state, world, blockPos);
                }

            });
        }

        handler.addEvent(CollisionEvent.EXTINGUISH);
    }

    private void onFireCollision(BlockState state, World world, BlockPos pos) {
        if (this.precipitation == Biome.Precipitation.SNOW) {
            decrementFluidLevel((BlockState)Blocks.WATER_CAULDRON.getDefaultState().with(LEVEL, (Integer)state.get(LEVEL)), world, pos);
        } else {
            decrementFluidLevel(state, world, pos);
        }

    }

    public static void decrementFluidLevel(BlockState state, World world, BlockPos pos) {
        int i = (Integer)state.get(LEVEL) - 1;
        BlockState blockState = i == 0 ? Blocks.CAULDRON.getDefaultState() : (BlockState)state.with(LEVEL, i);
        world.setBlockState(pos, blockState);
        world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
    }

    public void precipitationTick(BlockState state, World world, BlockPos pos, Biome.Precipitation precipitation) {
        if (OxidizableCauldronBlock.canFillWithPrecipitation(world, precipitation) && (Integer)state.get(LEVEL) != 3 && precipitation == this.precipitation) {
            BlockState blockState = (BlockState)state.cycle(LEVEL);
            world.setBlockState(pos, blockState);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
        }
    }

    protected int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return (Integer)state.get(LEVEL);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{LEVEL});
    }

    protected void fillFromDripstone(BlockState state, World world, BlockPos pos, Fluid fluid) {
        if (!this.isFull(state)) {
            BlockState blockState = (BlockState)state.with(LEVEL, (Integer)state.get(LEVEL) + 1);
            world.setBlockState(pos, blockState);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
            world.syncWorldEvent(1047, pos, 0);
        }
    }

    static {
        LEVEL = Properties.LEVEL_3;
        INSIDE_COLLISION_SHAPE_BY_LEVEL = (VoxelShape[]) Util.make(() -> Block.createShapeArray(2, (level) -> VoxelShapes.union(AbstractCauldronBlock.OUTLINE_SHAPE, Block.createColumnShape((double)12.0F, (double)4.0F, getFluidHeight(level + 1)))));
    }
}
