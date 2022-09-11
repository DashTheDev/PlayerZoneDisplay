package me.dash.playerzonedisplay.core.events;

import me.dash.playerzonedisplay.core.data.BaseZoneData;
import me.dash.playerzonedisplay.core.data.CustomZoneData;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ZoneLeaveEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final BaseZoneData zoneData;

    public ZoneLeaveEvent(Player player, BaseZoneData zoneData) {
        this.player = player;
        this.zoneData = zoneData;
    }

    public Player getPlayer() {
        return player;
    }

    public BaseZoneData getZoneData() {
        return zoneData;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
