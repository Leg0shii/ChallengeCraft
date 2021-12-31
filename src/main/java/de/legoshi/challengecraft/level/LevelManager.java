package de.legoshi.challengecraft.level;

import de.legoshi.challengecraft.player.PlayerObject;
import de.legoshi.challengecraft.utils.BlocksCopy;
import de.legoshi.challengecraft.utils.FW;
import de.legoshi.challengecraft.utils.World;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Arrays;
import java.util.HashMap;

@Getter @Setter
public class LevelManager {

    public HashMap<String, Integer> levelHashMap;
    private FW fw;

    public LevelManager(FW fw) {
        this.levelHashMap = new HashMap<>();
        this.fw = fw;
    }

    public void addLevel(String levelName, PlayerObject playerObject) {
        if(levelHashMap.containsKey(levelName)) {
            levelHashMap.put(levelName, levelHashMap.get(levelName)+1);
        } else {
            levelHashMap.put(levelName, 1);
        }
        Level level = new Level(fw, levelName);
        double[] p1 = {level.getX1(), level.getY1(), level.getZ1()};
        double[] p2 = {level.getX2(), level.getY2(), level.getZ2()};
        double[] spawn = {level.getSpawnX(), level.getSpawnY(), level.getSpawnZ()};
        BlocksCopy.copyAndPaste(p1, p2, shiftCopy(p1, levelName));
        System.out.println(Arrays.toString(p1));
        System.out.println(Arrays.toString(p2));
        System.out.println(Arrays.toString(shiftCopy(p1, levelName)));
        playerObject.setDuelSpawn(shiftSpawn(spawn, levelName));
        System.out.println("Duel Spawn " + playerObject.getDuelSpawn());
    }

    public void removeLevel(String levelName) {
        if(levelHashMap.containsKey(levelName)) {
            levelHashMap.put(levelName, levelHashMap.get(levelName)-1);
        }
    }

    private double[] shiftCopy(double[] point, String levelName) {
        double[] shiftedPoint = {
                point[0],
                point[1],
                point[2]+(57*levelHashMap.get(levelName))
        };
        return shiftedPoint;
    }

    private Location shiftSpawn(double[] spawn, String levelName) {
        Location location = new Location(
                Bukkit.getWorld(World.STANDARD.world),
                spawn[0],
                spawn[1],
                spawn[2]+(57*levelHashMap.get(levelName)));
        return location;
    }

}
