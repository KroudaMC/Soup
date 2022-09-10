package me.krouda.soup.config;

import me.krouda.soup.Soup;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.util.logging.Logger;

import static org.bukkit.Bukkit.getLogger;

public class SoupConfig {

    double spawnX = Double.parseDouble(Soup.getInstance().getConfig().getString("X"));
    double spawnY = Double.parseDouble(Soup.getInstance().getConfig().getString("Y"));
    double spawnZ = Double.parseDouble(Soup.getInstance().getConfig().getString("Z"));
    float spawnYaw = Float.parseFloat(Soup.getInstance().getConfig().getString("YAW"));
    float spawnPitch = Float.parseFloat(Soup.getInstance().getConfig().getString("PITCH"));
    World spawnWorld = Bukkit.getWorld("world");
    Location spawnLoc = new Location(spawnWorld, spawnX, spawnY, spawnZ, spawnYaw, spawnPitch);

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        player.teleport(spawnLoc);
    }

}
