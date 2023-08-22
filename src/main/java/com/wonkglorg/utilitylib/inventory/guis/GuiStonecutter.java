package com.wonkglorg.utilitylib.inventory.guis;

import com.wonkglorg.utilitylib.base.message.Message;
import com.wonkglorg.utilitylib.inventory.InventoryGUI;
import com.wonkglorg.utilitylib.inventory.MenuProfile;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.StonecutterInventory;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public abstract class GuiStonecutter extends InventoryGUI {

    public GuiStonecutter(String name, Player player, JavaPlugin plugin) {
        super(Bukkit.createInventory(player, InventoryType.STONECUTTER, Message.color(name)), plugin, player);
    }

    public GuiStonecutter(Component name, Player player, JavaPlugin plugin) {
        super(Bukkit.createInventory(player, InventoryType.STONECUTTER, name), plugin, player);
    }

    public GuiStonecutter(StonecutterInventory inventory, JavaPlugin plugin, MenuProfile profile) {
        super(inventory, plugin, profile);
    }

    public GuiStonecutter(StonecutterInventory inventory, JavaPlugin plugin, Player player) {
        super(inventory, plugin, player);
    }

    public StonecutterInventory getInventory(){
        return (StonecutterInventory) super.getInventory();
    }

}
