package me.dash.playerzonedisplay.core.data;

import org.bukkit.ChatColor;

import java.util.HashSet;
import java.util.UUID;
import java.util.stream.Stream;

public class ZoneData {

    private final String name;
    private final ChatColor colour;
    private final HashSet<UUID> players;

    public ZoneData(String name, ChatColor colour) {
        this.name = name;
        this.colour = colour;
        this.players = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public ChatColor getColour() {
        return colour;
    }

    public void addPlayer(UUID playerUUID) {
        players.add(playerUUID);
    }

    public void removePlayer(UUID playerUUID) {
        players.remove(playerUUID);
    }

    public Stream<UUID> getPlayers() {
        return players.stream();
    }
}
