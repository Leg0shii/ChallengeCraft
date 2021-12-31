package de.legoshi.challengecraft.commands;

import de.legoshi.challengecraft.utils.BlocksCopy;
import de.legoshi.challengecraft.utils.Message;
import de.legoshi.challengecraft.utils.Prefix;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.isOp()) {
            double x1 = Double.parseDouble(args[0]);
            double y1 = Double.parseDouble(args[1]);
            double z1 = Double.parseDouble(args[2]);
            double x2 = Double.parseDouble(args[3]);
            double y2 = Double.parseDouble(args[4]);
            double z2 = Double.parseDouble(args[5]);
            double x3 = Double.parseDouble(args[6]);
            double y3 = Double.parseDouble(args[7]);
            double z3 = Double.parseDouble(args[8]);

            double[] p1 = {x1, y1, z1};
            double[] p2 = {x2, y2, z2};
            double[] p3 = {x3, y3, z3};

            BlocksCopy.copyAndPaste(p1, p2, p3);
        } else sender.sendMessage(Message.ERR_NO_PERMISSION.msg(Prefix.BAD));

        return false;
    }
}
