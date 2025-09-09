package com.adccadc.rust.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
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

    public static final EntityType<IronGolemEntity> RUSTY_IRON_GOLEM = register("rusty_iron_golem", EntityType.Builder.create(IronGolemEntity::new, SpawnGroup.MISC).dimensions(1.4F, 2.7F).maxTrackingRange(8));
    public static final EntityType<IronGolemEntity> WAXED_IRON_GOLEM = register("waxed_iron_golem", EntityType.Builder.create(IronGolemEntity::new, SpawnGroup.MISC).dimensions(1.4F, 2.7F).maxTrackingRange(9));


    public static void initialize() {
        FabricDefaultAttributeRegistry.register(RUSTY_IRON_GOLEM, IronGolemEntity.createIronGolemAttributes().build());
        FabricDefaultAttributeRegistry.register(WAXED_IRON_GOLEM, IronGolemEntity.createIronGolemAttributes().build());
    }
}
