package me.dash.playerzonedisplay.core.listeners;

import me.dash.playerzonedisplay.core.PlayerZoneDisplayPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    private PlayerZoneDisplayPlugin plugin;

    public QuitListener(PlayerZoneDisplayPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        plugin.getPlayerZoneManager().removePlayer(event.getPlayer().getUniqueId());
    }
}
