package com.adccadc.rust;

import com.adccadc.rust.entity.ModEntity;
import com.adccadc.rust.entity.RustyIronGolemEntityRenderer;
import com.adccadc.rust.entity.WaxedIronGolemEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class RustClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntity.RUSTY_IRON_GOLEM, (context) -> new RustyIronGolemEntityRenderer(context));
        EntityRendererRegistry.register(ModEntity.WAXED_IRON_GOLEM, (context) -> new WaxedIronGolemEntityRenderer(context));
    }
}
