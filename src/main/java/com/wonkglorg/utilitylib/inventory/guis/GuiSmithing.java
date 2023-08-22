package com.wonkglorg.utilitylib.inventory.guis;

import com.wonkglorg.utilitylib.base.message.Message;
import com.wonkglorg.utilitylib.inventory.InventoryGUI;
import com.wonkglorg.utilitylib.inventory.MenuProfile;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.LoomInventory;
import org.bukkit.inventory.SmithingInventory;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public abstract class GuiSmithing extends InventoryGUI {

    public GuiSmithing(String name, Player player, JavaPlugin plugin) {
        super(Bukkit.createInventory(player, InventoryType.SMITHING, Message.color(name)), plugin, player);
    }

    public GuiSmithing(Component name, Player player, JavaPlugin plugin) {
        super(Bukkit.createInventory(player, InventoryType.SMITHING, name), plugin, player);
    }

    public GuiSmithing(SmithingInventory inventory, JavaPlugin plugin, MenuProfile profile) {
        super(inventory, plugin, profile);
    }

    public GuiSmithing(SmithingInventory inventory, JavaPlugin plugin, Player player) {
        super(inventory, plugin, player);
    }

    public SmithingInventory getInventory(){
        return (SmithingInventory) super.getInventory();
    }

}

