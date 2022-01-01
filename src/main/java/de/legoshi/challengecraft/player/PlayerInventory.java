package de.legoshi.challengecraft.player;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerInventory {

    public static void setInventorySpawn(Player player) {
        player.getInventory().clear();
        ItemStack emerald = new ItemStack(Material.EMERALD, 1);
        ItemMeta im = emerald.getItemMeta();
        im.setDisplayName("§aInfo");
        emerald.setItemMeta(im);
        player.getInventory().setItem(4, emerald);
    }

    public static void setInventoryDuel(Player player) {
        player.getInventory().clear();

        ItemStack blackDye = new ItemStack(Material.BLACK_DYE, 1);
        ItemMeta im2 = blackDye.getItemMeta();
        im2.setDisplayName("§aReset-Level");
        blackDye.setItemMeta(im2);

        player.getInventory().setItem(8, blackDye);
    }

    public static void setInventoryEB(Player player) {
        ItemStack redDye = new ItemStack(Material.RED_DYE, 1);
        ItemMeta im = redDye.getItemMeta();
        im.setDisplayName("§aReset-Level");
        redDye.setItemMeta(im);

        ItemStack blackDye = new ItemStack(Material.BLACK_DYE, 1);
        ItemMeta im2 = blackDye.getItemMeta();
        im2.setDisplayName("§aReset-Level");
        blackDye.setItemMeta(im2);

        player.getInventory().setItem(8, blackDye);
    }

}
