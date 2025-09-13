package com.adccadc.rust.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final RegistryKey<ItemGroup> CUSTOM_GROUP_KEY = RegistryKey.of(
            Registries.ITEM_GROUP.getKey(),
            Identifier.of("rust", "custom_group")
    );

    public static final ItemGroup CUSTOM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(Moditems.VERDIGRIS))
            .displayName(Text.translatable("rust"))
            .build();

    public static void register() {
        Registry.register(Registries.ITEM_GROUP, CUSTOM_GROUP_KEY, CUSTOM_GROUP);

        ItemGroupEvents.modifyEntriesEvent(CUSTOM_GROUP_KEY).register(itemGroup -> {

            itemGroup.add(Moditems.VERDIGRIS);
            itemGroup.add(Moditems.IRON_RUST);

            itemGroup.add(Moditems.EXPOSED_IRON_BLOCK);
            itemGroup.add(Moditems.WEATHERED_IRON_BLOCK);
            itemGroup.add(Moditems.OXIDIZED_IRON_BLOCK);
            itemGroup.add(Moditems.WAXED_IRON_BLOCK);
            itemGroup.add(Moditems.WAXED_EXPOSED_IRON_BLOCK);
            itemGroup.add(Moditems.WAXED_WEATHERED_IRON_BLOCK);
            itemGroup.add(Moditems.WAXED_OXIDIZED_IRON_BLOCK);

            itemGroup.add(Moditems.RUSTY_IRON_SWORD);
            itemGroup.add(Moditems.WAXED_IRON_SWORD);

            itemGroup.add(Moditems.EXPOSED_IRON_BARS);
            itemGroup.add(Moditems.WEATHERED_IRON_BARS);
            itemGroup.add(Moditems.OXIDIZED_IRON_BARS);
            itemGroup.add(Moditems.WAXED_IRON_BARS);
            itemGroup.add(Moditems.WAXED_EXPOSED_IRON_BARS);
            itemGroup.add(Moditems.WAXED_WEATHERED_IRON_BARS);
            itemGroup.add(Moditems.WAXED_OXIDIZED_IRON_BARS);

            itemGroup.add(Moditems.RUSTY_IRON_AXE);
            itemGroup.add(Moditems.WAXED_IRON_AXE);

            itemGroup.add(Moditems.EXPOSED_IRON_TRAPDOOR);
            itemGroup.add(Moditems.WEATHERED_IRON_TRAPDOOR);
            itemGroup.add(Moditems.OXIDIZED_IRON_TRAPDOOR);
            itemGroup.add(Moditems.WAXED_IRON_TRAPDOOR);
            itemGroup.add(Moditems.WAXED_EXPOSED_IRON_TRAPDOOR);
            itemGroup.add(Moditems.WAXED_WEATHERED_IRON_TRAPDOOR);
            itemGroup.add(Moditems.WAXED_OXIDIZED_IRON_TRAPDOOR);

            itemGroup.add(Moditems.RUSTY_IRON_PICKAXE);
            itemGroup.add(Moditems.WAXED_IRON_PICKAXE);

            itemGroup.add(Moditems.EXPOSED_IRON_CHAIN);
            itemGroup.add(Moditems.WEATHERED_IRON_CHAIN);
            itemGroup.add(Moditems.OXIDIZED_IRON_CHAIN);
            itemGroup.add(Moditems.WAXED_IRON_CHAIN);
            itemGroup.add(Moditems.WAXED_EXPOSED_IRON_CHAIN);
            itemGroup.add(Moditems.WAXED_WEATHERED_IRON_CHAIN);
            itemGroup.add(Moditems.WAXED_OXIDIZED_IRON_CHAIN);

            itemGroup.add(Moditems.RUSTY_IRON_SHOVEL);
            itemGroup.add(Moditems.WAXED_IRON_SHOVEL);

            itemGroup.add(Moditems.EXPOSED_IRON_DOOR);
            itemGroup.add(Moditems.WEATHERED_IRON_DOOR);
            itemGroup.add(Moditems.OXIDIZED_IRON_DOOR);
            itemGroup.add(Moditems.WAXED_IRON_DOOR);
            itemGroup.add(Moditems.WAXED_EXPOSED_IRON_DOOR);
            itemGroup.add(Moditems.WAXED_WEATHERED_IRON_DOOR);
            itemGroup.add(Moditems.WAXED_OXIDIZED_IRON_DOOR);

            itemGroup.add(Moditems.RUSTY_IRON_HOE);
            itemGroup.add(Moditems.WAXED_IRON_HOE);

            itemGroup.add(Moditems.EXPOSED_HEAVY_WEIGHTED_PRESSURE_PLATE);
            itemGroup.add(Moditems.WEATHERED_HEAVY_WEIGHTED_PRESSURE_PLATE);
            itemGroup.add(Moditems.OXIDIZED_HEAVY_WEIGHTED_PRESSURE_PLATE);
            itemGroup.add(Moditems.WAXED_HEAVY_WEIGHTED_PRESSURE_PLATE);
            itemGroup.add(Moditems.WAXED_EXPOSED_HEAVY_WEIGHTED_PRESSURE_PLATE);
            itemGroup.add(Moditems.WAXED_WEATHERED_HEAVY_WEIGHTED_PRESSURE_PLATE);
            itemGroup.add(Moditems.WAXED_OXIDIZED_HEAVY_WEIGHTED_PRESSURE_PLATE);

            itemGroup.add(Moditems.RUSTY_IRON_HELMET);
            itemGroup.add(Moditems.WAXED_IRON_HELMET);

            itemGroup.add(Moditems.EXPOSED_CAULDRON);
            itemGroup.add(Moditems.WEATHERED_CAULDRON);
            itemGroup.add(Moditems.OXIDIZED_CAULDRON);
            itemGroup.add(Moditems.WAXED_CAULDRON);
            itemGroup.add(Moditems.WAXED_EXPOSED_CAULDRON);
            itemGroup.add(Moditems.WAXED_WEATHERED_CAULDRON);
            itemGroup.add(Moditems.WAXED_OXIDIZED_CAULDRON);

            itemGroup.add(Moditems.RUSTY_IRON_CHESTPLATE);
            itemGroup.add(Moditems.WAXED_IRON_CHESTPLATE);

            itemGroup.add(Moditems.EXPOSED_LANTERN);
            itemGroup.add(Moditems.WEATHERED_LANTERN);
            itemGroup.add(Moditems.OXIDIZED_LANTERN);
            itemGroup.add(Moditems.WAXED_LANTERN);
            itemGroup.add(Moditems.WAXED_EXPOSED_LANTERN);
            itemGroup.add(Moditems.WAXED_WEATHERED_LANTERN);
            itemGroup.add(Moditems.WAXED_OXIDIZED_LANTERN);

            itemGroup.add(Moditems.RUSTY_IRON_LEGGINGS);
            itemGroup.add(Moditems.WAXED_IRON_LEGGINGS);

            itemGroup.add(Moditems.EXPOSED_SOUL_LANTERN);
            itemGroup.add(Moditems.WEATHERED_SOUL_LANTERN);
            itemGroup.add(Moditems.OXIDIZED_SOUL_LANTERN);
            itemGroup.add(Moditems.WAXED_SOUL_LANTERN);
            itemGroup.add(Moditems.WAXED_EXPOSED_SOUL_LANTERN);
            itemGroup.add(Moditems.WAXED_WEATHERED_SOUL_LANTERN);
            itemGroup.add(Moditems.WAXED_OXIDIZED_SOUL_LANTERN);

            itemGroup.add(Moditems.RUSTY_IRON_BOOTS);
            itemGroup.add(Moditems.WAXED_IRON_BOOTS);

            itemGroup.add(Moditems.RUSTY_IRON_GOLEM_EGG);
            itemGroup.add(Moditems.WAXED_IRON_GOLEM_EGG);
        });
    }
}
