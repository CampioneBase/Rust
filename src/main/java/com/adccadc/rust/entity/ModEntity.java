package com.adccadc.rust.entity;

import com.adccadc.rust.RustConfig;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModEntity {
    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> type) {
        RegistryKey<EntityType<?>> registryKey = RegistryKey.of(Registries.ENTITY_TYPE.getKey(), Identifier.of("rust", id));
        return Registry.register(Registries.ENTITY_TYPE, Identifier.of("rust", id), type.build(registryKey));
    }

    public static final EntityType<IronGolemEntity> EXPOSED_IRON_GOLEM = register("exposed_iron_golem", EntityType.Builder.create(IronGolemEntity::new, SpawnGroup.MISC).dimensions(1.4F, 2.7F).maxTrackingRange(10));
    public static final EntityType<IronGolemEntity> WEATHERED_IRON_GOLEM = register("weathered_iron_golem", EntityType.Builder.create(IronGolemEntity::new, SpawnGroup.MISC).dimensions(1.4F, 2.7F).maxTrackingRange(10));
    public static final EntityType<IronGolemEntity> OXIDIZED_IRON_GOLEM = register("oxidized_iron_golem", EntityType.Builder.create(IronGolemEntity::new, SpawnGroup.MISC).dimensions(1.4F, 2.7F).maxTrackingRange(10));
    public static final EntityType<IronGolemEntity> WAXED_IRON_GOLEM = register("waxed_iron_golem", EntityType.Builder.create(IronGolemEntity::new, SpawnGroup.MISC).dimensions(1.4F, 2.7F).maxTrackingRange(10));
    public static final EntityType<IronGolemEntity> WAXED_EXPOSED_IRON_GOLEM = register("waxed_exposed_iron_golem", EntityType.Builder.create(IronGolemEntity::new, SpawnGroup.MISC).dimensions(1.4F, 2.7F).maxTrackingRange(10));
    public static final EntityType<IronGolemEntity> WAXED_WEATHERED_IRON_GOLEM = register("waxed_weathered_iron_golem", EntityType.Builder.create(IronGolemEntity::new, SpawnGroup.MISC).dimensions(1.4F, 2.7F).maxTrackingRange(10));
    public static final EntityType<IronGolemEntity> WAXED_OXIDIZED_IRON_GOLEM = register("waxed_oxidized_iron_golem", EntityType.Builder.create(IronGolemEntity::new, SpawnGroup.MISC).dimensions(1.4F, 2.7F).maxTrackingRange(10));


    public static void initialize() {
        FabricDefaultAttributeRegistry.register(EXPOSED_IRON_GOLEM, IronGolemEntity.createIronGolemAttributes().add(EntityAttributes.MAX_HEALTH, (double) RustConfig.getExposed_IG().getFirst()).add(EntityAttributes.MOVEMENT_SPEED, (double) RustConfig.getExposed_IG().get(1)).add(EntityAttributes.KNOCKBACK_RESISTANCE, (double) RustConfig.getExposed_IG().get(2)).add(EntityAttributes.ATTACK_DAMAGE, (double) RustConfig.getExposed_IG().get(3)).add(EntityAttributes.STEP_HEIGHT, (double) RustConfig.getExposed_IG().get(4)).add(EntityAttributes.FOLLOW_RANGE, (double) RustConfig.getExposed_IG().get(5)).build());
        FabricDefaultAttributeRegistry.register(WEATHERED_IRON_GOLEM, IronGolemEntity.createIronGolemAttributes().add(EntityAttributes.MAX_HEALTH, (double) RustConfig.getWeathered_IG().getFirst()).add(EntityAttributes.MOVEMENT_SPEED, (double) RustConfig.getWeathered_IG().get(1)).add(EntityAttributes.KNOCKBACK_RESISTANCE, (double) RustConfig.getWeathered_IG().get(2)).add(EntityAttributes.ATTACK_DAMAGE, (double) RustConfig.getWeathered_IG().get(3)).add(EntityAttributes.STEP_HEIGHT, (double) RustConfig.getWeathered_IG().get(4)).add(EntityAttributes.FOLLOW_RANGE, (double) RustConfig.getWeathered_IG().get(5)).build());
        FabricDefaultAttributeRegistry.register(OXIDIZED_IRON_GOLEM, IronGolemEntity.createIronGolemAttributes().add(EntityAttributes.MAX_HEALTH, (double) RustConfig.getOxidized_IG().getFirst()).add(EntityAttributes.MOVEMENT_SPEED, (double) RustConfig.getOxidized_IG().get(1)).add(EntityAttributes.KNOCKBACK_RESISTANCE, (double) RustConfig.getOxidized_IG().get(2)).add(EntityAttributes.ATTACK_DAMAGE, (double) RustConfig.getOxidized_IG().get(3)).add(EntityAttributes.STEP_HEIGHT, (double) RustConfig.getOxidized_IG().get(4)).add(EntityAttributes.FOLLOW_RANGE, (double) RustConfig.getOxidized_IG().get(5)).build());
        FabricDefaultAttributeRegistry.register(WAXED_IRON_GOLEM, IronGolemEntity.createIronGolemAttributes().add(EntityAttributes.MAX_HEALTH, (double) RustConfig.getWaxed_IG().getFirst()).add(EntityAttributes.MOVEMENT_SPEED, (double) RustConfig.getWaxed_IG().get(1)).add(EntityAttributes.KNOCKBACK_RESISTANCE, (double) RustConfig.getWaxed_IG().get(2)).add(EntityAttributes.ATTACK_DAMAGE, (double) RustConfig.getWaxed_IG().get(3)).add(EntityAttributes.STEP_HEIGHT, (double) RustConfig.getWaxed_IG().get(4)).add(EntityAttributes.FOLLOW_RANGE, (double) RustConfig.getWaxed_IG().get(5)).build());
        FabricDefaultAttributeRegistry.register(WAXED_EXPOSED_IRON_GOLEM, IronGolemEntity.createIronGolemAttributes().add(EntityAttributes.MAX_HEALTH, (double) RustConfig.getWaxed_exposed_IG().getFirst()).add(EntityAttributes.MOVEMENT_SPEED, (double) RustConfig.getWaxed_exposed_IG().get(1)).add(EntityAttributes.KNOCKBACK_RESISTANCE, (double) RustConfig.getWaxed_exposed_IG().get(2)).add(EntityAttributes.ATTACK_DAMAGE, (double) RustConfig.getWaxed_exposed_IG().get(3)).add(EntityAttributes.STEP_HEIGHT, (double) RustConfig.getWaxed_exposed_IG().get(4)).add(EntityAttributes.FOLLOW_RANGE, (double) RustConfig.getWaxed_exposed_IG().get(5)).build());
        FabricDefaultAttributeRegistry.register(WAXED_WEATHERED_IRON_GOLEM, IronGolemEntity.createIronGolemAttributes().add(EntityAttributes.MAX_HEALTH, (double) RustConfig.getWaxed_weathered_IG().getFirst()).add(EntityAttributes.MOVEMENT_SPEED, (double) RustConfig.getWeathered_IG().get(1)).add(EntityAttributes.KNOCKBACK_RESISTANCE, (double) RustConfig.getWeathered_IG().get(2)).add(EntityAttributes.ATTACK_DAMAGE, (double) RustConfig.getWeathered_IG().get(3)).add(EntityAttributes.STEP_HEIGHT, (double) RustConfig.getWeathered_IG().get(4)).add(EntityAttributes.FOLLOW_RANGE, (double) RustConfig.getWeathered_IG().get(5)).build());
        FabricDefaultAttributeRegistry.register(WAXED_OXIDIZED_IRON_GOLEM, IronGolemEntity.createIronGolemAttributes().add(EntityAttributes.MAX_HEALTH, (double) RustConfig.getWaxed_oxidized_IG().getFirst()).add(EntityAttributes.MOVEMENT_SPEED, (double) RustConfig.getOxidized_IG().get(1)).add(EntityAttributes.KNOCKBACK_RESISTANCE, (double) RustConfig.getOxidized_IG().get(2)).add(EntityAttributes.ATTACK_DAMAGE, (double) RustConfig.getOxidized_IG().get(3)).add(EntityAttributes.STEP_HEIGHT, (double) RustConfig.getOxidized_IG().get(4)).add(EntityAttributes.FOLLOW_RANGE, (double) RustConfig.getOxidized_IG().get(5)).build());
    }
}
