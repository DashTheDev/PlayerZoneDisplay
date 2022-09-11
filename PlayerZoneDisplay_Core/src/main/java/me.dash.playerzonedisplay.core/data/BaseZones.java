package me.dash.playerzonedisplay.core.data;

import org.bukkit.ChatColor;

public interface BaseZones {
    BaseZoneData OVERWORLD = new BaseZoneData("Overworld", ChatColor.GREEN, -1);
    BaseZoneData NETHER = new BaseZoneData("Nether", ChatColor.RED, -1);
    BaseZoneData END = new BaseZoneData("The End", ChatColor.DARK_PURPLE, -1);
}
