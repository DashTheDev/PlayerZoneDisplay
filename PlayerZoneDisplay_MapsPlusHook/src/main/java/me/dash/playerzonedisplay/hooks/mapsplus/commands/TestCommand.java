package me.dash.playerzonedisplay.hooks.mapsplus.commands;

import me.dash.playerzonedisplay.hooks.mapsplus.MapsPlusHook;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {

    private final MapsPlusHook plugin;

    public TestCommand(MapsPlusHook plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            player.sendMessage("" + plugin.getApi().getPlayerZone(player.getUniqueId()));
        }

        return true;
    }
}
