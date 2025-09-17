package com.adccadc.rust.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.CollisionEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class OxidizableLavaCauldronBlock extends AbstractCauldronBlock implements Oxidizable {
    public static final MapCodec<LavaCauldronBlock> CODEC = createCodec(LavaCauldronBlock::new);
    private static final VoxelShape LAVA_SHAPE = Block.createColumnShape((double)12.0F, (double)4.0F, (double)15.0F);
    private static final VoxelShape INSIDE_COLLISION_SHAPE;
    private final OxidationLevel oxidationLevel;

    public MapCodec<LavaCauldronBlock> getCodec() {
        return CODEC;
    }

    public OxidizableLavaCauldronBlock(OxidationLevel oxidationLevel, AbstractBlock.Settings settings) {
        super(settings, CauldronBehavior.LAVA_CAULDRON_BEHAVIOR);
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

    protected double getFluidHeight(BlockState state) {
        return (double)0.9375F;
    }

    public boolean isFull(BlockState state) {
        return true;
    }

    protected VoxelShape getInsideCollisionShape(BlockState state, BlockView world, BlockPos pos, Entity entity) {
        return INSIDE_COLLISION_SHAPE;
    }

    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, EntityCollisionHandler handler) {
        handler.addEvent(CollisionEvent.LAVA_IGNITE);
        handler.addPostCallback(CollisionEvent.LAVA_IGNITE, Entity::setOnFireFromLava);
    }

    protected int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return 3;
    }

    static {
        INSIDE_COLLISION_SHAPE = VoxelShapes.union(AbstractCauldronBlock.OUTLINE_SHAPE, LAVA_SHAPE);
    }
}
