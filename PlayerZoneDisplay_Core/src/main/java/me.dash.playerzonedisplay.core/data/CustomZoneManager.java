package me.dash.playerzonedisplay.core.data;

import me.dash.playerzonedisplay.core.PlayerZoneDisplayPlugin;
import me.dash.playerzonedisplay.core.events.ZoneEnterEvent;
import me.dash.playerzonedisplay.core.events.ZoneLeaveEvent;
import me.dash.playerzonedisplay.core.events.ZoneLoadEvent;
import me.dash.playerzonedisplay.core.events.ZoneUnloadEvent;
import me.dash.playerzonedisplay.core.records.CustomChunk;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class CustomZoneManager {

    private final PlayerZoneDisplayPlugin plugin;
    private final HashMap<UUID, CustomZoneData> customZoneMap;

    public CustomZoneManager(PlayerZoneDisplayPlugin plugin) {
        this.plugin = plugin;
        this.customZoneMap = new HashMap<>();
    }

    public CustomZoneData getZone(UUID zoneUUID) {
        return customZoneMap.get(zoneUUID);
    }

    public void setZone(UUID zoneUUID, CustomZoneData zoneData) {
        customZoneMap.put(zoneUUID, zoneData);
    }

    public void chunkUpdateZones(CustomChunk chunk) {
        for (CustomZoneData customZoneData : customZoneMap.values()) {
            if (!customZoneData.chunkUpdate(chunk)) continue;

            if (customZoneData.isLoaded()) {
                ZoneLoadEvent zoneLoadEvent = new ZoneLoadEvent(customZoneData);
                plugin.getServer().getPluginManager().callEvent(zoneLoadEvent);
            } else {
                ZoneUnloadEvent zoneUnloadEvent = new ZoneUnloadEvent(customZoneData);
                plugin.getServer().getPluginManager().callEvent(zoneUnloadEvent);
            }
        }
    }

    public void playerCheckZones(Player player, Location newLocation) {
        internalPlayerCheckZones(player, newLocation);
    }

    public void playerCheckZones(Player player) {
        internalPlayerCheckZones(player, player.getLocation());
    }

    private void internalPlayerCheckZones(Player player, Location location) {
        for (CustomZoneData customZoneData : customZoneMap.values()) {
            if (!customZoneData.isLoaded()) continue;

            if (customZoneData.isInZone(location)) {
                if (customZoneData.hasPlayer(player.getUniqueId())) continue;

                customZoneData.addPlayer(player.getUniqueId());
                ZoneEnterEvent zoneEnterEvent = new ZoneEnterEvent(player, customZoneData);
                plugin.getServer().getPluginManager().callEvent(zoneEnterEvent);
            } else {
                if (!customZoneData.hasPlayer(player.getUniqueId())) continue;

                customZoneData.removePlayer(player.getUniqueId());
                ZoneLeaveEvent zoneLeaveEvent = new ZoneLeaveEvent(player, customZoneData);
                plugin.getServer().getPluginManager().callEvent(zoneLeaveEvent);
            }
        }
    }
}
