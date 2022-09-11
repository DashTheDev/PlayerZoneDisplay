package me.dash.playerzonedisplay.core.utility;

import me.dash.playerzonedisplay.core.data.BaseZones;
import me.dash.playerzonedisplay.core.data.BaseZoneData;
import org.bukkit.World;

public class ZoneUtility {

    public static BaseZoneData getDefaultZone(World.Environment worldEnvironment) {
        return switch (worldEnvironment) {
            case NORMAL, CUSTOM -> BaseZones.OVERWORLD;
            case NETHER -> BaseZones.NETHER;
            case THE_END -> BaseZones.END;
        };
    }
}
