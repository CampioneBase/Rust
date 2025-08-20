package com.adccadc.rust.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static RegistryEntry<StatusEffect> register(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of("rust", name), statusEffect);
    }

    public static final RegistryEntry<StatusEffect> TETANUS = register("tetanus", new TetanusEffecct());

    public static void initialize() {
    }
}
