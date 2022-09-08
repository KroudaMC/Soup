package me.krouda.soup.listener;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerListener implements Listener {

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
