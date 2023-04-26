package com.wonkglorg.utilitylib.pathfinding;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

@SuppressWarnings("unused")
public final class AStar
{
	// Define the heuristic function that estimates the distance between two points
	public static int heuristic(int[] a, int[] b)
	{
		return Math.abs(b[0] - a[0]) + Math.abs(b[1] - a[1]) + Math.abs(b[2] - a[2]);
	}
	
	// Define the A* pathfinding function
	public static ArrayList<Location> findPath(Location start, Location end, int minHeight, HashSet<Material> impassableMaterials)
	{
		// Initialize the starting node
		PriorityQueue<int[]> frontier = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
		frontier.add(new int[]{heuristic(toIntArray(start), toIntArray(end)), start.getBlockX(), start.getBlockY(), start.getBlockZ()});
		HashMap<String, int[]> cameFrom = new HashMap<>();
		HashMap<String, Integer> costSoFar = new HashMap<>();
		cameFrom.put(Arrays.toString(toIntArray(start)), null);
		costSoFar.put(Arrays.toString(toIntArray(start)), 0);
		
		// Loop until the goal is reached or there are no more nodes to explore
		while(!frontier.isEmpty())
		{
			// Get the node with the lowest estimated cost
			int[] current = frontier.poll();
			
			// Check if the goal has been reached
			if(Arrays.equals(new int[]{current[1], current[2], current[3]}, toIntArray(end)))
			{
				// Reconstruct the path from start to end
				ArrayList<Location> path = new ArrayList<>();
				Location node = toLocation(new int[]{current[1], current[2], current[3]}, start.getWorld());
				while(cameFrom.get(Arrays.toString(toIntArray(node))) != null)
				{
					path.add(0, node);
					node = toLocation(cameFrom.get(Arrays.toString(toIntArray(node))), start.getWorld());
				}
				path.add(0, start);
				return path;
			}
			
			// Explore the neighbors of the current node
			for(int[] neighbor : new int[][]{{current[1] + 1, current[2], current[3]},
											 {current[1] - 1, current[2], current[3]},
											 {current[1], current[2] + 1, current[3]},
											 {current[1], current[2] - 1, current[3]},
											 {current[1], current[2], current[3] + 1},
											 {current[1], current[2], current[3] - 1}})
			{
				// Check if the neighbor is impassable or if it requires a higher minimum height
				Block block = start.getWorld().getBlockAt(neighbor[0], neighbor[1], neighbor[2]);
				if(neighbor[2] < minHeight || impassableMaterials.contains(block.getType()))
				{
					continue;
				}
				
				int newCost = costSoFar.get(Arrays.toString(new int[]{current[1], current[2], current[3]})) + 1;
				if(!costSoFar.containsKey(Arrays.toString(neighbor)) || newCost < costSoFar.get(Arrays.toString(neighbor)))
				{
					costSoFar.put(Arrays.toString(neighbor), newCost);
					int priority = newCost + heuristic(neighbor, toIntArray(end));
					frontier.add(new int[]{priority, neighbor[0], neighbor[1], neighbor[2]});
					cameFrom.put(Arrays.toString(neighbor), new int[]{current[1], current[2], current[3]});
				}
			}
		}
		
		// No path found
		return null;
	}
	
	// Helper function to convert a Location object to an int array
	public static int[] toIntArray(Location location)
	{
		return new int[]{location.getBlockX(), location.getBlockY(), location.getBlockZ()};
	}
	
	// Helper function to convert an int array to a Location object
	public static Location toLocation(int[] arr, org.bukkit.World world)
	{
		return new Location(world, arr[0], arr[1], arr[2]);
	}
}
		

