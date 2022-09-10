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

import static org.bukkit.Bukkit.getServer;

public class PlayerListener implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        Player player = event.getPlayer();
        Location spawn = new Location(null, 0.0D, 0.0D, 0.0D);
        spawn.setWorld(getServer().getWorld(Soup.getInstance().getConfig().getString("WORLD")));
        spawn.setX(Soup.getInstance().getConfig().getDouble("X"));
        spawn.setY(Soup.getInstance().getConfig().getDouble("Y"));
        spawn.setZ(Soup.getInstance().getConfig().getDouble("Z"));
        spawn.setYaw(Soup.getInstance().getConfig().getInt("YAW"));
        spawn.setPitch(Soup.getInstance().getConfig().getInt("PITCH"));
        player.teleport(spawn);
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
