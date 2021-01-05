package com.i0dev.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CreateItemStack {
    public static ItemStack createItem(String m, int setShort, String itemName, List<String> lore, boolean glowing) {
        ItemStack itemStack = new ItemStack(Material.getMaterial(m), 1, (short) setShort);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', itemName));
        if (glowing) {
            itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        }

        if (lore != null) {
            List<String> loreList = new ArrayList();
            Iterator var9 = lore.iterator();

            while (var9.hasNext()) {
                String list = (String) var9.next();
                loreList.add(ChatColor.translateAlternateColorCodes('&', list));
            }

            itemMeta.setLore(loreList);
        }

        itemMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
