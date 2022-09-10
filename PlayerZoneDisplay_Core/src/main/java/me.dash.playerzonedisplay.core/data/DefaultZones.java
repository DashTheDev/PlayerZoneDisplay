package me.dash.playerzonedisplay.core.data;

import org.bukkit.ChatColor;

public interface DefaultZones {
    ZoneData OVERWORLD = new ZoneData("Overworld", ChatColor.GREEN);
    ZoneData NETHER = new ZoneData("Nether", ChatColor.RED);
    ZoneData END = new ZoneData("The End", ChatColor.DARK_PURPLE);
}
