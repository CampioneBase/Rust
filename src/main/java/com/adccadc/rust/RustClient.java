package com.adccadc.rust;

import com.adccadc.rust.entity.GolemEntityRenderer.*;
import com.adccadc.rust.entity.ModEntity;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class RustClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntity.EXPOSED_IRON_GOLEM, (context) -> new ExposedIronGolemEntityRenderer(context));
        EntityRendererRegistry.register(ModEntity.WEATHERED_IRON_GOLEM, (context) -> new WeatheredIronGolemEntityRenderer(context));
        EntityRendererRegistry.register(ModEntity.OXIDIZED_IRON_GOLEM, (context) -> new OxidizedIronGolemEntityRenderer(context));
        EntityRendererRegistry.register(ModEntity.WAXED_IRON_GOLEM, (context) -> new WaxedIronGolemEntityRenderer(context));
        EntityRendererRegistry.register(ModEntity.WAXED_EXPOSED_IRON_GOLEM, (context) -> new WaxedExposedIronGolemEntityRenderer(context));
        EntityRendererRegistry.register(ModEntity.WAXED_WEATHERED_IRON_GOLEM, (context) -> new WaxedWeatheredIronGolemEntityRenderer(context));
        EntityRendererRegistry.register(ModEntity.WAXED_OXIDIZED_IRON_GOLEM, (context) -> new WaxedOxidizedIronGolemEntityRenderer(context));
    }
}
