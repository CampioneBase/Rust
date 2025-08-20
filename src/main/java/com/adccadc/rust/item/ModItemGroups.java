package com.adccadc.rust.item;

import com.adccadc.rust.block.Modblocks;
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

            itemGroup.add(Modblocks.EXPOSED_IRON_BLOCK.asItem());
            itemGroup.add(Modblocks.WEATHERED_IRON_BLOCK.asItem());
            itemGroup.add(Modblocks.OXIDIZED_IRON_BLOCK.asItem());
            itemGroup.add(Modblocks.WAXED_IRON_BLOCK.asItem());
            itemGroup.add(Modblocks.WAXED_EXPOSED_IRON_BLOCK.asItem());
            itemGroup.add(Modblocks.WAXED_WEATHERED_IRON_BLOCK.asItem());
            itemGroup.add(Modblocks.WAXED_OXIDIZED_IRON_BLOCK.asItem());

            itemGroup.add(Moditems.RUSTY_IRON_SWORD);
            itemGroup.add(Moditems.WAXED_IRON_SWORD);

            itemGroup.add(Modblocks.EXPOSED_IRON_BARS.asItem());
            itemGroup.add(Modblocks.WEATHERED_IRON_BARS.asItem());
            itemGroup.add(Modblocks.OXIDIZED_IRON_BARS.asItem());
            itemGroup.add(Modblocks.WAXED_IRON_BARS.asItem());
            itemGroup.add(Modblocks.WAXED_EXPOSED_IRON_BARS.asItem());
            itemGroup.add(Modblocks.WAXED_WEATHERED_IRON_BARS.asItem());
            itemGroup.add(Modblocks.WAXED_OXIDIZED_IRON_BARS.asItem());

            itemGroup.add(Moditems.RUSTY_IRON_AXE);
            itemGroup.add(Moditems.WAXED_IRON_AXE);

            itemGroup.add(Modblocks.EXPOSED_IRON_TRAPDOOR.asItem());
            itemGroup.add(Modblocks.WEATHERED_IRON_TRAPDOOR.asItem());
            itemGroup.add(Modblocks.OXIDIZED_IRON_TRAPDOOR.asItem());
            itemGroup.add(Modblocks.WAXED_IRON_TRAPDOOR.asItem());
            itemGroup.add(Modblocks.WAXED_EXPOSED_IRON_TRAPDOOR.asItem());
            itemGroup.add(Modblocks.WAXED_WEATHERED_IRON_TRAPDOOR.asItem());
            itemGroup.add(Modblocks.WAXED_OXIDIZED_IRON_TRAPDOOR.asItem());

            itemGroup.add(Moditems.RUSTY_IRON_PICKAXE);
            itemGroup.add(Moditems.WAXED_IRON_PICKAXE);

            itemGroup.add(Modblocks.EXPOSED_IRON_CHAIN.asItem());
            itemGroup.add(Modblocks.WEATHERED_IRON_CHAIN.asItem());
            itemGroup.add(Modblocks.OXIDIZED_IRON_CHAIN.asItem());
            itemGroup.add(Modblocks.WAXED_IRON_CHAIN.asItem());
            itemGroup.add(Modblocks.WAXED_EXPOSED_IRON_CHAIN.asItem());
            itemGroup.add(Modblocks.WAXED_WEATHERED_IRON_CHAIN.asItem());
            itemGroup.add(Modblocks.WAXED_OXIDIZED_IRON_CHAIN.asItem());

            itemGroup.add(Moditems.RUSTY_IRON_SHOVEL);
            itemGroup.add(Moditems.WAXED_IRON_SHOVEL);

            itemGroup.add(Modblocks.EXPOSED_IRON_DOOR.asItem());
            itemGroup.add(Modblocks.WEATHERED_IRON_DOOR.asItem());
            itemGroup.add(Modblocks.OXIDIZED_IRON_DOOR.asItem());
            itemGroup.add(Modblocks.WAXED_IRON_DOOR.asItem());
            itemGroup.add(Modblocks.WAXED_EXPOSED_IRON_DOOR.asItem());
            itemGroup.add(Modblocks.WAXED_WEATHERED_IRON_DOOR.asItem());
            itemGroup.add(Modblocks.WAXED_OXIDIZED_IRON_DOOR.asItem());

            itemGroup.add(Moditems.RUSTY_IRON_HOE);
            itemGroup.add(Moditems.WAXED_IRON_HOE);

            itemGroup.add(Modblocks.EXPOSED_HEAVY_WEIGHTED_PRESSURE_PLATE.asItem());
            itemGroup.add(Modblocks.WEATHERED_HEAVY_WEIGHTED_PRESSURE_PLATE.asItem());
            itemGroup.add(Modblocks.OXIDIZED_HEAVY_WEIGHTED_PRESSURE_PLATE.asItem());
            itemGroup.add(Modblocks.WAXED_HEAVY_WEIGHTED_PRESSURE_PLATE.asItem());
            itemGroup.add(Modblocks.WAXED_EXPOSED_HEAVY_WEIGHTED_PRESSURE_PLATE.asItem());
            itemGroup.add(Modblocks.WAXED_WEATHERED_HEAVY_WEIGHTED_PRESSURE_PLATE.asItem());
            itemGroup.add(Modblocks.WAXED_OXIDIZED_HEAVY_WEIGHTED_PRESSURE_PLATE.asItem());

            itemGroup.add(Moditems.RUSTY_IRON_HELMET);
            itemGroup.add(Moditems.WAXED_IRON_HELMET);

            itemGroup.add(Modblocks.EXPOSED_CAULDRON.asItem());
            itemGroup.add(Modblocks.WEATHERED_CAULDRON.asItem());
            itemGroup.add(Modblocks.OXIDIZED_CAULDRON.asItem());
            itemGroup.add(Modblocks.WAXED_CAULDRON.asItem());
            itemGroup.add(Modblocks.WAXED_EXPOSED_CAULDRON.asItem());
            itemGroup.add(Modblocks.WAXED_WEATHERED_CAULDRON.asItem());
            itemGroup.add(Modblocks.WAXED_OXIDIZED_CAULDRON.asItem());

            itemGroup.add(Moditems.RUSTY_IRON_CHESTPLATE);
            itemGroup.add(Moditems.WAXED_IRON_CHESTPLATE);

            itemGroup.add(Modblocks.EXPOSED_LANTERN.asItem());
            itemGroup.add(Modblocks.WEATHERED_LANTERN.asItem());
            itemGroup.add(Modblocks.OXIDIZED_LANTERN.asItem());
            itemGroup.add(Modblocks.WAXED_LANTERN.asItem());
            itemGroup.add(Modblocks.WAXED_EXPOSED_LANTERN.asItem());
            itemGroup.add(Modblocks.WAXED_WEATHERED_LANTERN.asItem());
            itemGroup.add(Modblocks.WAXED_OXIDIZED_LANTERN.asItem());

            itemGroup.add(Moditems.RUSTY_IRON_LEGGINGS);
            itemGroup.add(Moditems.WAXED_IRON_LEGGINGS);

            itemGroup.add(Modblocks.EXPOSED_SOUL_LANTERN.asItem());
            itemGroup.add(Modblocks.WEATHERED_SOUL_LANTERN.asItem());
            itemGroup.add(Modblocks.OXIDIZED_SOUL_LANTERN.asItem());
            itemGroup.add(Modblocks.WAXED_SOUL_LANTERN.asItem());
            itemGroup.add(Modblocks.WAXED_EXPOSED_SOUL_LANTERN.asItem());
            itemGroup.add(Modblocks.WAXED_WEATHERED_SOUL_LANTERN.asItem());
            itemGroup.add(Modblocks.WAXED_OXIDIZED_SOUL_LANTERN.asItem());

            itemGroup.add(Moditems.RUSTY_IRON_BOOTS);
            itemGroup.add(Moditems.WAXED_IRON_BOOTS);
        });
    }
}
