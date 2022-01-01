package de.legoshi.challengecraft.commands;

import de.legoshi.challengecraft.escapebr.EscapeBedrock;
import de.legoshi.challengecraft.level.Level;
import de.legoshi.challengecraft.level.LevelManager;
import de.legoshi.challengecraft.player.PlayerManager;
import de.legoshi.challengecraft.player.PlayerObject;
import de.legoshi.challengecraft.utils.FW;
import de.legoshi.challengecraft.utils.Message;
import de.legoshi.challengecraft.utils.Prefix;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

public class PuzzleCommand implements CommandExecutor {

    private LevelManager levelManager;
    private PlayerManager playerManager;

    public PuzzleCommand(PlayerManager playerManager, LevelManager levelManager) {
        this.levelManager = levelManager;
        this.playerManager = playerManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(args.length > 1)) {
            sender.sendMessage(Message.ERR_PUZZLE.msg(Prefix.BAD));
            return false;
        }

        if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if(args[0].equals("list")) {
            listLevel(sender);
            return false;
        } else if(args[0].equals("join")) {
            PlayerObject playerObject = playerManager.getPlayerMap().get(player);
            new EscapeBedrock(playerObject, args[1], levelManager).run();
            return false;
        } else {
            sender.sendMessage(Message.ERR_PUZZLE.msg(Prefix.BAD));
            return false;
        }
    }

    private void listLevel(CommandSender sender) {
        FW fw = new FW("./plugins/configuration", "level.yaml");
        Set<String> keys = fw.getKeys(true);
        sender.sendMessage(Message.INFO_PUZZLE_LIST.msg(Prefix.GOOD));
        for(String level : keys) {
            sender.sendMessage("- " + level);
        }
    }

}
