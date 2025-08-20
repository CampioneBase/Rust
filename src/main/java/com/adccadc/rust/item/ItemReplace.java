package com.adccadc.rust.item;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.Random;

public class ItemReplace {
    public static void ChangeCorrosionToolWithAttribute(List<Item> ChangeItem, List<Item> ChangedItem, PlayerEntity player) {
        Random random = new Random();
        PlayerInventory inventory = player.getInventory();
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            int index = ChangeItem.indexOf(stack.getItem());
            if (index != -1) {
                if (random.nextDouble() < 0.95) {continue;}
                ItemStack change = new ItemStack(ChangedItem.get(index));
                // 附魔
                if (stack.hasEnchantments()) {
                    ItemEnchantmentsComponent enchantments = stack.getEnchantments();
                    EnchantmentHelper.set(change, enchantments);
                }
                // 耐久
                int Durability = stack.getMaxDamage() - change.getMaxDamage();
                if (stack.getDamage() > Durability) {
                    change.setDamage(stack.getDamage() - Durability);
                }
                // 自定义名称
                if (stack.contains(DataComponentTypes.CUSTOM_NAME)) {
                    change.set(DataComponentTypes.CUSTOM_NAME, stack.get(DataComponentTypes.CUSTOM_NAME));
                }
                inventory.setStack(i, change);
            }
        }
    }
}
