package com.wonkglorg.utilitylib.inventory.guis;

import com.wonkglorg.utilitylib.base.message.Message;
import com.wonkglorg.utilitylib.inventory.InventoryGUI;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public abstract class GuiSmoker extends InventoryGUI {

    public GuiSmoker(String name, Player player, JavaPlugin plugin) {
        super(Bukkit.createInventory(player, InventoryType.SMOKER, Message.color(name)), plugin, player);
    }

    public GuiSmoker(Component name, Player player, JavaPlugin plugin) {
        super(Bukkit.createInventory(player, InventoryType.SMOKER, name), plugin, player);
    }



}
