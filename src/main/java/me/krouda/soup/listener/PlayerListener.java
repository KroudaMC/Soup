package me.krouda.soup.listener;

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
        if (action.equals("RIGHT_")) {
            if (player.getItemInHand().getType() == Material.MUSHROOM_SOUP) {
                if (player.getHealth() < 20) {
                    player.setHealth(player.getHealth() + 2.5);
                }
            }
        }
    }
}
