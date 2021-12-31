package de.legoshi.challengecraft.commands;

import de.legoshi.challengecraft.utils.FW;
import de.legoshi.challengecraft.utils.Message;
import de.legoshi.challengecraft.utils.Prefix;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class LevelCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.isOp()) {
            switch (args[0]) {
                case "add":
                    addLevel(args);
                    return false;
                case "remove":
                    return false;
            }
        }
        sender.sendMessage(Message.ERR_NO_PERMISSION.msg(Prefix.BAD));
        return false;
    }

    private void addLevel(String[] args) {

        String levelName = args[1];
        double x1 = Double.parseDouble(args[2]);
        double y1 = Double.parseDouble(args[3]);
        double z1 = Double.parseDouble(args[4]);
        double x2 = Double.parseDouble(args[5]);
        double y2 = Double.parseDouble(args[6]);
        double z2 = Double.parseDouble(args[7]);
        double x3 = Double.parseDouble(args[8]);
        double y3 = Double.parseDouble(args[9]);
        double z3 = Double.parseDouble(args[10]);

        FW fw = new FW("./plugins/configuration", "level.yaml");
        fw.setValue(levelName, levelName);
        fw.setValue(levelName + ".x1", x1);
        fw.setValue(levelName + ".y1", y1);
        fw.setValue(levelName + ".z1", z1);
        fw.setValue(levelName + ".x2", x2);
        fw.setValue(levelName + ".y2", y2);
        fw.setValue(levelName + ".z2", z2);
        fw.setValue(levelName + ".spawnX", x3);
        fw.setValue(levelName + ".spawnY", y3);
        fw.setValue(levelName + ".spawnZ", z3);
        fw.save();

    }

}
