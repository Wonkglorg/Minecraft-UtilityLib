package com.wonkglorg.utilitylib.builder.structure;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a structure of blocks that can be checked for completion
 */
public class Structure {
    private final List<Material[][]> structureLayers = new ArrayList<>();
    private final Set<Material> containedBlockMaterials = new HashSet<>();

    public Structure() {
    }

    public void addLayerBelow(Material[][] layer) {
        structureLayers.add(0, layer);
        updateContainedBlockMaterials();
    }

    public void addLayerAbove(Material[][] layer) {
        structureLayers.add(layer);
        updateContainedBlockMaterials();
    }

    public void setLayer(int index, Material[][] layer) {
        structureLayers.set(index, layer);
        updateContainedBlockMaterials();
    }

    public Material[][] getLayer(int index) {
        return structureLayers.get(index);
    }

    public List<Material[][]> getStructureLayers() {
        return structureLayers;
    }

    public Set<Material> getContainedBlockMaterials() {
        return containedBlockMaterials;
    }

    //updates the list of materials contained used for optimizing when to even check for the structure
    private void updateContainedBlockMaterials() {
        containedBlockMaterials.clear();
        for (Material[][] layer : structureLayers) {
            for (Material[] row : layer) {
                for (Material material : row) {
                    if (material != null) {
                        containedBlockMaterials.add(material);
                    }
                }
            }
        }
    }

    /**
     * Checkss for a valid structure at the location of the block, considers all possible permutations
     *
     * @param block the block to check
     * @return true if the structure is valid
     */
    public boolean isValidStructure(Block block) {
        Material placedMaterial = block.getType();
        if (!containedBlockMaterials.contains(placedMaterial)) {
            return false;
        }
        return isStructureComplete(block.getLocation());
    }

    //checks if all blocks are in a valid position
    private boolean isStructureComplete(Location placedLocation) {
        for (int yOffset = 0; yOffset < structureLayers.size(); yOffset++) {
            Material[][] layer = structureLayers.get(yOffset);
            for (int row = 0; row < layer.length; row++) {
                for (int col = 0; col < layer[row].length; col++) {
                    if (layer[row][col] != null) {
                        Location origin = placedLocation.clone().subtract(col, yOffset, row);
                        if (doesStructureMatch(origin)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    //checks if the structure matches the structure at the origin
    private boolean doesStructureMatch(Location origin) {
        for (int layerIndex = 0; layerIndex < structureLayers.size(); layerIndex++) {
            Material[][] layer = structureLayers.get(layerIndex);
            for (int row = 0; row < layer.length; row++) {
                for (int col = 0; col < layer[row].length; col++) {
                    Material expectedMaterial = layer[row][col];
                    Location checkLocation = origin.clone().add(col, layerIndex, row);
                    Block actualBlock = checkLocation.getBlock();

                    if (expectedMaterial != null) {
                        if (actualBlock.getType() != expectedMaterial) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int layerIndex = 0; layerIndex < structureLayers.size(); layerIndex++) {
            builder.append("-------- Layer ").append(layerIndex + 1).append(" --------\n");
            Material[][] layer = structureLayers.get(layerIndex);

            for (Material[] row : layer) {
                for (Material material : row) {
                    builder.append(material == null ? " " : material.name()).append(" ");
                }
                builder.append("\n");
            }
            builder.append("\n");
        }

        return builder.toString();
    }

}
