package com.wonkglorg.utilitylib.inventory.guis;

import com.wonkglorg.utilitylib.base.message.Message;
import com.wonkglorg.utilitylib.inventory.InventoryGUI;
import com.wonkglorg.utilitylib.inventory.MenuProfile;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public abstract class GuiBrewer extends InventoryGUI implements Listener {


    public GuiBrewer(String name, Player player, JavaPlugin plugin) {
        super(Bukkit.createInventory(player, InventoryType.BREWING, Message.color(name)), plugin, player);
    }

    public GuiBrewer(Component name, Player player, JavaPlugin plugin) {
        super(Bukkit.createInventory(player, InventoryType.BREWING, name), plugin, player);
    }

    public GuiBrewer(BrewerInventory inventory, JavaPlugin plugin, MenuProfile profile) {
        super(inventory, plugin, profile);
    }

    public GuiBrewer(BrewerInventory inventory, JavaPlugin plugin, Player player) {
        super(inventory, plugin, player);
    }

    //not gonna work cause the listener gets destroyed on close!
    /*
    @EventHandler
    public void onBrewing(BrewEvent e){
        if(e.getContents().equals(inventory)){
            onBrewingEvent(e);
        }
    }
    public abstract void onBrewingEvent(BrewEvent e);

     */
}
