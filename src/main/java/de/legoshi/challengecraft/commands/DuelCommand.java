package de.legoshi.challengecraft.commands;

import de.legoshi.challengecraft.escapebr.EscapeBedrockDuel;
import de.legoshi.challengecraft.level.LevelManager;
import de.legoshi.challengecraft.player.PlayerManager;
import de.legoshi.challengecraft.player.PlayerObject;
import de.legoshi.challengecraft.utils.Message;
import de.legoshi.challengecraft.utils.Prefix;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DuelCommand implements CommandExecutor {

    private PlayerManager playerManager;
    private LevelManager levelManager;

    public DuelCommand(PlayerManager playerManager, LevelManager levelManager) {
        this.playerManager = playerManager;
        this.levelManager = levelManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = ((Player) sender);
        PlayerObject playerObject = playerManager.getPlayerMap().get(player);

        if(!(args.length == 2)) {
            sender.sendMessage(Message.ERR_DUEL.msg(Prefix.BAD));
            return false;
        }

        if(args[1].equals(sender.getName())) {
            sender.sendMessage(Message.ERR_DUEL_CANT_INV_YOURSELF.msg(Prefix.BAD));
            return false;
        }

        Player invited = null;

        for(Player all : Bukkit.getOnlinePlayers()) {
            if(all.getName().equals(args[1])) {
                invited = Bukkit.getPlayer(args[1]);
            }
        }

        if(invited == null) {
            sender.sendMessage(Message.ERR_PLAYER_NOT_ONLINE.msg(Prefix.BAD), args[1]);
            return false;
        }

        if(args[0].equals("invite")) {
            sender.sendMessage(Message.SUCC_DUEL_INVITE.msg(Prefix.GOOD, args[1]));
            invited.sendMessage(Message.SUCC_DUEL_INVITE_OTHER.msg(Prefix.GOOD, sender.getName()));
            playerManager.getPlayerMap().get(invited).setDuelPlayer(playerObject);
            return true;
        } else if(args[0].equals("accept")) {
            if(args[1].equals(playerObject.getDuelPlayer().getPlayer().getName())) {
                if(playerObject.getDuelPlayer() != null) {
                    sender.sendMessage(Message.SUCC_DUEL_ACCEPT.msg(Prefix.GOOD, sender.getName()));
                    // starts new game
                    new EscapeBedrockDuel(playerObject, playerObject.getDuelPlayer(),levelManager).run();
                }
            } else sender.sendMessage(Message.ERR_DUEL_NOT_INVITED.msg(Prefix.GOOD, args[1]));
            return true;
        }
        sender.sendMessage(Message.ERR_DUEL.msg(Prefix.BAD));
        return false;
    }
}
