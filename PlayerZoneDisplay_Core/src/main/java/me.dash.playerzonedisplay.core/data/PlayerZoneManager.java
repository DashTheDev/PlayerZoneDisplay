package me.dash.playerzonedisplay.core.data;

import java.util.HashMap;
import java.util.UUID;

public class PlayerZoneManager {

    private final HashMap<UUID, ZoneData> playerZoneMap;

    public PlayerZoneManager() {
        playerZoneMap = new HashMap<>();
    }

    public ZoneData getPlayerZone(UUID playerUUID) {
        return playerZoneMap.get(playerUUID);
    }

    public void setPlayerZone(UUID playerUUID, ZoneData zoneData) {
        playerZoneMap.put(playerUUID, zoneData);
    }
    public void removePlayer(UUID playerUUID) {
        playerZoneMap.remove(playerUUID);
    }
}
