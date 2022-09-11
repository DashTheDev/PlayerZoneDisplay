package me.dash.playerzonedisplay.core.tasks;

import me.dash.playerzonedisplay.core.PlayerZoneDisplayPlugin;
import me.dash.playerzonedisplay.core.data.BaseZoneData;
import me.dash.playerzonedisplay.core.events.ZoneEnterEvent;
import me.dash.playerzonedisplay.core.events.ZoneLeaveEvent;
import me.dash.playerzonedisplay.core.utility.ZoneUtility;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerBaseZoneCheckTask extends BukkitRunnable {

    private final PlayerZoneDisplayPlugin plugin;
    private final Player player;
    private final Location location;

    public PlayerBaseZoneCheckTask(PlayerZoneDisplayPlugin plugin, Player player, Location location) {
        this.plugin = plugin;
        this.player = player;
        this.location = location;
    }

    @Override
    public void run() {
        BaseZoneData zoneData = ZoneUtility.getDefaultZone(player.getLocation().getWorld().getEnvironment());
        ZoneLeaveEvent zoneLeaveEvent = new ZoneLeaveEvent(player, zoneData);
        plugin.getServer().getPluginManager().callEvent(zoneLeaveEvent);

        BaseZoneData zoneData2 = ZoneUtility.getDefaultZone(location.getWorld().getEnvironment());
        ZoneEnterEvent zoneEnterEvent = new ZoneEnterEvent(player, zoneData2);
        plugin.getServer().getPluginManager().callEvent(zoneEnterEvent);
    }
}
