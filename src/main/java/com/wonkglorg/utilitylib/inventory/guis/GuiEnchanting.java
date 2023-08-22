package com.wonkglorg.utilitylib.inventory.guis;

import com.wonkglorg.utilitylib.base.message.Message;
import com.wonkglorg.utilitylib.inventory.InventoryGUI;
import com.wonkglorg.utilitylib.inventory.MenuProfile;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public abstract class GuiEnchanting extends InventoryGUI {

    public GuiEnchanting(String name, Player player, JavaPlugin plugin) {
        super(Bukkit.createInventory(player, InventoryType.ENCHANTING, Message.color(name)), plugin, player);
    }

    public GuiEnchanting(Component name, Player player, JavaPlugin plugin) {
        super(Bukkit.createInventory(player, InventoryType.ENCHANTING, name), plugin, player);
    }

    public GuiEnchanting(EnchantingInventory inventory, JavaPlugin plugin, MenuProfile profile) {
        super(inventory, plugin, profile);
    }

    public GuiEnchanting(EnchantingInventory inventory, JavaPlugin plugin, Player player) {
        super(inventory, plugin, player);
    }

    public EnchantingInventory getInventory(){
        return (EnchantingInventory) super.getInventory();
    }

}
