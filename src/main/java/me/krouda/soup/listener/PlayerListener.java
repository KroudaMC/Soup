package me.krouda.soup.listener;

import me.krouda.soup.Soup;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        Player player = event.getPlayer();
        World spawnWorld = (World) Soup.getInstance().getConfig().get("WORLD.SPAWN.WORLD");
        Double spawnX = (Double) Soup.getInstance().getConfig().get("WORLD.SPAWN.X");
        Double spawnY = (Double) Soup.getInstance().getConfig().get("WORLD.SPAWN.Y");
        Double spawnZ = (Double) Soup.getInstance().getConfig().get("WORLD.SPAWN.Z");
        Float spawnYaw = (Float) Soup.getInstance().getConfig().get("WORLD.SPAWN.YAW");
        Float spawnPitch = (Float) Soup.getInstance().getConfig().get("WORLD.SPAWN.PITCH");
        Location spawnLoc = new Location(spawnWorld, spawnX, spawnY, spawnZ, spawnYaw, spawnPitch);
        player.teleport(spawnLoc);
    }

    @EventHandler
    public void useSoup(PlayerInteractEvent sex) {
        Player player = sex.getPlayer();
        Action action = sex.getAction();
        if (action.name().startsWith("RIGHT_") && player.getGameMode() == GameMode.SURVIVAL) {
            if (player.getItemInHand().getType() == Material.MUSHROOM_SOUP) {
                if (player.getHealth() <= 19.0D && !player.isDead()) {
                    if (player.getHealth() < 20.0D || player.getFoodLevel() < 20)
                        player.getItemInHand().setType(Material.BOWL);
                    player.setHealth(Math.min(player.getHealth() + 7.0D, 20.0D));
                    player.setFoodLevel(Math.min(player.getFoodLevel() + 2, 20));
                    player.updateInventory();
                }
            }
        }
    }
}
