package me.dash.playerzonedisplay.core.listeners;

import me.dash.playerzonedisplay.core.PlayerZoneDisplayPlugin;
import me.dash.playerzonedisplay.core.data.ZoneData;
import me.dash.playerzonedisplay.core.utility.FormatUtility;
import me.dash.playerzonedisplay.core.utility.ZoneUtility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class TeleportListener implements Listener {

    private PlayerZoneDisplayPlugin plugin;

    public TeleportListener(PlayerZoneDisplayPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        plugin.getPlayerZoneManager().getPlayerZone(player.getUniqueId()).removePlayer(player.getUniqueId());

        ZoneData defaultZone = ZoneUtility.getDefaultZone(event.getTo().getWorld().getEnvironment());
        String playerListName = FormatUtility.formatPlayerListName(player, defaultZone);
        player.setPlayerListName(playerListName);
        defaultZone.addPlayer(player.getUniqueId());

        plugin.getPlayerZoneManager().setPlayerZone(player.getUniqueId(), defaultZone);
    }
}
