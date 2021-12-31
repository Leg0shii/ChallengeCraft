package de.legoshi.challengecraft.escapebr;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class RandomItems {

    public static ItemStack[] generateRandomInventory() {

        int amount = (int) (Math.random() * 10) + 5;
        int matCount = Material.values().length;
        ItemStack[] mats = new ItemStack[amount];

        for(int i = 0; i < amount; i++) {
            ItemStack itemStack = new ItemStack(Material.values()[(int) (Math.random() * matCount)], (int) (Math.random()*15 + 1));
            mats[i] = itemStack;
        }
        return mats;
    }

}
