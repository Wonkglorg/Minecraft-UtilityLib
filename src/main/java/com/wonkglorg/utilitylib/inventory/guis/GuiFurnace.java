package com.wonkglorg.utilitylib.inventory.guis;

import com.wonkglorg.utilitylib.base.message.Message;
import com.wonkglorg.utilitylib.inventory.InventoryGUI;
import com.wonkglorg.utilitylib.inventory.MenuProfile;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public abstract class GuiFurnace extends InventoryGUI {

    public GuiFurnace(String name, Player player, JavaPlugin plugin) {
        super(Bukkit.createInventory(player, InventoryType.FURNACE, Message.color(name)), plugin, player);
    }

    public GuiFurnace(Component name, Player player, JavaPlugin plugin) {
        super(Bukkit.createInventory(player, InventoryType.FURNACE, name), plugin, player);
    }

    public GuiFurnace(FurnaceInventory inventory, JavaPlugin plugin, MenuProfile profile) {
        super(inventory, plugin, profile);
    }

    public GuiFurnace(FurnaceInventory inventory, JavaPlugin plugin, Player player) {
        super(inventory, plugin, player);
    }

    public FurnaceInventory getInventory(){
        return (FurnaceInventory) super.getInventory();
    }

}