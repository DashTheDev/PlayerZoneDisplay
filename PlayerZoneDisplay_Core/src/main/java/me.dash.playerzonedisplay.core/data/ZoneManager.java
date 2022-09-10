package me.dash.playerzonedisplay.core.data;

import java.util.HashMap;
import java.util.UUID;

public class ZoneManager {

    private final HashMap<UUID, ZoneData> zoneMap;

    public ZoneManager() {
        this.zoneMap = new HashMap<>();
    }

    public ZoneData getZone(UUID zoneUUID) {
        return zoneMap.get(zoneUUID);
    }

    public void setZone(UUID zoneUUID, ZoneData zoneData) {
        zoneMap.put(zoneUUID, zoneData);
    }
}
