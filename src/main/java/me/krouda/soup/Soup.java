package me.krouda.soup;

import org.bukkit.plugin.java.JavaPlugin;

public class Soup extends JavaPlugin {

    private static Soup instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {
    }

    public static Soup getInstance() {
        return instance;
    }
}
