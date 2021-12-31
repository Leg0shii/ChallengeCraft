package de.legoshi.challengecraft.commands;

import de.legoshi.challengecraft.utils.Message;
import de.legoshi.challengecraft.utils.Prefix;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PuzzleCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(args.length > 1)) {
            sender.sendMessage(Message.ERR_PUZZLE.msg(Prefix.BAD));
            return false;
        }

        return false;
    }
}
