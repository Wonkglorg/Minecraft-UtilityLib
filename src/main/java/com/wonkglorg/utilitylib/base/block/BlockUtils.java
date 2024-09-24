package com.wonkglorg.utilitylib.base.block;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.type.Chest;

public class BlockUtils {
    /**
     * Determines the location of the other half of a double chest.
     *
     * @param location The location of the chest block.
     * @return The location of the other half of the chest, or null if the block is not a double chest.
     */
    public static Location getOtherHalf(Location location) {
        var block = location.getBlock();
        if (block.getType() != Material.CHEST) return null;

        var chest = (Chest) block.getBlockData();
        var type = chest.getType();

        if (type == Chest.Type.SINGLE) return null;

        var otherHalf = block.getLocation();
        int offset = (type == Chest.Type.LEFT) ? 1 : -1;

        return switch (chest.getFacing()) {
            case NORTH -> otherHalf.add(offset, 0, 0);
            case SOUTH -> otherHalf.add(-offset, 0, 0);
            case EAST -> otherHalf.add(0, 0, offset);
            case WEST -> otherHalf.add(0, 0, -offset);
            default -> otherHalf;
        };
    }


}
