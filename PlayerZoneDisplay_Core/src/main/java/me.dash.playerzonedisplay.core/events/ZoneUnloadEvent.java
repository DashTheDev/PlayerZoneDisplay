package me.dash.playerzonedisplay.core.events;

import me.dash.playerzonedisplay.core.data.CustomZoneData;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ZoneUnloadEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final CustomZoneData zoneData;

    public ZoneUnloadEvent(CustomZoneData zoneData) {
        this.zoneData = zoneData;
    }

    public CustomZoneData getZoneData() {
        return zoneData;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
