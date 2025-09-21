package com.adccadc.rust.potion;

import com.adccadc.rust.Rust;
import com.adccadc.rust.effect.ModEffects;
import com.adccadc.rust.item.Moditems;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModPotion {

    public static final Potion TETANUS = Registry.register(Registries.POTION, Identifier.of(Rust.MOD_ID, "tetanus"), new Potion("tetanus", new StatusEffectInstance(ModEffects.TETANUS, 3600, 0)));
    public static final Potion LONG_TETANUS = Registry.register(Registries.POTION, Identifier.of(Rust.MOD_ID, "long_tetanus"), new Potion("long_tetanus", new StatusEffectInstance(ModEffects.TETANUS, 9600, 0)));
    public static final Potion STRONG_TETANUS = Registry.register(Registries.POTION, Identifier.of(Rust.MOD_ID, "strong_tetanus"), new Potion("strong_tetanus", new StatusEffectInstance(ModEffects.TETANUS, 1200, 1)));

    public static void initialize() {
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> builder.registerPotionRecipe(Potions.WEAKNESS, Moditems.IRON_RUST, Registries.POTION.getEntry(TETANUS)));
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> builder.registerPotionRecipe(Potions.WEAKNESS, Moditems.VERDIGRIS, Registries.POTION.getEntry(TETANUS)));
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> builder.registerPotionRecipe(Registries.POTION.getEntry(TETANUS), Items.REDSTONE, Registries.POTION.getEntry(LONG_TETANUS)));
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> builder.registerPotionRecipe(Registries.POTION.getEntry(TETANUS), Items.GLOWSTONE_DUST, Registries.POTION.getEntry(STRONG_TETANUS)));
    }
}
