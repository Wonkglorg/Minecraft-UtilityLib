package com.wonkglorg.utilitylib.abstraction;

import com.wonkglorg.utilitylib.utils.builder.ItemBuilder;
import com.wonkglorg.utilitylib.utils.inventory.InventoryGUI;
import com.wonkglorg.utilitylib.utils.inventory.MenuUtility;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Creates a paginated menu which allows for multipage selection.
 */
@SuppressWarnings("unused")
public abstract class PaginatedMenu extends Menu
{
	
	//Keep track of what page the menu is on
	protected int page = 0;
	//28 is max items because with the border set below
	protected int maxItemsPerPage;
	//the index represents the index of the slot
	//that the loop is on
	protected int index = 0;
	
	public PaginatedMenu(MenuUtility playerMenuUtility, int maxItemsPerPage)
	{
		super(playerMenuUtility);
		this.maxItemsPerPage = maxItemsPerPage;
	}
	
	/**
	 * Sets border for menu including buttons.
	 */
	public void addMenuBorder()
	{
		inventory.setItem(48, menuIconPreviousPage());
		
		inventory.setItem(49, menuIconInfo("&#3875fbP&#3785fca&#3794fcg&#36a4fde" + " &#fb8f36" + page));
		
		inventory.setItem(50, menuIconNextPage());
		
		for(int i = 45; i < 54; i++)
		{
			if(inventory.getItem(i) == null)
			{
				inventory.setItem(i, super.FILLER_GLASS);
			}
		}
	}
	
	/**
	 * @return amount of items per page.
	 */
	public int getMaxItemsPerPage()
	{
		return maxItemsPerPage;
	}
	
	protected final ItemStack menuIconInfo(String name)
	{
		return new ItemBuilder(Material.PAPER, 1, name).build();
	}
	
	/**
	 * @return next page Icon
	 */
	protected final ItemStack menuIconNextPage()
	{
		return new ItemBuilder(Material.ARROW, 1, "&#5680fbN&#558afbe&#5493fcx&#539dfct &#52a7fcP&#51b1fca&#50bafdg&#4fc4fde").build();
	}
	
	/**
	 * @return previous page icon
	 */
	protected final ItemStack menuIconPreviousPage()
	{
		return new ItemBuilder(Material.ARROW,
				1,
				"&#fb8f36P&#fb8c37r&#fb8838e&#fc853av&#fc823bi&#fc7e3co&#fc7b3du&#fc773es &#fc743fP&#fd7141a&#fd6d42g&#fd6a43e").build();
	}
}