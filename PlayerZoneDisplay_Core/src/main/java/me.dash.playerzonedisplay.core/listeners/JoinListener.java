package me.dash.playerzonedisplay.core.listeners;

import me.dash.playerzonedisplay.core.PlayerZoneDisplayPlugin;
import me.dash.playerzonedisplay.core.data.ZoneData;
import me.dash.playerzonedisplay.core.utility.FormatUtility;
import me.dash.playerzonedisplay.core.utility.ZoneUtility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private PlayerZoneDisplayPlugin plugin;

    public JoinListener(PlayerZoneDisplayPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ZoneData defaultZone = ZoneUtility.getDefaultZone(player.getWorld().getEnvironment());
        plugin.getPlayerZoneManager().setPlayerZone(player.getUniqueId(), defaultZone);

        String playerListName = FormatUtility.formatPlayerListName(player, defaultZone);
        player.setPlayerListName(playerListName);
        defaultZone.addPlayer(player.getUniqueId());
    }
}
