package com.wonkglorg.utilitylib.builder.structure;

import com.wonkglorg.utilitylib.builder.structure.function.MaterialFunction;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Builder class for creating structures in a recipe styled way
 */
public class StructureBuilder {
	private final Map<Character, MaterialFunction> materialMapper = new HashMap<>();
	private final List<String[]> layers = new ArrayList<>();

	public StructureBuilder() {

	}

	/**
	 * Asigns a character to a material (space cannot be asigned automatically gets treated as any
	 * block valid at this location)
	 *
	 * @param key the char to use
	 * @param material the material to asign
	 * @return the builder
	 */
	public StructureBuilder addMaterial(char key, Material material) {
		materialMapper.put(key, MaterialFunction.of(material));
		return this;
	}

	public StructureBuilder addMaterialFunction(char key, MaterialFunction materialFunction) {
		materialMapper.put(key, materialFunction);
		return this;
	}

	/**
	 * Adds a layer to the structure above the current highest layer based on the pattern. Space gets
	 * interpreted as any block is valid, if it should be air assign Material.air to a character
	 * <p>
	 * Format:
	 * <pre>
	 * cae
	 * b e
	 * cae
	 * </pre>
	 *
	 * @param pattern the pattern to add
	 * @return the structure builder
	 */
	public StructureBuilder addLayerAbove(String[] pattern) {
		layers.add(pattern);
		return this;
	}

	/**
	 * Adds a layer to the structure below the current lowest layer based on the pattern. Space gets
	 * interpreted as any block is valid, if it should be air assign Material.air to a character
	 * <p>
	 * Format:
	 * <pre>
	 * cae
	 * bxe
	 * cae
	 * </pre>
	 *
	 * @param pattern the pattern to add
	 * @return the structure builder
	 */
	public StructureBuilder addLayerBelow(String[] pattern) {
		layers.add(0, pattern);
		return this;
	}

	private MaterialFunction[][] convertPatternToMaterials(String[] pattern) {
		if (pattern.length == 0) {
			throw new IllegalArgumentException("Pattern must have at least one row.");
		}

		int rowLength = pattern[0].length();
		for (String row : pattern) {
			if (row.length() != rowLength) {
				throw new IllegalArgumentException("All rows in the pattern must have the same length.");
			}
		}

		MaterialFunction[][] materialPattern = new MaterialFunction[pattern.length][rowLength];
		for (int i = 0; i < pattern.length; i++) {
			for (int j = 0; j < pattern[i].length(); j++) {
				char key = pattern[i].charAt(j);
				materialPattern[i][j] = (key == ' ') ? null : materialMapper.get(key);

				if (materialPattern[i][j] == null && key != ' ') {
					throw new IllegalArgumentException("No material mapped for key: " + key);
				}
			}
		}
		return materialPattern;
	}

	public Structure build() {
		Structure structure = new Structure();
		for (String[] pattern : layers) {
			//todo:jmd check if this is the right way around
			structure.addLayerAbove(convertPatternToMaterials(pattern));
		}


		return structure;
	}
}
