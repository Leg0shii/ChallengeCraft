package de.legoshi.challengecraft.player;

import de.legoshi.challengecraft.level.Level;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@Getter
@Setter
public class PlayerObject {

    private Player player;
    private PlayerObject duelPlayer;
    private Level level;

    private boolean inDuel;
    private boolean inEBMap;
    private boolean completed;

    public PlayerObject(Player player) {
        this.inDuel = false;
        this.inEBMap = false;
        this.completed = false;
        this.player = player;
    }

    public boolean isSpawn() {
        return !inEBMap && !inDuel;
    }

}
