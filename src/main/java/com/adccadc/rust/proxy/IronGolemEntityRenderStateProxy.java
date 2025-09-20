package com.adccadc.rust.proxy;

import com.adccadc.rust.manager.RustState;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface IronGolemEntityRenderStateProxy {

    void setRustState(RustState state);

    RustState getRustState();
}
