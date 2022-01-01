package de.legoshi.challengecraft.escapebr;

import de.legoshi.challengecraft.Main;
import de.legoshi.challengecraft.level.Level;
import de.legoshi.challengecraft.level.LevelManager;
import de.legoshi.challengecraft.player.PlayerInventory;
import de.legoshi.challengecraft.player.PlayerObject;
import de.legoshi.challengecraft.utils.FW;
import de.legoshi.challengecraft.utils.Message;
import de.legoshi.challengecraft.utils.Prefix;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.TimerTask;

public class EscapeBedrockDuel extends TimerTask {

    private long started;
    private PlayerObject po1;
    private PlayerObject po2;
    private Player player1;
    private Player player2;

    private Level level;
    private ItemStack[] inventory;

    private LevelManager levelManager;

    public EscapeBedrockDuel(PlayerObject po1, PlayerObject po2, LevelManager levelManager) {
        this.started = System.currentTimeMillis();
        this.po1 = po1;
        this.po2 = po2;
        this.player1 = po1.getPlayer();
        this.player2 = po2.getPlayer();

        this.inventory = RandomItems.generateRandomInventory();
        FW fw = new FW("./plugins/configuration", "level.yaml");

        this.level = new Level(fw, "duel");
        this.levelManager = levelManager;

        loadPlayer(po1);
        loadPlayer(po2);
    }

    private void loadPlayer(PlayerObject playerObject) {
        Player player = playerObject.getPlayer();
        levelManager.addLevel("duel", player);
        levelManager.loadLevel("duel", player);
        playerObject.setInDuel(true);
        player.teleport(level.getSpawn());
        PlayerInventory.setInventoryDuel(player);
        playerObject.setLevel(level);
        player.getInventory().setContents(inventory);
        player.sendMessage(Message.INFO_DUEL_STARTED.msg(Prefix.GOOD));
        player.setBedSpawnLocation(level.getSpawn());
    }

    private void unloadPlayer(PlayerObject playerObject) {
        Player player = playerObject.getPlayer();
        levelManager.removeLevel("duel", player);
        playerObject.setInDuel(false);
        player.teleport(Main.getInstance().spawn);
        PlayerInventory.setInventorySpawn(player);
        player.setBedSpawnLocation(Main.getInstance().spawn);
    }

    @Override
    public void run() {
        if(!(Math.abs(started - System.currentTimeMillis()) <= 300*1000)
                || !player1.isOnline() || !player2.isOnline() || po1.isSpawn() || po2.isSpawn()) {
            checkWinCondition();
            unloadPlayer(po1);
            unloadPlayer(po2);
            cancel();
        } else {
            TextComponent textComponent = new TextComponent("§6Time left: §e" + (300 - Math.abs(started - System.currentTimeMillis())/1000));
            player1.spigot().sendMessage(ChatMessageType.ACTION_BAR, textComponent);
            player2.spigot().sendMessage(ChatMessageType.ACTION_BAR, textComponent);
        }
    }

    private void checkWinCondition() {
        double height1 = player1.getLocation().getY();
        double height2 = player2.getLocation().getY();

        String playerName1 = player1.getName();
        String playerName2 = player2.getName();

        if(height1 < height2) Bukkit.broadcastMessage(Message.INFO_DUEL_WINNER.msg(Prefix.GOOD,playerName2, "" + height2));
        else if(height2 < height1) Bukkit.broadcastMessage(Message.INFO_DUEL_WINNER.msg(Prefix.GOOD,playerName1, "" + height1));
        else if(height2 == height1) Bukkit.broadcastMessage(Message.INFO_DUEL_TIE.msg(Prefix.GOOD,playerName1, playerName2, "" + height1));
        else Bukkit.broadcastMessage(Message.ERR_PLAYER_LEFT.msg(Prefix.BAD));
    }
}
