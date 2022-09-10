package me.krouda.soup.handler;

import com.jonahseguin.drink.CommandService;
import com.jonahseguin.drink.Drink;
import com.jonahseguin.drink.command.DrinkAuthorizer;
import com.jonahseguin.drink.parametric.binder.DrinkBinder;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandHandler {
    private final JavaPlugin plugin;
    private final CommandService service;

    public CommandHandler(JavaPlugin plugin) {
        this.plugin = plugin;
        this.service = Drink.get(plugin);
    }

    public void register(Object handler, String name, String... aliases) {
        this.service.register(handler, name, aliases);
    }

    public <T> DrinkBinder<T> bind(Class<T> type) {
        return this.service.bind(type);
    }

    public void registerCommands(String packageName) {
        try {
            this.service.registerCommands();
        } catch (Exception var3) {
            this.plugin.getServer().getLogger().info("There was an error registering commands!");
            this.plugin.getServer().getLogger().info("Error: " + var3.getMessage());
        }

    }

    public void registerCommands() {
        this.service.registerCommands();
    }

    public void setPermissionMessage(String message) {
        DrinkAuthorizer authorizer = new DrinkAuthorizer();
        authorizer.setNoPermissionMessage(message);
        this.service.setAuthorizer(authorizer);
    }
}
