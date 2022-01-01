package de.legoshi.challengecraft.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class BlocksCopy {

    public static void copyAndPaste(double[] sourceP1, double[] sourceP2, double[] destP) {

        double xDown = sourceP1[0];
        double yDown = sourceP1[1];
        double zDown = sourceP1[2];

        double xUp = sourceP2[0];
        double yUp = sourceP2[1];
        double zUp = sourceP2[2];

        if(xDown > xUp) {
            double temp = xDown;
            xDown = xUp;
            xUp = temp;
        }

        if(zDown > zUp) {
            double temp = zDown;
            zDown = zUp;
            zUp = temp;
        }

        if(yDown > yUp) {
            double temp = yDown;
            yDown = yUp;
            yUp = temp;
        }

        for(int x = (int) Math.floor(xDown); x <= (int) Math.floor(xUp); x++) {
            for(int y = (int) Math.floor(yDown); y <= (int) Math.floor(yUp); y++) {
                for(int z = (int) Math.floor(zDown); z <= (int) Math.floor(zUp); z++) {

                    int xNew = (int) ((x - xDown) + destP[0]);
                    int yNew = (int) ((y - yDown) + destP[1]);
                    int zNew = (int) ((z - zDown) + destP[2]);

                    Block block = Bukkit.getServer().getWorld(World.STANDARD.world).getBlockAt(x, y, z);
                    Bukkit.getServer().getWorld(World.STANDARD.world).setBlockData(xNew, yNew, zNew, block.getBlockData());
                }
            }
        }

    }

}
