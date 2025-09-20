package com.adccadc.rust.mixin.render;

import com.adccadc.rust.manager.RustState;
import com.adccadc.rust.proxy.IronGolemEntityProxy;
import com.adccadc.rust.proxy.IronGolemEntityRenderStateProxy;
import com.google.common.collect.ImmutableMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.IronGolemEntityRenderer;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.IronGolemEntityModel;
import net.minecraft.client.render.entity.state.IronGolemEntityRenderState;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(IronGolemEntityRenderer.class)
public abstract class IronGolemEntityRendererMixin extends MobEntityRenderer<IronGolemEntity, IronGolemEntityRenderState, IronGolemEntityModel>  {
    private static final ImmutableMap<Integer, Identifier> TEXTURES;
    private static final ImmutableMap<Integer, Identifier> WAXED_TEXTURES;

    public IronGolemEntityRendererMixin(EntityRendererFactory.Context context, IronGolemEntityModel entityModel, float f) {
        super(context, entityModel, f);
    }

    @Inject(
            method = "getTexture",
            at = @At("HEAD"),
            cancellable = true
    )
    public void getTextureWithRust(IronGolemEntityRenderState state, CallbackInfoReturnable<Identifier> cir){
        IronGolemEntityRenderStateProxy stateProxy = ((IronGolemEntityRenderStateProxy) state);
        RustState rustState = stateProxy.getRustState();
        int level = Math.max(0, Math.min(3, rustState.level));
        if(rustState.isWaxed)
            cir.setReturnValue(WAXED_TEXTURES.get(level));
        else
        cir.setReturnValue(TEXTURES.get(level));
    }

    @Inject(
            method = "updateRenderState",
            at = @At("TAIL")
    )
    public void updateRenderStateWithRust(
            IronGolemEntity entity,
            IronGolemEntityRenderState state,
            float f,
            CallbackInfo ci
    ){
        IronGolemEntityRenderStateProxy stateProxy = ((IronGolemEntityRenderStateProxy) state);
        IronGolemEntityProxy entityProxy = ((IronGolemEntityProxy) entity);
        stateProxy.setRustState(entityProxy.getRust().getState());
    }

    static {
        TEXTURES = ImmutableMap.of(
                0, Identifier.ofVanilla("textures/entity/iron_golem/iron_golem.png"),
                1, Identifier.of("rust", "textures/entity/exposed_iron_golem.png"),
                2, Identifier.of("rust", "textures/entity/weathered_iron_golem.png"),
                3, Identifier.of("rust", "textures/entity/oxidized_iron_golem.png")
        );
        WAXED_TEXTURES = ImmutableMap.of(
                0, Identifier.of("rust", "textures/entity/waxed_iron_golem.png"),
                1, Identifier.of("rust", "textures/entity/waxed_exposed_iron_golem.png"),
                2, Identifier.of("rust", "textures/entity/waxed_weathered_iron_golem.png"),
                3, Identifier.of("rust", "textures/entity/waxed_oxidized_iron_golem.png")
        );
    }
}
