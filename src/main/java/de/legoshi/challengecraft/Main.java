package de.legoshi.challengecraft;

import de.legoshi.challengecraft.commands.DuelCommand;
import de.legoshi.challengecraft.commands.LevelCommand;
import de.legoshi.challengecraft.commands.TestCommand;
import de.legoshi.challengecraft.level.LevelManager;
import de.legoshi.challengecraft.listener.JoinListener;
import de.legoshi.challengecraft.listener.PlayerChatListener;
import de.legoshi.challengecraft.listener.QuitListener;
import de.legoshi.challengecraft.player.PlayerManager;
import de.legoshi.challengecraft.player.PlayerObject;
import de.legoshi.challengecraft.utils.FW;
import de.legoshi.challengecraft.utils.World;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static Main instance;

    public PlayerManager playerManager;
    public LevelManager levelManager;
    public Location spawn;

    public FW fw;

    @Override
    public void onEnable() {

        instance = this;

        this.fw = new FW("./plugins/configuration", "level.yaml");
        this.playerManager = new PlayerManager();
        this.levelManager = new LevelManager(fw);
        this.spawn = new Location(Bukkit.getWorld(World.STANDARD.world), -50.5, 1, 23.5);

        commandRegistration();
        listenerRegistration();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void commandRegistration() {
        getCommand("test").setExecutor(new TestCommand());
        getCommand("duel").setExecutor(new DuelCommand(playerManager, levelManager));
        getCommand("level").setExecutor(new LevelCommand());
    }

    private void listenerRegistration() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinListener(playerManager), this);
        pm.registerEvents(new QuitListener(playerManager), this);
        pm.registerEvents(new PlayerChatListener(), this);
    }

    public static Main getInstance() {
        return instance;
    }

}
