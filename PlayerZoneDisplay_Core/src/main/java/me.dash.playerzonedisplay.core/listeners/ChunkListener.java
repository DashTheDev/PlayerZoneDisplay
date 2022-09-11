package me.dash.playerzonedisplay.core.listeners;

import me.dash.playerzonedisplay.core.PlayerZoneDisplayPlugin;
import me.dash.playerzonedisplay.core.tasks.ZoneChunkUpdateTask;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

public class ChunkListener implements Listener {

    private PlayerZoneDisplayPlugin plugin;

    public ChunkListener(PlayerZoneDisplayPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
        new ZoneChunkUpdateTask(plugin, event.getChunk()).run();
    }

    @EventHandler
    public void onChunkUnload(ChunkUnloadEvent event) {
        new ZoneChunkUpdateTask(plugin, event.getChunk()).runTaskLater(plugin, 100);
    }
}
