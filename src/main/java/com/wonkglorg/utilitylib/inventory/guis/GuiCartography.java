package com.wonkglorg.utilitylib.inventory.guis;

import com.wonkglorg.utilitylib.base.message.Message;
import com.wonkglorg.utilitylib.inventory.InventoryGUI;
import com.wonkglorg.utilitylib.inventory.MenuProfile;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.CartographyInventory;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public abstract class GuiCartography extends InventoryGUI {

    public GuiCartography(String name, Player player, JavaPlugin plugin) {
        super(Bukkit.createInventory(player, InventoryType.CARTOGRAPHY, Message.color(name)), plugin, player);
    }

    public GuiCartography(Component name, Player player, JavaPlugin plugin) {
        super(Bukkit.createInventory(player, InventoryType.CARTOGRAPHY, name), plugin, player);
    }

    public GuiCartography(CartographyInventory inventory, JavaPlugin plugin, MenuProfile profile) {
        super(inventory, plugin, profile);
    }

    public GuiCartography(CartographyInventory inventory, JavaPlugin plugin, Player player) {
        super(inventory, plugin, player);
    }




}
