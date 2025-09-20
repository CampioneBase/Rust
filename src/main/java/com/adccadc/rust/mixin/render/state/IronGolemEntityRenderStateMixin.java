package com.adccadc.rust.mixin.render.state;

import com.adccadc.rust.manager.RustState;
import com.adccadc.rust.proxy.IronGolemEntityRenderStateProxy;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.state.IronGolemEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Environment(EnvType.CLIENT)
@Mixin(IronGolemEntityRenderState.class)
public class IronGolemEntityRenderStateMixin implements IronGolemEntityRenderStateProxy {
    @Unique
    public RustState rustState;

    @Override
    public void setRustState(RustState state) {
        this.rustState = state;
    }

    @Override
    public RustState getRustState() {
        return rustState;
    }
}
