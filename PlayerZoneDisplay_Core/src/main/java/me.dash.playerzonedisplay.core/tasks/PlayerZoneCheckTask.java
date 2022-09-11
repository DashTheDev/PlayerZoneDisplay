package me.dash.playerzonedisplay.core.tasks;

import me.dash.playerzonedisplay.core.PlayerZoneDisplayPlugin;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerZoneCheckTask extends BukkitRunnable {

    private final PlayerZoneDisplayPlugin plugin;
    private final Player player;
    private final Location location;

    public PlayerZoneCheckTask(PlayerZoneDisplayPlugin plugin, Player player, Location location) {
        this.plugin = plugin;
        this.player = player;
        this.location = location;
    }

    @Override
    public void run() {
        plugin.getZoneManager().playerCheckZones(player, location);
    }
}
