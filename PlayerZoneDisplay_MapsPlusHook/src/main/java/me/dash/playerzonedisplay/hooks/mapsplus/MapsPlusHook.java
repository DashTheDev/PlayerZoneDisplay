package me.dash.playerzonedisplay.hooks.mapsplus;

import me.dash.playerzonedisplay.api.PlayerZoneDisplay;
import me.dash.playerzonedisplay.hooks.mapsplus.commands.TestCommand;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class MapsPlusHook extends JavaPlugin {

    private PlayerZoneDisplay api;

    public PlayerZoneDisplay getApi() {
        return api;
    }

    @Override
    public void onEnable() {
        RegisteredServiceProvider<PlayerZoneDisplay> provider = getServer().getServicesManager().getRegistration(PlayerZoneDisplay.class);
        if (provider != null) {
            this.api = provider.getProvider();
        }

        getCommand("test").setExecutor(new TestCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
