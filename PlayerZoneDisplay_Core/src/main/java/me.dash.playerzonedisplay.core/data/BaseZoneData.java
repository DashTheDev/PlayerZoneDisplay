package me.dash.playerzonedisplay.core.data;

import org.bukkit.ChatColor;

import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

public class BaseZoneData {

    private final String name;
    private final ChatColor colour;
    private final int priority;
    private final HashSet<UUID> players;

    public BaseZoneData(String name, ChatColor colour, int priority) {
        this.name = Objects.requireNonNull(name);
        this.colour = Objects.requireNonNull(colour);
        this.priority = priority;
        this.players = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public ChatColor getColour() {
        return colour;
    }

    public int getPriority() {
        return priority;
    }

    public void addPlayer(UUID playerUUID) {
        players.add(playerUUID);
    }

    public void removePlayer(UUID playerUUID) {
        players.remove(playerUUID);
    }

    public boolean hasPlayer(UUID playerUUID) {
        return players.contains(playerUUID);
    }

    public Stream<UUID> getPlayers() {
        return players.stream();
    }
}
