package com.adccadc.rust.item;

import com.adccadc.rust.entity.ModEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.SpawnEggItem;

public class RustyIronGolemEgg extends SpawnEggItem {
    public RustyIronGolemEgg(Settings settings) {
        super(ModEntity.RUSTY_IRON_GOLEM ,settings);
    }
}
