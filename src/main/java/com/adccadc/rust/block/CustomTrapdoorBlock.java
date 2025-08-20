package com.adccadc.rust.block;

import net.minecraft.block.BlockSetType;
import net.minecraft.block.TrapdoorBlock;

public class CustomTrapdoorBlock extends TrapdoorBlock {
    public CustomTrapdoorBlock(Settings settings) {
        super(BlockSetType.IRON, settings);
    }
}