package me.krouda.soup.listener;

import me.krouda.soup.Soup;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import static me.krouda.soup.listener.CooldownListener.COOLDOWN;

public class RefillListener implements Listener {

    private final CooldownListener cooldownListener = new CooldownListener();

    @EventHandler
    public void onClickSign(PlayerInteractEvent dog) {
        ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP);
        Player player = dog.getPlayer();
        int coolDownLeft = cooldownListener.getCoolDown(player.getUniqueId());
        if (dog.getAction().name().contains("RIGHT_CLICK_BLOCK")) {
            if (dog.getClickedBlock().getType().name().contains("SIGN") && dog
                    .getClickedBlock().getState() instanceof Sign) {
                Sign sign = (Sign) dog.getClickedBlock().getState();
                if (ChatColor.stripColor(sign.getLine(1)).equals("[Soup]")) {
                    if (coolDownLeft == 0) {
                        dog.setCancelled(true);
                        Inventory inventory = Soup.getInstance().getServer().createInventory(null, 54, ChatColor.YELLOW + "Soup Refill");
                        for (int i = 0; i < 54; i++)
                            inventory.setItem(i, soup);
                        player.openInventory(inventory);

                        cooldownListener.setCoolDown(player.getUniqueId(), COOLDOWN);
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                int timeLeft = cooldownListener.getCoolDown(player.getUniqueId());
                                cooldownListener.setCoolDown(player.getUniqueId(), --timeLeft);
                                if (timeLeft == 0) {
                                    this.cancel();
                                }
                            }
                        }.runTaskTimer(Soup.getInstance(), 20, 20);
                    } else {
                        player.sendMessage(ChatColor.RED.toString() + coolDownLeft + " seconds left to use again.");
                    }
                }
            }
        }
    }
}
