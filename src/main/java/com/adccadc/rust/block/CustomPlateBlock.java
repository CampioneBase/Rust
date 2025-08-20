package com.adccadc.rust.block;

import net.minecraft.block.BlockSetType;
import net.minecraft.block.WeightedPressurePlateBlock;

public class CustomPlateBlock extends WeightedPressurePlateBlock {
    public CustomPlateBlock(Settings settings) {
        super(150, BlockSetType.IRON, settings);
    }
}