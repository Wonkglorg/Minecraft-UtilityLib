package com.wonkglorg.utilitylib.builder.structure;

import com.wonkglorg.utilitylib.manager.selection.Cuboid;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StructureManager {
    private final Map<Structure, List<Cuboid>> structureLocations = new HashMap<>();

    public StructureManager() {

    }

    /**
     * Test if this placed block completes a structure
     *
     * @param block
     */
    public void completesStructure(Block block) {
        for (Structure structure : structureLocations.keySet()) {
            List<Cuboid> cuboids = structureLocations.get(structure);
            if (!structure.getContainedBlockMaterials().contains(block.getType())) {
                continue;
            }
            for (Cuboid cuboid : cuboids) {
                if (cuboid.contains(block.getLocation())) {
                    //can't have a struce already contained in anothers radius
                    return;
                }
            }

            if(!structure.isValidStructure(block)){
                return;
            }



            //add new structure bounds if it is completed etc
        }
    }

    //checks all valid locations if they would intercept with this players current position, if yes
    // execute an action assigned to this block / structure
    public void onPlayerMoveInto() {
        for (Map.Entry<Structure, List<Cuboid>> structureEntry : structureLocations.entrySet()) {
            Structure structure = structureEntry.getKey();
            List<Cuboid> cuboids = structureEntry.getValue();

            for (Cuboid cuboid : cuboids) {
                //if location within cuboid, do x if it is in the right block continue else, return entirely

                //if its not in the cuboid continue checking.
            }
        }
        //players current post
        //loads each structure, checks if a structure type contains such an event, if not skipt o next
        // one
        //else check that structures event area, if it overlaps but is not valid return as it can't be
        // any other
    }

    //checks all valid locations if they would intercept with a thrown items position
    public void onItemMoveInto() {
        //items current pos
        //loads each structure, checks if a structure type contains such an event, if not skipt o next
        // one
        //else check that structures event area, if it overlaps but is not valid return as it can't be
        // any other
    }


    public void register(final Structure structure) {
        structureLocations.putIfAbsent(structure, new ArrayList<>());
    }

    public void unregister(final Structure structure) {
        structureLocations.remove(structure);
    }


}
