package me.krouda.soup.listener;

import me.krouda.soup.Soup;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerListener implements Listener {

    private final Map<UUID, Integer> cooldown = new HashMap<>();
    public static final int COOLDOWN = 30;

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
    
    @EventHandler
    public void onClickSign(PlayerInteractEvent dog) {
        Player player = dog.getPlayer();
        int coolDownLeft = getCoolDown(player.getUniqueId());
        if (dog.getClickedBlock() == null) {
            if (dog.getClickedBlock().getType().name().contains("SIGN") && dog
                    .getClickedBlock().getState() instanceof Sign) {
                Sign sign = (Sign) dog.getClickedBlock().getState();
                if (ChatColor.stripColor(sign.getLine(1)).equals("[Soup]")) {
                    if (coolDownLeft == 0) {
                        dog.setCancelled(true);
                        Inventory inventory = Soup.getInstance().getServer().createInventory(null, 54, ChatColor.GRAY + "Soup Refill");
                        for (int i = 0; i < 54; i++)
                            inventory.setItem(i, new ItemStack(Material.MUSHROOM_SOUP));
                        player.openInventory(inventory);

                        setCoolDown(player.getUniqueId(), COOLDOWN);
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                int timeLeft = getCoolDown(player.getUniqueId());
                                setCoolDown(player.getUniqueId(), --timeLeft);
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

    public void setCoolDown(UUID player, int time){
        if(time < 1) {
            cooldown.remove(player);
        } else {
            cooldown.put(player, time);
        }
    }

    public int getCoolDown(UUID player){
        return cooldown.getOrDefault(player, 0);
    }
}
