package me.krouda.soup;

import me.krouda.soup.listener.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Soup extends JavaPlugin {

    private static Soup instance;

    @Override
    public void onEnable() {
        instance = this;

        registerListeners();
    }

    public void registerListeners() {
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }

    @Override
    public void onDisable() {
    }

    public static Soup getInstance() {
        return instance;
    }
}
