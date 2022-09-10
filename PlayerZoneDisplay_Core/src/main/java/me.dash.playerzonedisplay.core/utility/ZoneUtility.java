package me.dash.playerzonedisplay.core.utility;

import me.dash.playerzonedisplay.core.data.DefaultZones;
import me.dash.playerzonedisplay.core.data.ZoneData;
import org.bukkit.World;

public class ZoneUtility {

    public static ZoneData getDefaultZone(World.Environment worldEnvironment) {
        return switch (worldEnvironment) {
            case NORMAL, CUSTOM -> DefaultZones.OVERWORLD;
            case NETHER -> DefaultZones.NETHER;
            case THE_END -> DefaultZones.END;
        };
    }
}
