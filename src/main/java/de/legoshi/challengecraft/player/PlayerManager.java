package de.legoshi.challengecraft.player;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

@Getter
@Setter
public class PlayerManager {

    private HashMap<Player, PlayerObject> playerMap;

    public PlayerManager() {
        this.playerMap = new HashMap<>();
    }

    public void addPlayer(Player player) {
        PlayerObject playerObject = new PlayerObject(player);
        playerMap.put(player, playerObject);
    }

    public void removePlayer(Player player) {
        if(playerMap.get(player).getRunnableID() != -1) {
            Bukkit.getScheduler().cancelTask(playerMap.get(player).getRunnableID());
        }
        playerMap.remove(player);
    }

}
