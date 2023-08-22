package com.wonkglorg.utilitylib.inventory.guis;

import com.wonkglorg.utilitylib.base.message.Message;
import com.wonkglorg.utilitylib.inventory.InventoryGUI;
import com.wonkglorg.utilitylib.inventory.MenuProfile;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.BeaconInventory;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public abstract class GuiBeacon extends InventoryGUI {
    public GuiBeacon(String name, Player player, JavaPlugin plugin) {
        super(Bukkit.createInventory(player, InventoryType.BEACON, Message.color(name)), plugin, player);
    }

    public GuiBeacon(Component name, Player player, JavaPlugin plugin) {
        super(Bukkit.createInventory(player, InventoryType.BEACON, name), plugin, player);
    }

    public GuiBeacon(BeaconInventory inventory, JavaPlugin plugin, MenuProfile profile) {
        super(inventory, plugin, profile);
    }

    public GuiBeacon(BeaconInventory inventory, JavaPlugin plugin, Player player) {
        super(inventory, plugin, player);
    }

    //TODO add method to handle beacon event when clicking done?

}
