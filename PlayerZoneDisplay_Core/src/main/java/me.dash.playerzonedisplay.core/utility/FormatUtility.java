package me.dash.playerzonedisplay.core.utility;

import me.dash.playerzonedisplay.core.data.BaseZoneData;
import org.bukkit.entity.Player;

public class FormatUtility {

    public static String formatPlayerListName(Player player, BaseZoneData zoneData) {
        return player.getDisplayName() + " " + "§8§l(" + zoneData.getColour() + "§l" + zoneData.getName() + "§8§l)";
    }
}
