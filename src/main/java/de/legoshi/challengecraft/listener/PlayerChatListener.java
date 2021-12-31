package de.legoshi.challengecraft.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void onChatMessage(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();
        String rank = "§6[" + "§e-" + "§6]§7 "; // colorize it
        String message = event.getMessage();
        event.setCancelled(true);

        Bukkit.broadcastMessage(rank + player.getName() + ": " + message);

    }

}
