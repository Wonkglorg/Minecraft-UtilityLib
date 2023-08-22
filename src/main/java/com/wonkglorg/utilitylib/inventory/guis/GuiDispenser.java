package com.wonkglorg.utilitylib.inventory.guis;

import com.wonkglorg.utilitylib.base.message.Message;
import com.wonkglorg.utilitylib.inventory.InventoryGUI;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public abstract class GuiDispenser extends InventoryGUI {

    public GuiDispenser(String name, Player player, JavaPlugin plugin) {
        super(Bukkit.createInventory(player, InventoryType.DISPENSER, Message.color(name)), plugin, player);
    }

    public GuiDispenser(Component name, Player player, JavaPlugin plugin) {
        super(Bukkit.createInventory(player, InventoryType.DISPENSER, name), plugin, player);
    }

}
