package de.legoshi.challengecraft.escapebr;

import com.google.common.util.concurrent.AbstractScheduledService;
import de.legoshi.challengecraft.Main;
import de.legoshi.challengecraft.level.LevelManager;
import de.legoshi.challengecraft.player.PlayerManager;
import de.legoshi.challengecraft.player.PlayerObject;
import de.legoshi.challengecraft.utils.Message;
import de.legoshi.challengecraft.utils.Prefix;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

public class EscapeBedrock {

    private PlayerManager playerManager;
    private LevelManager levelManager;

    public EscapeBedrock(PlayerManager playerManager, LevelManager levelManager) {
        this.playerManager = playerManager;
        this.levelManager = levelManager;
    }

    public void startGame(PlayerObject player1, PlayerObject player2) {
        levelManager.addLevel("duel", player1);
        levelManager.addLevel("duel", player2);

        Player realPlayer1 = player1.getPlayer();
        Player realPlayer2 = player2.getPlayer();

        realPlayer1.teleport(player1.getDuelSpawn());
        System.out.println(player1.getDuelSpawn());
        realPlayer2.teleport(player2.getDuelSpawn());
        System.out.println(player2.getDuelSpawn());

        realPlayer1.sendMessage(Message.INFO_DUEL_STARTED.msg(Prefix.GOOD));
        realPlayer2.sendMessage(Message.INFO_DUEL_STARTED.msg(Prefix.GOOD));

        realPlayer1.setBedSpawnLocation(player1.getDuelSpawn());
        realPlayer2.setBedSpawnLocation(player2.getDuelSpawn());

        runGame(player1, player2);
    }

    private void runGame(PlayerObject player1, PlayerObject player2) {
        long time = System.currentTimeMillis();
        Player realPlayer1 = player1.getPlayer();
        Player realPlayer2 = player2.getPlayer();
        Main main = Main.getInstance();
        ItemStack[] mats = RandomItems.generateRandomInventory();
        realPlayer1.getInventory().setContents(mats);
        realPlayer2.getInventory().setContents(mats);

        int id = Bukkit.getScheduler().runTaskTimer(Main.getInstance(), () -> {
            // System.out.println("RUNNING " + Math.abs(time - System.nanoTime()));
            if(!(Math.abs(time - System.currentTimeMillis()) <= 300*1000) || !realPlayer1.isOnline() || !realPlayer2.isOnline()) {
                double height1 = realPlayer1.getLocation().getY();
                double height2 = realPlayer2.getLocation().getY();

                if(height1 < height2) {
                    Bukkit.broadcastMessage(Message.INFO_DUEL_WINNER.msg(Prefix.GOOD, realPlayer2.getDisplayName(), "" + height2));
                } else if(height2 < height1) {
                    Bukkit.broadcastMessage(Message.INFO_DUEL_WINNER.msg(Prefix.GOOD, realPlayer1.getDisplayName(), "" + height1));
                } else if(height2 == height1) {
                    Bukkit.broadcastMessage(Message.INFO_DUEL_TIE.msg(
                            Prefix.GOOD,
                            realPlayer1.getDisplayName(),
                            realPlayer2.getDisplayName(),
                            String.valueOf(height1))
                    );
                } else {
                    Bukkit.broadcastMessage(Message.ERR_PLAYER_LEFT.msg(Prefix.BAD));
                }
                realPlayer1.setBedSpawnLocation(main.spawn);
                realPlayer2.setBedSpawnLocation(main.spawn);
                realPlayer1.teleport(main.spawn);
                realPlayer2.teleport(main.spawn);
                levelManager.removeLevel("duel");
                levelManager.removeLevel("duel");
                player1.setDuelPlayer(null);
                player2.setDuelPlayer(null);
                Bukkit.getScheduler().cancelTask(player1.getRunnableID());
                Bukkit.getScheduler().cancelTask(player2.getRunnableID());
                player1.setRunnableID(-1);
                player2.setRunnableID(-1);
                realPlayer1.getInventory().clear();
                realPlayer2.getInventory().clear();
            } else {
                realPlayer1.spigot().sendMessage(
                        ChatMessageType.ACTION_BAR,
                        new TextComponent("§6Time left: §e" + (300 - Math.abs(time - System.currentTimeMillis())/1000))
                );
                realPlayer2.spigot().sendMessage(
                        ChatMessageType.ACTION_BAR,
                        new TextComponent("§6Time left: §e" + (300 - Math.abs(time - System.currentTimeMillis())/1000))
                );
            }
        }, 20, 20).getTaskId();
        player1.setRunnableID(id);
    }

}
