package com.adccadc.rust.proxy;

import com.adccadc.rust.manager.RustManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface IronGolemEntityProxy {

    default IronGolemEntityProxy proxy(){
        return this;
    }

    RustManager getRust();
}
