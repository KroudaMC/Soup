package me.krouda.soup.listener;

import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownListener {

    private final Map<UUID, Integer> cooldown = new HashMap<>();
    public static final int COOLDOWN = 10;

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
