package de.legoshi.challengecraft.listener;

import de.legoshi.challengecraft.player.PlayerManager;
import de.legoshi.challengecraft.utils.Message;
import de.legoshi.challengecraft.utils.Prefix;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    public PlayerManager playerManager;

    public QuitListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();
        player.getInventory().clear();
        event.setQuitMessage(Message.PLAYER_LEAVE.msg(Prefix.BAD, player.getName()));
        playerManager.removePlayer(player);

    }

}
