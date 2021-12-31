package de.legoshi.challengecraft.player;

import de.legoshi.challengecraft.level.Level;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@Getter @Setter
public class PlayerObject {

    private Player player;
    private PlayerObject duelPlayer;
    private Location duelSpawn;
    private int runnableID;

    public PlayerObject(Player player) {
        this.player = player;
        this.runnableID = -1;
    }

}
