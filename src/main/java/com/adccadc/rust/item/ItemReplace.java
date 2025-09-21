package com.adccadc.rust.item;

import com.adccadc.rust.OxidizeMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameRules;

import java.util.List;
import java.util.Random;

public class ItemReplace {

    private static void CopyEnchantments(ItemStack fromItem, ItemStack toItem) {
        if (fromItem.hasEnchantments()) {EnchantmentHelper.set(toItem, fromItem.getEnchantments());}
    }

    private static void CopyDamage(ItemStack fromItem, ItemStack toItem) {
        int Durability = fromItem.getMaxDamage() - toItem.getMaxDamage();
        if (fromItem.getDamage() > Durability) {toItem.setDamage(fromItem.getDamage() - Durability);}
    }

    private static void CopyCustomName(ItemStack fromItem, ItemStack toItem) {
        if (fromItem.contains(DataComponentTypes.CUSTOM_NAME)) {toItem.set(DataComponentTypes.CUSTOM_NAME, fromItem.get(DataComponentTypes.CUSTOM_NAME));}
    }

    @Deprecated
    public static void ChangeCorrosionToolWithAttribute(List<Item> ChangeItem, List<Item> ChangedItem, PlayerEntity player) {
        Random random = new Random();
        PlayerInventory inventory = player.getInventory();
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            int index = ChangeItem.indexOf(stack.getItem());
            if (index != -1) {
                if (random.nextDouble() < 0.95) {continue;}
                ItemStack change = new ItemStack(ChangedItem.get(index));

                CopyEnchantments(stack, change);
                CopyDamage(stack, change);
                CopyCustomName(stack, change);

                inventory.setStack(i, change);
            }
        }
    }

    public static void OxidizationItemWithAttribute(PlayerEntity player, Integer times) {
        Random random = new Random();
        PlayerInventory inventory = player.getInventory();
        for (int i = 0; i < times; i++) {
            if (random.nextDouble() < 0.05) {continue;}
            int index = random.nextInt(inventory.size() + 1);
            Item ChangedItem = (OxidizeMap.MOD_OXIDATION_LEVEL_INCREASES_ITEM.get()).get(inventory.getStack(index).getItem());
            if (ChangedItem != null) {
                ItemStack oldItem = inventory.getStack(index);
                ItemStack newItem = new ItemStack(ChangedItem);

                CopyEnchantments(oldItem, newItem);
                CopyDamage(oldItem, newItem);
                CopyCustomName(oldItem, newItem);

                inventory.setStack(index, newItem);
            }
        }
    }

}
