package me.dash.playerzonedisplay.core.listeners;

import me.dash.playerzonedisplay.core.PlayerZoneDisplayPlugin;
import me.dash.playerzonedisplay.core.events.ZoneEnterEvent;
import me.dash.playerzonedisplay.core.events.ZoneLeaveEvent;
import me.dash.playerzonedisplay.core.events.ZoneLoadEvent;
import me.dash.playerzonedisplay.core.events.ZoneUnloadEvent;
import me.dash.playerzonedisplay.core.tasks.PlayerZoneUpdateTask;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ZoneListener implements Listener {

    private PlayerZoneDisplayPlugin plugin;

    public ZoneListener(PlayerZoneDisplayPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onZoneLoad(ZoneLoadEvent event) {
        System.out.println(event.getZoneData().getName() + " loaded");
    }

    @EventHandler
    public void onZoneUnload(ZoneUnloadEvent event) {
        System.out.println(event.getZoneData().getName() + " unloaded");
    }

    @EventHandler
    public void onZoneEnter(ZoneEnterEvent event) {
        plugin.getPlayerZoneManager().addPlayerZone(event.getPlayer().getUniqueId(), event.getZoneData());
        new PlayerZoneUpdateTask(plugin, event.getPlayer()).run();
        System.out.println(event.getPlayer().getDisplayName() + " has entered " + event.getZoneData().getName());
    }

    @EventHandler
    public void onZoneLeave(ZoneLeaveEvent event) {
        plugin.getPlayerZoneManager().removePlayerZone(event.getPlayer().getUniqueId(), event.getZoneData());
        new PlayerZoneUpdateTask(plugin, event.getPlayer()).run();
        System.out.println(event.getPlayer().getDisplayName() + " has left " + event.getZoneData().getName());
    }
}
