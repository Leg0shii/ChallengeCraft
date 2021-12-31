package de.legoshi.challengecraft.listener;

import de.legoshi.challengecraft.player.PlayerManager;
import de.legoshi.challengecraft.player.PlayerObject;
import de.legoshi.challengecraft.utils.Message;
import de.legoshi.challengecraft.utils.Prefix;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    public PlayerManager playerManager;

    public JoinListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        event.setJoinMessage(Message.PLAYER_JOIN.msg(Prefix.GOOD, player.getName()));
        event.getPlayer().sendMessage("§cWrite /duel invite <player> to invite a player to your duel!");
        event.getPlayer().sendMessage("§cWrite /duel accept <player> to accept a duel!");
        playerManager.addPlayer(player);

    }

}
