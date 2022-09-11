package me.dash.playerzonedisplay.core.data;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class PlayerZoneManager {

    private final HashMap<UUID, HashSet<BaseZoneData>> playerZoneMap;

    public PlayerZoneManager() {
        playerZoneMap = new HashMap<>();
    }

    public void addPlayer(UUID playerUUID) {
        playerZoneMap.put(playerUUID, new HashSet<>());
    }

    public void removePlayer(UUID playerUUID) {
        playerZoneMap.remove(playerUUID);
    }

    public void addPlayerZone(UUID playerUUID, BaseZoneData zoneData) {
        playerZoneMap.get(playerUUID).add(zoneData);
    }

    public void removePlayerZone(UUID playerUUID, BaseZoneData zoneData) {
        playerZoneMap.get(playerUUID).remove(zoneData);
    }

    public BaseZoneData getPlayerHighestPriorityZone(UUID playerUUID) {
        if (playerZoneMap.get(playerUUID).stream().count() == 0) {
            return null;
        }

        return playerZoneMap.get(playerUUID).stream().max(Comparator.comparing(BaseZoneData::getPriority)).get();
    }
}
