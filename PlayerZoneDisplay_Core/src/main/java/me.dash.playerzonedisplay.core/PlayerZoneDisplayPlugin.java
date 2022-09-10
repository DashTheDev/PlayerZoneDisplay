package me.dash.playerzonedisplay.core;

import me.dash.playerzonedisplay.api.PlayerZoneDisplay;
import me.dash.playerzonedisplay.core.api.ApiProvider;
import me.dash.playerzonedisplay.core.data.PlayerZoneManager;
import me.dash.playerzonedisplay.core.data.ZoneManager;
import me.dash.playerzonedisplay.core.listeners.JoinListener;
import me.dash.playerzonedisplay.core.listeners.QuitListener;
import me.dash.playerzonedisplay.core.listeners.TeleportListener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerZoneDisplayPlugin extends JavaPlugin {

    private ZoneManager zoneManager;
    private PlayerZoneManager playerZoneManager;
    private ServicesManager servicesManager;

    public ZoneManager getZoneManager() {
        return zoneManager;
    }

    public PlayerZoneManager getPlayerZoneManager() {
        return playerZoneManager;
    }

    @Override
    public void onEnable() {
        this.zoneManager = new ZoneManager();
        this.playerZoneManager = new PlayerZoneManager();
        this.servicesManager = getServer().getServicesManager();

        ApiProvider apiProvider = new ApiProvider(this);
        servicesManager.register(PlayerZoneDisplay.class, apiProvider, this, ServicePriority.Highest);

        for (var service : servicesManager.getKnownServices()) {
            System.out.println(service);
        }

        getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        getServer().getPluginManager().registerEvents(new QuitListener(this), this);
        getServer().getPluginManager().registerEvents(new TeleportListener(this), this);
    }

    @Override
    public void onDisable() {

    }
}
