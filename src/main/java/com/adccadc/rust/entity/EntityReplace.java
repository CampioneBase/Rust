package com.adccadc.rust.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;

import java.util.List;
import java.util.Random;

import static net.minecraft.entity.SpawnReason.CONVERSION;

public class EntityReplace {
    public static void ReplaceIronGolemWithAttribute(ServerWorld serverWorld, IronGolemEntity ChangeIronGolem, Boolean isRusty) {
        IronGolemEntity ChangedIronGolem;
        if (isRusty == null) {
            ChangedIronGolem = EntityType.IRON_GOLEM.create(serverWorld,CONVERSION);
        } else if (isRusty) {
            ChangedIronGolem = ModEntity.EXPOSED_IRON_GOLEM.create(serverWorld,CONVERSION);
        } else {
            ChangedIronGolem = ModEntity.WAXED_IRON_GOLEM.create(serverWorld,CONVERSION);
        }
        if (ChangedIronGolem != null) {
            ChangedIronGolem.refreshPositionAndAngles(
                    ChangeIronGolem.getPos(),
                    ChangeIronGolem.getYaw(),
                    ChangeIronGolem.getPitch()
            );
            ChangedIronGolem.setVelocity(ChangeIronGolem.getVelocity());
            ChangedIronGolem.setHealth(ChangeIronGolem.getHealth());
        }
        ChangeIronGolem.discard();
        serverWorld.spawnEntity(ChangedIronGolem);
    }

    public static void ReplaceRustyEntityWithAttribute(ServerWorld world, Box searchbox) {
        List<Entity> NearbyEntity = world.getOtherEntities(null, searchbox);
        Random random = new Random();
        for ( Entity NeedChangeEntity : NearbyEntity ) {
            if (NeedChangeEntity.getType() == EntityType.IRON_GOLEM) {
                if (random.nextDouble() > 0.8) {
                    ReplaceIronGolemWithAttribute(world, (IronGolemEntity) NeedChangeEntity, true);
                }
            }
        }
    }
}
