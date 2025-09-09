package com.adccadc.rust.item;

import com.adccadc.rust.Rust;
import com.adccadc.rust.entity.ModEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ToolMaterial;
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
    public static final Item RUSTY_IRON_GOLEM_EGG = register("rusty_iron_golem_spawn_egg", RustyIronGolemEgg::new, new Item.Settings());
    public static final Item WAXED_IRON_GOLEM_EGG = register("waxed_iron_golem_spawn_egg", WaxedIronGolemEgg::new, new Item.Settings());

    public static final Item VERDIGRIS = register("verdigris", Item::new, new Item.Settings());
    public static final Item IRON_RUST = register("iron_rust", Item::new, new Item.Settings());

    public static final Item RUSTY_IRON_SWORD = register("rusty_iron_sword", Item::new, new Item.Settings().sword(IRON_CORROSION, 3F, -2.4F));
    public static final Item WAXED_IRON_SWORD = register("waxed_iron_sword", Item::new, new Item.Settings().sword(IRON_WAXED, 3F, -2.4F));

    public static final Item RUSTY_IRON_PICKAXE = register("rusty_iron_pickaxe", Item::new, new Item.Settings().pickaxe(IRON_CORROSION, 1F, -2.8F));
    public static final Item WAXED_IRON_PICKAXE = register("waxed_iron_pickaxe", Item::new, new Item.Settings().pickaxe(IRON_WAXED, 1F, -2.8F));

    public static final Item RUSTY_IRON_AXE = register("rusty_iron_axe", CustomRustyAxeItem::new, new Item.Settings());
    public static final Item WAXED_IRON_AXE = register("waxed_iron_axe", CustomWaxedAxeItem::new, new Item.Settings());

    public static final Item RUSTY_IRON_SHOVEL = register("rusty_iron_shovel", CustomRustyShovelItem::new, new Item.Settings());
    public static final Item WAXED_IRON_SHOVEL = register("waxed_iron_shovel", CustomWaxedShovelItem::new, new Item.Settings());

    public static final Item RUSTY_IRON_HOE = register("rusty_iron_hoe", CustomRustyHoeItem::new, new Item.Settings());
    public static final Item WAXED_IRON_HOE = register("waxed_iron_hoe", CustomWaxedHoeItem::new, new Item.Settings());

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
