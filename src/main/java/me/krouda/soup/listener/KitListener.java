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

public class KitListener implements Listener {

    private final CooldownListener cooldownListener = new CooldownListener();
    
    @EventHandler
    public void onKitSign(PlayerInteractEvent e) {
        ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP);
        Player player = e.getPlayer();
        int coolDownLeft = cooldownListener.getCoolDown(player.getUniqueId());
        if (e.getAction().name().contains("RIGHT_CLICK_BLOCK")) {
            if (e.getClickedBlock().getType().name().contains("SIGN") && e
                    .getClickedBlock().getState() instanceof Sign) {
                Sign sign = (Sign) e.getClickedBlock().getState();
                if (ChatColor.stripColor(sign.getLine(1)).equals("[Iron]")) {
                    if (coolDownLeft == 0) {
                        e.setCancelled(true);
                        Inventory inventory = Soup.getInstance().getServer().createInventory(null, 54, ChatColor.GRAY + "Iron Kit");
                        for (int i = 0; i < 54; i++)
                            inventory.setItem(i, soup);
                        inventory.setItem(0, new ItemStack(Material.IRON_HELMET));
                        inventory.setItem(1, new ItemStack(Material.IRON_CHESTPLATE));
                        inventory.setItem(2, new ItemStack(Material.IRON_LEGGINGS));
                        inventory.setItem(3, new ItemStack(Material.IRON_BOOTS));
                        inventory.setItem(4, new ItemStack(Material.DIAMOND_SWORD));
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
