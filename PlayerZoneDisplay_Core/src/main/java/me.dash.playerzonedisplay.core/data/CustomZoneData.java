package me.dash.playerzonedisplay.core.data;

import me.dash.playerzonedisplay.core.records.CustomChunk;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.BoundingBox;

import java.util.HashSet;
import java.util.Objects;

public class CustomZoneData extends BaseZoneData {

    private final BoundingBox boundingBox;
    private final Location centerPoint;
    private final int radius;
    private final World world;
    private final HashSet<CustomChunk> chunks;
    private boolean isLoaded;

    public CustomZoneData(String name, ChatColor colour, Location centerPoint, int radius, int priority) throws NullPointerException {
        super(name, colour, priority);
        this.boundingBox = null;
        this.centerPoint = Objects.requireNonNull(centerPoint);
        this.radius = radius;
        this.world = centerPoint.getWorld();
        this.isLoaded = false;
        this.chunks = getChunksByRadius();
    }

    public CustomZoneData(String name, ChatColor colour, Location posOne, Location posTwo, int priority) throws Exception {
        super(name, colour, priority);
        this.boundingBox = getBoundingBox(posOne, posTwo);
        this.centerPoint = null;
        this.radius = 0;
        this.world = getWorld(posOne, posTwo);
        this.isLoaded = false;
        this.chunks = getChunksByBoundingBox();
    }

    private BoundingBox getBoundingBox(Location posOne, Location posTwo) throws Exception {
        checkPositionsForNPE(posOne, posTwo);
        checkPositionsInSameEnvironmentAndWorld(posOne, posTwo);
        return new BoundingBox(posOne.getX(), posOne.getY(), posOne.getZ(),
                posTwo.getX(), posTwo.getY(), posTwo.getZ());
    }

    private World getWorld(Location posOne, Location posTwo) throws Exception {
        checkPositionsForNPE(posOne, posTwo);
        checkPositionsInSameEnvironmentAndWorld(posOne, posTwo);
        return posOne.getWorld();
    }

    private void checkPositionsForNPE(Location posOne, Location posTwo) throws NullPointerException {
        if (posOne == null || posTwo == null) {
            throw new NullPointerException();
        }
    }

    private void checkPositionsInSameEnvironmentAndWorld(Location posOne, Location posTwo) throws Exception {
        if (posOne.getWorld() != posTwo.getWorld() ||
                posOne.getWorld().getEnvironment() != posTwo.getWorld().getEnvironment()) {
            throw new Exception("Locations must be in the same world and environment/dimension.");
        }
    }

    private HashSet<CustomChunk> getChunksByRadius() {
        return getChunksInArea(centerPoint.getBlockZ() + radius,
                centerPoint.getBlockX() + radius,
                centerPoint.getBlockZ() - radius,
                centerPoint.getBlockX() - radius);
    }

    private HashSet<CustomChunk> getChunksByBoundingBox() {
        return getChunksInArea((int)boundingBox.getMaxZ(),
                (int)boundingBox.getMaxX(),
                (int)boundingBox.getMinZ(),
                (int)boundingBox.getMaxZ());
    }

    private HashSet<CustomChunk> getChunksInArea(int zMax, int xMax, int zMin, int xMin) {
        HashSet<CustomChunk> coveredChunks = new HashSet<>();

        for (int zPos = zMax; zPos > zMin; zPos -= 16) {
            for (int xPos = xMax; xPos > xMin; xPos -= 16) {
                CustomChunk chunk = new CustomChunk(xPos >> 4, zPos >> 4);
                coveredChunks.add(chunk);

                if (!isLoaded) {
                    isLoaded = world.isChunkLoaded(chunk.x(), chunk.z());
                }
            }
        }

        return coveredChunks;
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public boolean isInZone(Location location) {
        if (boundingBox != null) {
            return boundingBox.contains(location.toVector());
        } else {
            return location.distanceSquared(centerPoint) <= radius * radius;
        }
    }

    public boolean chunkUpdate(CustomChunk chunk) {
        if (!chunks.contains(chunk)) return false;
        boolean hasLoadedChunks = chunks.stream().anyMatch(c -> world.isChunkLoaded(c.x(), c.z()));

        if (!hasLoadedChunks && isLoaded) {
            isLoaded = false;
            return true;
        } else if (hasLoadedChunks && !isLoaded) {
            isLoaded = true;
            return true;
        }

        return false;
    }
}
