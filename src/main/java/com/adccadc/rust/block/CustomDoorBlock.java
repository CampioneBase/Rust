package com.adccadc.rust.block;

import net.minecraft.block.BlockSetType;
import net.minecraft.block.DoorBlock;

public class CustomDoorBlock extends DoorBlock {
    public CustomDoorBlock(Settings settings) {
        super(BlockSetType.IRON, settings);
    }
}