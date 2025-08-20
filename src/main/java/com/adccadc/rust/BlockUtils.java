package com.adccadc.rust;

import net.minecraft.block.*;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.DoorHinge;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.block.enums.RailShape;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class BlockUtils {

    // 继承PaneBlock属性 各种玻璃板，铁栅栏
    public static void PutPaneBlockWithAttribute(World world, Block block, BlockState state, BlockPos pos) {
        Boolean east = state.get(PaneBlock.EAST);
        Boolean north = state.get(PaneBlock.NORTH);
        Boolean west = state.get(PaneBlock.WEST);
        Boolean south = state.get(PaneBlock.SOUTH);
        Boolean waterlogged = state.get(PaneBlock.WATERLOGGED);

        world.setBlockState(pos, block.getDefaultState()
                .with(PaneBlock.EAST, east).with(PaneBlock.NORTH, north)
                .with(PaneBlock.WEST, west).with(PaneBlock.SOUTH, south)
                .with(PaneBlock.WATERLOGGED, waterlogged), 0);
    }

    // 继承DoorBlock属性 各种门
    public static void PutDoorBlockWithAttribute(World world, Block block, BlockState state, BlockPos pos) {
        Direction facing = state.get(DoorBlock.FACING);
        DoubleBlockHalf half = state.get(DoorBlock.HALF);
        DoorHinge hinge = state.get(DoorBlock.HINGE);
        Boolean open = state.get(DoorBlock.OPEN);
        Boolean powered = state.get(DoorBlock.POWERED);

        world.setBlockState(pos, block.getDefaultState()
                .with(DoorBlock.FACING, facing)
                .with(DoorBlock.HALF, half)
                .with(DoorBlock.HINGE, hinge)
                .with(DoorBlock.OPEN, open)
                .with(DoorBlock.POWERED, powered), 0);

        if (half == DoubleBlockHalf.LOWER) {
            BlockPos topPos = pos.up();
            BlockState topState = world.getBlockState(topPos);

            Direction topFacing = topState.get(DoorBlock.FACING);
            DoubleBlockHalf topHalf = topState.get(DoorBlock.HALF);
            DoorHinge topHinge = topState.get(DoorBlock.HINGE);
            Boolean topOpen = topState.get(DoorBlock.OPEN);
            Boolean topPowered = topState.get(DoorBlock.POWERED);

            world.setBlockState(topPos, block.getDefaultState()
                    .with(DoorBlock.FACING, topFacing)
                    .with(DoorBlock.HALF, topHalf)
                    .with(DoorBlock.HINGE, topHinge)
                    .with(DoorBlock.OPEN, topOpen)
                    .with(DoorBlock.POWERED, topPowered), 0);
            world.updateListeners(topPos, topState, block.getDefaultState(), 0);
        } else {
            BlockPos bottomPos = pos.down();
            BlockState bottomState = world.getBlockState(bottomPos);

            Direction bottomFacing = bottomState.get(DoorBlock.FACING);
            DoubleBlockHalf bottomHalf = bottomState.get(DoorBlock.HALF);
            DoorHinge bottomHinge = bottomState.get(DoorBlock.HINGE);
            Boolean bottomOpen = bottomState.get(DoorBlock.OPEN);
            Boolean bottomPowered = bottomState.get(DoorBlock.POWERED);

            world.setBlockState(bottomPos, block.getDefaultState()
                    .with(DoorBlock.FACING, bottomFacing)
                    .with(DoorBlock.HALF, bottomHalf)
                    .with(DoorBlock.HINGE, bottomHinge)
                    .with(DoorBlock.OPEN, bottomOpen)
                    .with(DoorBlock.POWERED, bottomPowered), 0);
            world.updateListeners(bottomPos, bottomState, block.getDefaultState(), 0);
        }
    }

    // 继承TrapdoorBlock属性 各种活板门
    public static void PutTrapdoorBlockWithAttribute(World world, Block block, BlockState state, BlockPos pos) {
        Direction facing = state.get(TrapdoorBlock.FACING);
        BlockHalf half = state.get(TrapdoorBlock.HALF);
        Boolean open = state.get(TrapdoorBlock.OPEN);
        Boolean powered = state.get(TrapdoorBlock.POWERED);
        Boolean waterlogged = state.get(TrapdoorBlock.WATERLOGGED);

        world.setBlockState(pos, block.getDefaultState()
                .with(TrapdoorBlock.FACING, facing)
                .with(TrapdoorBlock.HALF, half)
                .with(TrapdoorBlock.OPEN, open)
                .with(TrapdoorBlock.POWERED, powered)
                .with(TrapdoorBlock.WATERLOGGED, waterlogged), 0);
    }

    // 继承ChainBlock属性 各种锁链
    public static void PutChainBlockWithAttribute(World world, Block block, BlockState state, BlockPos pos) {
        Direction.Axis axis = state.get(ChainBlock.AXIS);
        Boolean waterlogged = state.get(ChainBlock.WATERLOGGED);

        world.setBlockState(pos, block.getDefaultState()
                .with(ChainBlock.AXIS, axis)
                .with(ChainBlock.WATERLOGGED, waterlogged), 0);
    }

    // 继承PlateBlock属性 各种压力板
    public static void PutPlateBlockWithAttribute(World world, Block block, BlockState state, BlockPos pos) {
        Integer power = state.get(WeightedPressurePlateBlock.POWER);

        world.setBlockState(pos, block.getDefaultState()
                .with(WeightedPressurePlateBlock.POWER, power), 0);
    }

    // 继承LeveledCauldronBlock属性 各种含内容炼药锅
    public static void PutLeveledCauldronBlockWithAttribute(World world, Block block, BlockState state, BlockPos pos) {
        Integer level = state.get(LeveledCauldronBlock.LEVEL);

        world.setBlockState(pos, block.getDefaultState()
                .with(LeveledCauldronBlock.LEVEL, level), 0);
    }

    // 继承RailBlock属性 各种铁轨
    public static void PutRailBlockWithAttribute(World world, Block block, BlockState state, BlockPos pos) {
        RailShape shape = state.get(RailBlock.SHAPE);
        Boolean waterlogged = state.get(RailBlock.WATERLOGGED);

        world.setBlockState(pos, block.getDefaultState()
                .with(RailBlock.SHAPE, shape)
                .with(RailBlock.WATERLOGGED, waterlogged), 0);
    }

    // 继承LanternBlock属性 各种铁轨
    public static void PutLanternBlockWithAttribute(World world, Block block, BlockState state, BlockPos pos) {
        Boolean hanging = state.get(LanternBlock.HANGING);
        Boolean waterlogged = state.get(LanternBlock.WATERLOGGED);

        world.setBlockState(pos, block.getDefaultState()
                .with(LanternBlock.HANGING, hanging)
                .with(LanternBlock.WATERLOGGED, waterlogged), 0);
    }

    // 挑选符合的类型继承属性
    public static void PutWhichBlockWithAttribute(World world, Block block, BlockState state, BlockPos pos) {
        switch (block) {
            case PaneBlock paneBlock -> PutPaneBlockWithAttribute(world, block, state, pos);
            case DoorBlock doorBlock -> PutDoorBlockWithAttribute(world, block, state, pos);
            case TrapdoorBlock trapdoorBlock -> PutTrapdoorBlockWithAttribute(world, block, state, pos);
            case ChainBlock chainBlock -> PutChainBlockWithAttribute(world, block, state, pos);
            case WeightedPressurePlateBlock weightedPressurePlateBlock -> PutPlateBlockWithAttribute(world, block, state, pos);
            case LeveledCauldronBlock leveledCauldronBlock -> PutLeveledCauldronBlockWithAttribute(world, block, state, pos);
            case RailBlock railBlock -> PutRailBlockWithAttribute(world, block, state, pos);
            case LanternBlock lanternBlock -> PutLanternBlockWithAttribute(world, block, state, pos);
            default -> world.setBlockState(pos, block.getDefaultState(), 0);
        }
    }

}
