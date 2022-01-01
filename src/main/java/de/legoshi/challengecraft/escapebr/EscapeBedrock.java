package de.legoshi.challengecraft.escapebr;

import de.legoshi.challengecraft.Main;
import de.legoshi.challengecraft.level.Level;
import de.legoshi.challengecraft.level.LevelManager;
import de.legoshi.challengecraft.player.PlayerInventory;
import de.legoshi.challengecraft.player.PlayerObject;
import de.legoshi.challengecraft.utils.FW;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class EscapeBedrock extends TimerTask {

    private long started;
    private PlayerObject po;
    private Player player;

    private Level level;

    private LevelManager levelManager;

    public EscapeBedrock(PlayerObject playerObject, String levelName, LevelManager levelManager) {
        this.started = System.currentTimeMillis();
        this.po = playerObject;
        this.player = playerObject.getPlayer();

        FW fw = new FW("./plugins/configuration", "level.yaml");
        this.level = new Level(fw, levelName);
        this.levelManager = levelManager;

        loadPlayer();
    }

    @Override
    public void run() {
        if(!player.isOnline() || po.isSpawn()) {
            if(po.isCompleted()) {
                Bukkit.broadcastMessage("§4" + player.getName() + "§6 finished " + "§4" + level.getName());
            }
            unloadPlayer();
            cancel();
        } else {
            TextComponent textComponent = new TextComponent("§6Time: §e" + (started - System.currentTimeMillis())/1000);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, textComponent);
        }
    }

    private void loadPlayer() {
        levelManager.addLevel(level.getName(), player);
        levelManager.loadLevel(level.getName(), player);
        po.setInEBMap(true);
        PlayerInventory.setInventoryEB(player);
        po.setLevel(level);
        player.teleport(level.getSpawn());
        player.setBedSpawnLocation(level.getSpawn());
    }

    private void unloadPlayer() {
        levelManager.removeLevel(level.getName(), player);
        po.setInEBMap(false);
        po.setCompleted(false);
        PlayerInventory.setInventorySpawn(player);
        player.teleport(Main.getInstance().spawn);
        player.setBedSpawnLocation(Main.getInstance().spawn);
    }

}
