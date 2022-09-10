package me.dash.playerzonedisplay.core.api;

import me.dash.playerzonedisplay.api.PlayerZoneDisplay;
import me.dash.playerzonedisplay.core.PlayerZoneDisplayPlugin;

import java.util.UUID;

public class ApiProvider implements PlayerZoneDisplay {

    private final PlayerZoneDisplayPlugin plugin;

    public ApiProvider(PlayerZoneDisplayPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean getPlayerZone(UUID playerUUID) {
        return this.plugin.getPlayerZoneManager().getPlayerZone(playerUUID) != null;
    }
}
