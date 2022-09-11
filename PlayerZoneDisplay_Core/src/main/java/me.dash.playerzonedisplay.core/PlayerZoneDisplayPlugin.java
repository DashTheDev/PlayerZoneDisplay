package me.dash.playerzonedisplay.core;

import me.dash.playerzonedisplay.api.PlayerZoneDisplay;
import me.dash.playerzonedisplay.core.api.ApiProvider;
import me.dash.playerzonedisplay.core.data.CustomZoneData;
import me.dash.playerzonedisplay.core.data.PlayerZoneManager;
import me.dash.playerzonedisplay.core.data.CustomZoneManager;
import me.dash.playerzonedisplay.core.listeners.ChunkListener;
import me.dash.playerzonedisplay.core.listeners.PlayerListener;
import me.dash.playerzonedisplay.core.listeners.ZoneListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public final class PlayerZoneDisplayPlugin extends JavaPlugin {

    private CustomZoneManager zoneManager;
    private PlayerZoneManager playerZoneManager;
    private ServicesManager servicesManager;

    public CustomZoneManager getZoneManager() {
        return zoneManager;
    }

    public PlayerZoneManager getPlayerZoneManager() {
        return playerZoneManager;
    }

    @Override
    public void onEnable() {
        this.zoneManager = new CustomZoneManager(this);
        this.playerZoneManager = new PlayerZoneManager();
        this.servicesManager = getServer().getServicesManager();

        UUID test = UUID.randomUUID();
        UUID test2 = UUID.randomUUID();
        UUID test3 = UUID.randomUUID();
        zoneManager.setZone(test, new CustomZoneData("Testing", ChatColor.RED, new Location(Bukkit.getWorld("world"),0, 60, 0), 25, 1));
        zoneManager.setZone(test2, new CustomZoneData("Testing 2", ChatColor.RED, new Location(Bukkit.getWorld("world"),100, 70, 0), 10, 2));
        zoneManager.setZone(test3, new CustomZoneData("Testing 3", ChatColor.RED, new Location(Bukkit.getWorld("world"),10000, 70, 0), 10, 3));

        ApiProvider apiProvider = new ApiProvider(this);
        servicesManager.register(PlayerZoneDisplay.class, apiProvider, this, ServicePriority.Highest);

        for (var service : servicesManager.getKnownServices()) {
            System.out.println(service);
        }

        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getPluginManager().registerEvents(new ChunkListener(this), this);
        getServer().getPluginManager().registerEvents(new ZoneListener(this), this);
    }

    @Override
    public void onDisable() {

    }
}
