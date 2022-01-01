package de.legoshi.challengecraft.level;

import de.legoshi.challengecraft.player.PlayerObject;
import de.legoshi.challengecraft.utils.BlocksCopy;
import de.legoshi.challengecraft.utils.FW;
import de.legoshi.challengecraft.utils.World;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

@Getter @Setter
public class LevelManager {

    public HashMap<String, HashMap<Integer, Player>> levelHashMap;
    private FW fw;

    public LevelManager(FW fw) {
        this.levelHashMap = new HashMap<>();
        this.fw = fw;
    }

    public void addLevel(String levelName, Player player) {
        if(!levelHashMap.containsKey(levelName)) {
            levelHashMap.put(levelName, new HashMap<>());
        }

        int temp = 0;
        Integer[] positions = (Integer[]) levelHashMap.get(levelName).keySet().stream().sorted().toArray();

        for(int i : positions) {
            if((i - temp) > 1) {
                temp = temp + 1;
                levelHashMap.get(levelName).put(temp, player);
                return;
            }
            temp = i;
        }
        levelHashMap.get(levelName).put(temp, player);
    }

    public void loadLevel(String levelName, Player player) {
        Level level = new Level(fw, levelName);
        double[] p1 = {level.getX1(), level.getY1(), level.getZ1()};
        double[] p2 = {level.getX2(), level.getY2(), level.getZ2()};
        BlocksCopy.copyAndPaste(p1, p2, shiftCopy(p1, levelName, player));
    }

    public void removeLevel(String levelName, Player player) {
        Integer[] positions = (Integer[]) levelHashMap.get(levelName).keySet().stream().sorted().toArray();
        for(int i : positions) {
            if(player.equals(levelHashMap.get(levelName).get(i))) {
                levelHashMap.get(levelName).remove(i);
                return;
            }
        }
    }

    private double[] shiftCopy(double[] point, String levelName, Player player) {
        double[] shiftedPoint = {
                point[0],
                point[1],
                point[2]+55*getOffset(levelName, player)
        };
        return shiftedPoint;
    }

    private Location shiftSpawn(double[] spawn, String levelName, Player player) {
        Location location = new Location(
                Bukkit.getWorld(World.STANDARD.world),
                spawn[0],
                spawn[1],
                spawn[2]+55*getOffset(levelName, player)
        );
        return location;
    }

    private int getOffset(String levelName, Player player) {
        Integer[] positions = (Integer[]) levelHashMap.get(levelName).keySet().stream().sorted().toArray();
        for(int i : positions) {
            if(player.equals(levelHashMap.get(levelName).get(i))) {
                return i;
            }
        }
        return -1;
    }

}
