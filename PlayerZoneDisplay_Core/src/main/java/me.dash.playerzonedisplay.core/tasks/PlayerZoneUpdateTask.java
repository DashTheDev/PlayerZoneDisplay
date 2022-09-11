package me.dash.playerzonedisplay.core.tasks;

import me.dash.playerzonedisplay.core.PlayerZoneDisplayPlugin;
import me.dash.playerzonedisplay.core.data.BaseZoneData;
import me.dash.playerzonedisplay.core.utility.FormatUtility;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerZoneUpdateTask extends BukkitRunnable {

    private final PlayerZoneDisplayPlugin plugin;
    private final Player player;

    public PlayerZoneUpdateTask(PlayerZoneDisplayPlugin plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
    }

    @Override
    public void run() {
        BaseZoneData highestPriorityZone = plugin.getPlayerZoneManager().getPlayerHighestPriorityZone(player.getUniqueId());
        String playerListName = FormatUtility.formatPlayerListName(player, highestPriorityZone);
        player.setPlayerListName(playerListName);
    }
}
