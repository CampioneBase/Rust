package com.adccadc.rust.item;

import com.adccadc.rust.Rust;
import com.adccadc.rust.block.Modblocks;
import com.adccadc.rust.entity.ModEntity;
import net.minecraft.item.*;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.function.Function;

public class Moditems {
    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of("rust", name));
        Item item = itemFactory.apply(settings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);
        return item;
    }

    public static final ToolMaterial IRON_CORROSION = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 200, 5.0F, 1.5F, 18, ItemTags.IRON_TOOL_MATERIALS);
    public static final ToolMaterial IRON_WAXED = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 260, 5.5F, 1.8F, 16, ItemTags.IRON_TOOL_MATERIALS);

    public static final RegistryKey<EquipmentAsset> RUSTY = RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, Identifier.of(Rust.MOD_ID, "rusty"));
    public static final ArmorMaterial ARMOR_IRON_CORROSION = new ArmorMaterial(
            14,
            Map.of(
                    EquipmentType.HELMET, 1,
                    EquipmentType.CHESTPLATE, 5,
                    EquipmentType.LEGGINGS, 4,
                    EquipmentType.BOOTS, 1
            ), 8,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F, 0.0F,
            ItemTags.IRON_TOOL_MATERIALS, RUSTY
    );

    public static final RegistryKey<EquipmentAsset> WAXED = RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, Identifier.of(Rust.MOD_ID, "waxed"));
    public static final ArmorMaterial ARMOR_IRON_WAXED = new ArmorMaterial(
            16,
            Map.of(
                    EquipmentType.HELMET, 2,
                    EquipmentType.CHESTPLATE, 6,
                    EquipmentType.LEGGINGS, 5,
                    EquipmentType.BOOTS, 2
            ), 6,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.25F, 0.025F,
            ItemTags.IRON_TOOL_MATERIALS, WAXED
    );
    public static final Item EXPOSED_IRON_BLOCK = register("exposed_iron_block", (settings) -> new BlockItem(Modblocks.EXPOSED_IRON_BLOCK, settings), new Item.Settings());
    public static final Item WEATHERED_IRON_BLOCK = register("weathered_iron_block", (settings) -> new BlockItem(Modblocks.WEATHERED_IRON_BLOCK, settings), new Item.Settings());
    public static final Item OXIDIZED_IRON_BLOCK = register("oxidized_iron_block", (settings) -> new BlockItem(Modblocks.OXIDIZED_IRON_BLOCK, settings), new Item.Settings());
    public static final Item WAXED_IRON_BLOCK = register("waxed_iron_block", (settings) -> new BlockItem(Modblocks.WAXED_IRON_BLOCK, settings), new Item.Settings());
    public static final Item WAXED_EXPOSED_IRON_BLOCK = register("waxed_exposed_iron_block", (settings) -> new BlockItem(Modblocks.WAXED_WEATHERED_IRON_BLOCK, settings), new Item.Settings());
    public static final Item WAXED_WEATHERED_IRON_BLOCK = register("waxed_weathered_iron_block", (settings) -> new BlockItem(Modblocks.WAXED_WEATHERED_IRON_BLOCK, settings), new Item.Settings());
    public static final Item WAXED_OXIDIZED_IRON_BLOCK = register("waxed_oxidized_iron_block", (settings) -> new BlockItem(Modblocks.WAXED_OXIDIZED_IRON_BLOCK, settings), new Item.Settings());

    public static final Item EXPOSED_IRON_BARS = register("exposed_iron_bars", (settings) -> new BlockItem(Modblocks.EXPOSED_IRON_BARS, settings), new Item.Settings());
    public static final Item WEATHERED_IRON_BARS = register("weathered_iron_bars", (settings) -> new BlockItem(Modblocks.WEATHERED_IRON_BARS, settings), new Item.Settings());
    public static final Item OXIDIZED_IRON_BARS = register("oxidized_iron_bars", (settings) -> new BlockItem(Modblocks.OXIDIZED_IRON_BARS, settings), new Item.Settings());
    public static final Item WAXED_IRON_BARS = register("waxed_iron_bars", (settings) -> new BlockItem(Modblocks.WAXED_IRON_BARS, settings), new Item.Settings());
    public static final Item WAXED_EXPOSED_IRON_BARS = register("waxed_exposed_iron_bars", (settings) -> new BlockItem(Modblocks.WAXED_EXPOSED_IRON_BARS, settings), new Item.Settings());
    public static final Item WAXED_WEATHERED_IRON_BARS = register("waxed_weathered_iron_bars", (settings) -> new BlockItem(Modblocks.WAXED_WEATHERED_IRON_BARS, settings), new Item.Settings());
    public static final Item WAXED_OXIDIZED_IRON_BARS = register("waxed_oxidized_iron_bars", (settings) -> new BlockItem(Modblocks.WAXED_OXIDIZED_IRON_BARS, settings), new Item.Settings());

    public static final Item EXPOSED_IRON_TRAPDOOR = register("exposed_iron_trapdoor", (settings) -> new BlockItem(Modblocks.EXPOSED_IRON_TRAPDOOR, settings), new Item.Settings());
    public static final Item WEATHERED_IRON_TRAPDOOR = register("weathered_iron_trapdoor", (settings) -> new BlockItem(Modblocks.WEATHERED_IRON_TRAPDOOR, settings), new Item.Settings());
    public static final Item OXIDIZED_IRON_TRAPDOOR = register("oxidized_iron_trapdoor", (settings) -> new BlockItem(Modblocks.OXIDIZED_IRON_TRAPDOOR, settings), new Item.Settings());
    public static final Item WAXED_IRON_TRAPDOOR = register("waxed_iron_trapdoor", (settings) -> new BlockItem(Modblocks.WAXED_IRON_TRAPDOOR, settings), new Item.Settings());
    public static final Item WAXED_EXPOSED_IRON_TRAPDOOR = register("waxed_exposed_iron_trapdoor", (settings) -> new BlockItem(Modblocks.WAXED_EXPOSED_IRON_TRAPDOOR, settings), new Item.Settings());
    public static final Item WAXED_WEATHERED_IRON_TRAPDOOR = register("waxed_weathered_iron_trapdoor", (settings) -> new BlockItem(Modblocks.WAXED_WEATHERED_IRON_TRAPDOOR, settings), new Item.Settings());
    public static final Item WAXED_OXIDIZED_IRON_TRAPDOOR = register("waxed_oxidized_iron_trapdoor", (settings) -> new BlockItem(Modblocks.WAXED_OXIDIZED_IRON_TRAPDOOR, settings), new Item.Settings());

    public static final Item EXPOSED_IRON_DOOR = register("exposed_iron_door", (settings) -> new BlockItem(Modblocks.EXPOSED_IRON_DOOR, settings), new Item.Settings());
    public static final Item WEATHERED_IRON_DOOR = register("weathered_iron_door", (settings) -> new BlockItem(Modblocks.WEATHERED_IRON_DOOR, settings), new Item.Settings());
    public static final Item OXIDIZED_IRON_DOOR = register("oxidized_iron_door", (settings) -> new BlockItem(Modblocks.OXIDIZED_IRON_DOOR, settings), new Item.Settings());
    public static final Item WAXED_IRON_DOOR = register("waxed_iron_door", (settings) -> new BlockItem(Modblocks.WAXED_IRON_DOOR, settings), new Item.Settings());
    public static final Item WAXED_EXPOSED_IRON_DOOR = register("waxed_exposed_iron_door", (settings) -> new BlockItem(Modblocks.WAXED_EXPOSED_IRON_DOOR, settings), new Item.Settings());
    public static final Item WAXED_WEATHERED_IRON_DOOR = register("waxed_weathered_iron_door", (settings) -> new BlockItem(Modblocks.WAXED_WEATHERED_IRON_DOOR, settings), new Item.Settings());
    public static final Item WAXED_OXIDIZED_IRON_DOOR = register("waxed_oxidized_iron_door", (settings) -> new BlockItem(Modblocks.WAXED_OXIDIZED_IRON_DOOR, settings), new Item.Settings());

    public static final Item EXPOSED_IRON_CHAIN = register("exposed_iron_chain", (settings) -> new BlockItem(Modblocks.EXPOSED_IRON_CHAIN, settings), new Item.Settings());
    public static final Item WEATHERED_IRON_CHAIN = register("weathered_iron_chain", (settings) -> new BlockItem(Modblocks.WEATHERED_IRON_CHAIN, settings), new Item.Settings());
    public static final Item OXIDIZED_IRON_CHAIN = register("oxidized_iron_chain", (settings) -> new BlockItem(Modblocks.OXIDIZED_IRON_CHAIN, settings), new Item.Settings());
    public static final Item WAXED_IRON_CHAIN = register("waxed_iron_chain", (settings) -> new BlockItem(Modblocks.WAXED_IRON_CHAIN, settings), new Item.Settings());
    public static final Item WAXED_EXPOSED_IRON_CHAIN = register("waxed_exposed_iron_chain", (settings) -> new BlockItem(Modblocks.WAXED_EXPOSED_IRON_CHAIN, settings), new Item.Settings());
    public static final Item WAXED_WEATHERED_IRON_CHAIN = register("waxed_weathered_iron_chain", (settings) -> new BlockItem(Modblocks.WAXED_WEATHERED_IRON_CHAIN, settings), new Item.Settings());
    public static final Item WAXED_OXIDIZED_IRON_CHAIN = register("waxed_oxidized_iron_chain", (settings) -> new BlockItem(Modblocks.WAXED_OXIDIZED_IRON_CHAIN, settings), new Item.Settings());

    public static final Item EXPOSED_CAULDRON = register("exposed_cauldron", (settings) -> new BlockItem(Modblocks.EXPOSED_CAULDRON, settings), new Item.Settings());
    public static final Item WEATHERED_CAULDRON = register("weathered_cauldron", (settings) -> new BlockItem(Modblocks.WEATHERED_CAULDRON, settings), new Item.Settings());
    public static final Item OXIDIZED_CAULDRON = register("oxidized_cauldron", (settings) -> new BlockItem(Modblocks.OXIDIZED_CAULDRON, settings), new Item.Settings());
    public static final Item WAXED_CAULDRON = register("waxed_cauldron", (settings) -> new BlockItem(Modblocks.WAXED_CAULDRON, settings), new Item.Settings());
    public static final Item WAXED_EXPOSED_CAULDRON = register("waxed_exposed_cauldron", (settings) -> new BlockItem(Modblocks.WAXED_EXPOSED_CAULDRON, settings), new Item.Settings());
    public static final Item WAXED_WEATHERED_CAULDRON = register("waxed_weathered_cauldron", (settings) -> new BlockItem(Modblocks.WAXED_WEATHERED_CAULDRON, settings), new Item.Settings());
    public static final Item WAXED_OXIDIZED_CAULDRON = register("waxed_oxidized_cauldron", (settings) -> new BlockItem(Modblocks.WAXED_OXIDIZED_CAULDRON, settings), new Item.Settings());

    public static final Item EXPOSED_HEAVY_WEIGHTED_PRESSURE_PLATE = register("exposed_heavy_weighted_pressure_plate", (settings) -> new BlockItem(Modblocks.EXPOSED_HEAVY_WEIGHTED_PRESSURE_PLATE, settings), new Item.Settings());
    public static final Item WEATHERED_HEAVY_WEIGHTED_PRESSURE_PLATE = register("weathered_heavy_weighted_pressure_plate", (settings) -> new BlockItem(Modblocks.WEATHERED_HEAVY_WEIGHTED_PRESSURE_PLATE, settings), new Item.Settings());
    public static final Item OXIDIZED_HEAVY_WEIGHTED_PRESSURE_PLATE = register("oxidized_heavy_weighted_pressure_plate", (settings) -> new BlockItem(Modblocks.OXIDIZED_HEAVY_WEIGHTED_PRESSURE_PLATE, settings), new Item.Settings());
    public static final Item WAXED_HEAVY_WEIGHTED_PRESSURE_PLATE = register("waxed_heavy_weighted_pressure_plate", (settings) -> new BlockItem(Modblocks.WAXED_HEAVY_WEIGHTED_PRESSURE_PLATE, settings), new Item.Settings());
    public static final Item WAXED_EXPOSED_HEAVY_WEIGHTED_PRESSURE_PLATE = register("waxed_exposed_heavy_weighted_pressure_plate", (settings) -> new BlockItem(Modblocks.WAXED_EXPOSED_HEAVY_WEIGHTED_PRESSURE_PLATE, settings), new Item.Settings());
    public static final Item WAXED_WEATHERED_HEAVY_WEIGHTED_PRESSURE_PLATE = register("waxed_weathered_heavy_weighted_pressure_plate", (settings) -> new BlockItem(Modblocks.WAXED_WEATHERED_HEAVY_WEIGHTED_PRESSURE_PLATE, settings), new Item.Settings());
    public static final Item WAXED_OXIDIZED_HEAVY_WEIGHTED_PRESSURE_PLATE = register("waxed_oxidized_heavy_weighted_pressure_plate", (settings) -> new BlockItem(Modblocks.WAXED_OXIDIZED_HEAVY_WEIGHTED_PRESSURE_PLATE, settings), new Item.Settings());

    public static final Item EXPOSED_LANTERN = register("exposed_lantern", (settings) -> new BlockItem(Modblocks.EXPOSED_LANTERN, settings), new Item.Settings());
    public static final Item WEATHERED_LANTERN = register("weathered_lantern", (settings) -> new BlockItem(Modblocks.WEATHERED_LANTERN, settings), new Item.Settings());
    public static final Item OXIDIZED_LANTERN = register("oxidized_lantern", (settings) -> new BlockItem(Modblocks.OXIDIZED_LANTERN, settings), new Item.Settings());
    public static final Item WAXED_LANTERN = register("waxed_lantern", (settings) -> new BlockItem(Modblocks.WAXED_LANTERN, settings), new Item.Settings());
    public static final Item WAXED_EXPOSED_LANTERN = register("waxed_exposed_lantern", (settings) -> new BlockItem(Modblocks.WAXED_EXPOSED_LANTERN, settings), new Item.Settings());
    public static final Item WAXED_WEATHERED_LANTERN = register("waxed_weathered_lantern", (settings) -> new BlockItem(Modblocks.WAXED_WEATHERED_LANTERN, settings), new Item.Settings());
    public static final Item WAXED_OXIDIZED_LANTERN = register("waxed_oxidized_lantern", (settings) -> new BlockItem(Modblocks.WAXED_OXIDIZED_LANTERN, settings), new Item.Settings());

    public static final Item EXPOSED_SOUL_LANTERN = register("exposed_soul_lantern", (settings) -> new BlockItem(Modblocks.EXPOSED_SOUL_LANTERN, settings), new Item.Settings());
    public static final Item WEATHERED_SOUL_LANTERN = register("weathered_soul_lantern", (settings) -> new BlockItem(Modblocks.WEATHERED_SOUL_LANTERN, settings), new Item.Settings());
    public static final Item OXIDIZED_SOUL_LANTERN = register("oxidized_soul_lantern", (settings) -> new BlockItem(Modblocks.OXIDIZED_SOUL_LANTERN, settings), new Item.Settings());
    public static final Item WAXED_SOUL_LANTERN = register("waxed_soul_lantern", (settings) -> new BlockItem(Modblocks.WAXED_SOUL_LANTERN, settings), new Item.Settings());
    public static final Item WAXED_EXPOSED_SOUL_LANTERN = register("waxed_exposed_soul_lantern", (settings) -> new BlockItem(Modblocks.WAXED_EXPOSED_SOUL_LANTERN, settings), new Item.Settings());
    public static final Item WAXED_WEATHERED_SOUL_LANTERN = register("waxed_weathered_soul_lantern", (settings) -> new BlockItem(Modblocks.WAXED_WEATHERED_SOUL_LANTERN, settings), new Item.Settings());
    public static final Item WAXED_OXIDIZED_SOUL_LANTERN = register("waxed_oxidized_soul_lantern", (settings) -> new BlockItem(Modblocks.WAXED_OXIDIZED_SOUL_LANTERN, settings), new Item.Settings());

    public static final Item RUSTY_IRON_GOLEM_EGG = register("rusty_iron_golem_spawn_egg", (settings) -> new SpawnEggItem(ModEntity.RUSTY_IRON_GOLEM ,settings), new Item.Settings());
    public static final Item WAXED_IRON_GOLEM_EGG = register("waxed_iron_golem_spawn_egg", (settings) -> new SpawnEggItem(ModEntity.WAXED_IRON_GOLEM ,settings), new Item.Settings());

    public static final Item VERDIGRIS = register("verdigris", Item::new, new Item.Settings());
    public static final Item IRON_RUST = register("iron_rust", Item::new, new Item.Settings());

    public static final Item RUSTY_IRON_SWORD = register("rusty_iron_sword", Item::new, new Item.Settings().sword(IRON_CORROSION, 3F, -2.4F));
    public static final Item WAXED_IRON_SWORD = register("waxed_iron_sword", Item::new, new Item.Settings().sword(IRON_WAXED, 3F, -2.4F));

    public static final Item RUSTY_IRON_PICKAXE = register("rusty_iron_pickaxe", Item::new, new Item.Settings().pickaxe(IRON_CORROSION, 1F, -2.8F));
    public static final Item WAXED_IRON_PICKAXE = register("waxed_iron_pickaxe", Item::new, new Item.Settings().pickaxe(IRON_WAXED, 1F, -2.8F));

    public static final Item RUSTY_IRON_AXE = register("rusty_iron_axe", (settings) -> new AxeItem(IRON_CORROSION,6F,-3.1F, settings), new Item.Settings());
    public static final Item WAXED_IRON_AXE = register("waxed_iron_axe", (settings) -> new AxeItem(IRON_WAXED,6F,-3.1F, settings), new Item.Settings());

    public static final Item RUSTY_IRON_SHOVEL = register("rusty_iron_shovel", (settings) -> new ShovelItem(IRON_CORROSION,1.5F,-3F, settings), new Item.Settings());
    public static final Item WAXED_IRON_SHOVEL = register("waxed_iron_shovel", (settings) -> new ShovelItem(IRON_WAXED,1.5F,-3F, settings), new Item.Settings());

    public static final Item RUSTY_IRON_HOE = register("rusty_iron_hoe", (settings) -> new HoeItem(IRON_CORROSION,-2F,-1F, settings), new Item.Settings());
    public static final Item WAXED_IRON_HOE = register("waxed_iron_hoe", (settings) -> new HoeItem(IRON_WAXED,-2F,-1F, settings), new Item.Settings());

    public static final Item RUSTY_IRON_HELMET = register("rusty_iron_helmet", Item::new, new Item.Settings().armor(ARMOR_IRON_CORROSION, EquipmentType.HELMET).maxDamage(EquipmentType.HELMET.getMaxDamage(14)));
    public static final Item WAXED_IRON_HELMET = register("waxed_iron_helmet", Item::new, new Item.Settings().armor(ARMOR_IRON_WAXED, EquipmentType.HELMET).maxDamage(EquipmentType.HELMET.getMaxDamage(16)));

    public static final Item RUSTY_IRON_CHESTPLATE = register("rusty_iron_chestplate", Item::new, new Item.Settings().armor(ARMOR_IRON_CORROSION, EquipmentType.CHESTPLATE).maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(14)));
    public static final Item WAXED_IRON_CHESTPLATE = register("waxed_iron_chestplate", Item::new, new Item.Settings().armor(ARMOR_IRON_WAXED, EquipmentType.CHESTPLATE).maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(16)));

    public static final Item RUSTY_IRON_LEGGINGS = register("rusty_iron_leggings", Item::new, new Item.Settings().armor(ARMOR_IRON_CORROSION, EquipmentType.LEGGINGS).maxDamage(EquipmentType.LEGGINGS.getMaxDamage(14)));
    public static final Item WAXED_IRON_LEGGINGS = register("waxed_iron_leggings", Item::new, new Item.Settings().armor(ARMOR_IRON_WAXED, EquipmentType.LEGGINGS).maxDamage(EquipmentType.LEGGINGS.getMaxDamage(16)));

    public static final Item RUSTY_IRON_BOOTS = register("rusty_iron_boots", Item::new, new Item.Settings().armor(ARMOR_IRON_CORROSION, EquipmentType.BOOTS).maxDamage(EquipmentType.BOOTS.getMaxDamage(14)));
    public static final Item WAXED_IRON_BOOTS = register("waxed_iron_boots", Item::new, new Item.Settings().armor(ARMOR_IRON_WAXED, EquipmentType.BOOTS).maxDamage(EquipmentType.BOOTS.getMaxDamage(16)));

    public static void initialize() {
    }
}
