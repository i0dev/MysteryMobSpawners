package com.i0dev.util;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerUtils {
    public static void removeOneItemInHandFromPlayer(Player player) {
        if (player.getInventory().getItemInHand().getAmount() == 1) {
            player.getInventory().setItemInHand(null);
        } else {
            player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - 1);
        }
    }

    public static boolean areSameLocations(Location loc1, Location loc2) {
        if (loc1.getWorld().getName().equalsIgnoreCase(loc2.getWorld().getName())
                && loc1.getBlockX() == loc2.getBlockX()
                && loc1.getBlockY() == loc2.getBlockY()
                && loc1.getBlockZ() == loc2.getBlockZ())
            return true;
        return false;
    }

    public static void givePlayerItem(Player player, ItemStack item, int amount) {
        if (player.getInventory().firstEmpty() == -1) {
            for (int i = 0; i < amount; i++) {
                player.getWorld().dropItem(player.getLocation(), item);
            }
        } else {
            for (int i = 0; i < amount; i++) {
                player.getInventory().addItem(item);
            }
        }
    }
}
