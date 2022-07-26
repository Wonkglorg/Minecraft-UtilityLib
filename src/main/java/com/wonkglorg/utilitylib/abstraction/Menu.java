package com.wonkglorg.utilitylib.abstraction;

import com.wonkglorg.utilitylib.utils.builder.ItemBuilder;
import com.wonkglorg.utilitylib.utils.message.Color;
import com.wonkglorg.utilitylib.utils.inventory.MenuUtility;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public abstract class Menu implements InventoryHolder
{
	protected MenuUtility menuUtility;
	protected Inventory inventory;
	protected final ItemStack FILLER_GLASS = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE,1," ").build();
	
	public Menu(MenuUtility playerMenuUtility)
	{
		this.menuUtility = playerMenuUtility;
	}
	
	/**
	 * Returns the {@link Inventory} name.
	 * @return Menu name.
	 */
	public abstract String getMenuName();
	
	/**
	 * Gets the slots of the {@link Inventory}
	 *
	 * @return Amount of slots
	 */
	public abstract int getSlots();
	
	/**
	 * Executes when a {@link org.bukkit.entity.Player} clicks in an {@link Inventory} as {@link InventoryClickEvent} if the GUI is child of {@link Menu}
	 *
	 * @param event {@link InventoryClickEvent}
	 */
	public abstract void handleMenu(InventoryClickEvent event);
	
	/**
	 * Executes when a {@link org.bukkit.entity.Player} drags items in an {@link Inventory} as {@link InventoryDragEvent} if the GUI is child of {@link Menu}
	 *
	 * @param event {@link InventoryDragEvent}
	 */
	public abstract void dragHandle(InventoryDragEvent event);
	
	/**
	 * Executes when an entity or block moves an item from {@link Inventory} as {@link InventoryMoveItemEvent} if the GUI is child of {@link Menu}
	 *
	 * @param event {@link InventoryMoveItemEvent}
	 */
	public abstract void moveHandle(InventoryMoveItemEvent event);
	
	/**
	 * Executes when a {@link org.bukkit.entity.Player}
	 *  closes an {@link Inventory} as {@link InventoryCloseEvent} if the GUI is child of {@link Menu}
	 *
	 * @param event {@link InventoryDragEvent}
	 */
	public abstract void closeMenu(InventoryCloseEvent event);
	
	/**
	 * Sets all {@link ItemStack} within this method into the gui using "inventory.setItem()".
	 */
	public abstract void setMenuItems();
	
	/**
	 * Opens menu gui
	 */
	public void open()
	{
		inventory = Bukkit.createInventory(this, getSlots(), Color.color(getMenuName()));
		
		this.setMenuItems();
		
		menuUtility.getOwner().openInventory(inventory);
	}
	
	/**
	 *
	 * @return Returns the {@link Inventory}
	 */
	@Override
	public @NotNull Inventory getInventory()
	{
		return inventory;
	}
	
	/**
	 * Sets all remaining empty slots to glass panes.
	 */
	public void setFillerGlass()
	{
		for(int i = 0; i < getSlots(); i++)
		{
			if(inventory.getItem(i) == null)
			{
				inventory.setItem(i, FILLER_GLASS);
			}
		}
	}
}



