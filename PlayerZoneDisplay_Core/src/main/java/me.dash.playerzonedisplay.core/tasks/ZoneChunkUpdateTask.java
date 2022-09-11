package me.dash.playerzonedisplay.core.tasks;

import me.dash.playerzonedisplay.core.PlayerZoneDisplayPlugin;
import me.dash.playerzonedisplay.core.records.CustomChunk;
import org.bukkit.Chunk;
import org.bukkit.scheduler.BukkitRunnable;

public class ZoneChunkUpdateTask extends BukkitRunnable {

    private final PlayerZoneDisplayPlugin plugin;
    private final Chunk chunk;

    public ZoneChunkUpdateTask(PlayerZoneDisplayPlugin plugin, Chunk chunk) {
        this.plugin = plugin;
        this.chunk = chunk;
    }

    @Override
    public void run() {
        CustomChunk customChunk = new CustomChunk(chunk.getX(), chunk.getZ());
        plugin.getZoneManager().chunkUpdateZones(customChunk);
    }
}
