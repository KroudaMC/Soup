package me.krouda.soup.cmds;

import me.krouda.soup.Soup;
import me.krouda.soup.annotation.Command;
import me.krouda.soup.annotation.Require;
import me.krouda.soup.annotation.Sender;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class SetSpawnCommand implements SoupCommand {

    @Command(name = "", desc = "Set Spawn Location")
    public void setSpawn(@Sender Player sender) {
        if (sender.hasPermission("spawn.spawn")) {
            Location location = sender.getLocation();
            Soup.getInstance().getConfig().set("X", location.getX());
            Soup.getInstance().getConfig().set("Y", location.getY());
            Soup.getInstance().getConfig().set("Z", location.getZ());
            Soup.getInstance().getConfig().set("YAW", location.getYaw());
            Soup.getInstance().getConfig().set("PITCH", location.getPitch());

            Soup.getInstance().saveDefaultConfig();
            sender.sendMessage(ChatColor.GREEN + "Set spawned.");
        }
    }

    @Override
    public String getCommandName() {
        return "setspawn";
    }

    @Override
    public String[] getAliases() {
        return new String[]{"spawnset", "setlobby"};
    }
}
