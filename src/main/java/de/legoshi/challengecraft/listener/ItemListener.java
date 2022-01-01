package de.legoshi.challengecraft.listener;

import de.legoshi.challengecraft.level.LevelManager;
import de.legoshi.challengecraft.player.PlayerManager;
import de.legoshi.challengecraft.player.PlayerObject;
import de.legoshi.challengecraft.utils.BlocksCopy;
import de.legoshi.challengecraft.utils.Message;
import de.legoshi.challengecraft.utils.Prefix;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ItemListener implements Listener {

    private PlayerManager playerManager;
    private LevelManager levelManager;

    public ItemListener(PlayerManager playerManager, LevelManager levelManager) {
        this.playerManager = playerManager;
        this.levelManager = levelManager;
    }

    @EventHandler
    public void onItemClick(PlayerInteractEvent event) {

        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(event.hasItem()) {
                PlayerObject playerObject = playerManager.getPlayerMap().get(event.getPlayer());
                if(event.getItem().getType().equals(Material.EMERALD)) {
                    playerObject.getPlayer().sendMessage(Message.ERR_PUZZLE.msg(Prefix.GOOD));
                    playerObject.getPlayer().sendMessage(Message.ERR_DUEL.msg(Prefix.GOOD));
                } else if(event.getItem().getType().equals(Material.RED_DYE)) {
                    playerObject.getPlayer().getInventory().clear();
                    levelManager.loadLevel(playerObject.getLevel().getName(), playerObject.getPlayer());
                    event.getPlayer().sendMessage("Reset...");
                } else if(event.getItem().getType().equals(Material.BLACK_DYE)) {
                    playerObject.setInEBMap(false);
                    playerObject.setInDuel(false);
                }
            }
        } else if(event.getAction() == Action.PHYSICAL) {
            if(event.getClickedBlock().getType().equals(Material.HEAVY_WEIGHTED_PRESSURE_PLATE)) {
                PlayerObject playerObject = playerManager.getPlayerMap().get(event.getPlayer());
                playerObject.setCompleted(true);
                playerObject.setInEBMap(false);
            }
        }

    }

}
