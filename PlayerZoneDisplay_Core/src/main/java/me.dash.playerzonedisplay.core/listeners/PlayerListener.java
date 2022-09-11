package me.dash.playerzonedisplay.core.listeners;

import me.dash.playerzonedisplay.core.PlayerZoneDisplayPlugin;
import me.dash.playerzonedisplay.core.data.BaseZoneData;
import me.dash.playerzonedisplay.core.tasks.PlayerBaseZoneCheckTask;
import me.dash.playerzonedisplay.core.tasks.PlayerZoneCheckTask;
import me.dash.playerzonedisplay.core.utility.FormatUtility;
import me.dash.playerzonedisplay.core.utility.ZoneUtility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerListener implements Listener {

    private PlayerZoneDisplayPlugin plugin;

    public PlayerListener(PlayerZoneDisplayPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        plugin.getPlayerZoneManager().addPlayer(event.getPlayer().getUniqueId());
        new PlayerZoneCheckTask(plugin, event.getPlayer(), event.getPlayer().getLocation()).runTaskLater(plugin, 10);
        new PlayerBaseZoneCheckTask(plugin, event.getPlayer(), event.getPlayer().getLocation()).run();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        plugin.getPlayerZoneManager().removePlayer(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        new PlayerZoneCheckTask(plugin, event.getPlayer(), event.getTo()).runTaskLater(plugin, 10);
        new PlayerBaseZoneCheckTask(plugin, event.getPlayer(), event.getPlayer().getLocation()).run();
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        new PlayerZoneCheckTask(plugin, event.getPlayer(), event.getTo()).run();
    }
}
