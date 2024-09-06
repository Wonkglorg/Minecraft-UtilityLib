package com.wonkglorg.utilitylib.builder.structure;

import com.wonkglorg.utilitylib.builder.structure.function.MaterialFunction;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Represents a structure of blocks that can be checked for completion
 */
public class Structure {
	private final List<MaterialFunction[][]> structureLayers = new ArrayList<>();
	private final Set<Material> containedBlockMaterials = new HashSet<>();
	private final Map<Class<? extends MaterialFunction>, Boolean> containedEvents = new HashMap();

	public Structure() {
	}

	public void addLayerBelow(MaterialFunction[][] layer) {
		structureLayers.add(0, layer);
		update();
	}

	public void addLayerAbove(MaterialFunction[][] layer) {
		structureLayers.add(layer);
		update();
	}

	public void setLayer(int index, MaterialFunction[][] layer) {
		structureLayers.set(index, layer);
		update();
	}

	public MaterialFunction[][] getLayer(int index) {
		return structureLayers.get(index);
	}

	public List<MaterialFunction[][]> getStructureLayers() {
		return structureLayers;
	}

	public Set<Material> getContainedBlockMaterials() {
		return containedBlockMaterials;
	}

	//updates the list of materials contained used for optimizing when to even check for the
	// structure

	private void update() {
		updateContainedBlockMaterials();
		updateEvents();
	}

	private void updateContainedBlockMaterials() {
		containedBlockMaterials.clear();
		for (MaterialFunction[][] layer : structureLayers) {
			for (MaterialFunction[] row : layer) {
				for (MaterialFunction material : row) {
					if (material != null) {
						containedBlockMaterials.add(material.material());
					}
				}
			}
		}
	}

	private void updateEvents() {
		containedEvents.clear();
		for (MaterialFunction[][] layer : structureLayers) {
			for (MaterialFunction[] row : layer) {
				for (MaterialFunction material : row) {
					if (material == null) {
						continue;
					}

					if (material.hasFunction()) {
						containedEvents.put(material.getClass(), true);
					}
				}
			}
		}
	}

	/**
	 * Checkss for a valid structure at the location of the block, considers all possible
	 * permutations
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
			MaterialFunction[][] layer = structureLayers.get(yOffset);
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
			MaterialFunction[][] layer = structureLayers.get(layerIndex);
			for (int row = 0; row < layer.length; row++) {
				for (int col = 0; col < layer[row].length; col++) {
					MaterialFunction expectedMaterial = layer[row][col];
					Location checkLocation = origin.clone().add(col, layerIndex, row);
					Block actualBlock = checkLocation.getBlock();

					if (expectedMaterial != null) {
						if (actualBlock.getType() != expectedMaterial.material()) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	public boolean hasPlayerEvent() {
		return containsPlayerEvent;
	}

	public boolean hasBlockEvent() {
		return containsBlockEvent;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		for (int layerIndex = 0; layerIndex < structureLayers.size(); layerIndex++) {
			builder.append("-------- Layer ").append(layerIndex + 1).append(" --------\n");
			MaterialFunction[][] layer = structureLayers.get(layerIndex);

			for (MaterialFunction[] row : layer) {
				for (MaterialFunction material : row) {
					builder.append(material == null ? " " : material.name()).append(" ");
				}
				builder.append("\n");
			}
			builder.append("\n");
		}

		return builder.toString();
	}

}
