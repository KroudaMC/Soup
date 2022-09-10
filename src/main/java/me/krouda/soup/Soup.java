package me.krouda.soup;

import me.krouda.soup.cmds.SetSpawnCommand;
import me.krouda.soup.cmds.SpawnCommand;
import me.krouda.soup.listener.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Soup extends JavaPlugin {

    private static Soup instance;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("Soup PvP Plugin Enabled.");
        getConfig().options().copyDefaults();
        this.saveDefaultConfig();
        registerListeners();
        registerCommands();
    }

    public void registerListeners() {
        Bukkit.getServer().getPluginManager().registerEvents(new BasicListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BasicListener(), this);

        Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new RefillListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new KitListener(), this);
    }

    public void registerCommands() {
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
    }

    @Override
    public void onDisable() {
    }

    public static Soup getInstance() {
        return instance;
    }
}
