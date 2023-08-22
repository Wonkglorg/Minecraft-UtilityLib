package com.wonkglorg.utilitylib.inventory.guis;

import com.wonkglorg.utilitylib.base.message.Message;
import com.wonkglorg.utilitylib.inventory.InventoryGUI;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.BeaconInventory;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public abstract class GuiBlastFurnace extends InventoryGUI {

    public GuiBlastFurnace(String name, Player player, JavaPlugin plugin) {
        super(Bukkit.createInventory(player, InventoryType.BLAST_FURNACE, Message.color(name)), plugin, player);
    }

    public GuiBlastFurnace(Component name, Player player, JavaPlugin plugin) {
        super(Bukkit.createInventory(player, InventoryType.BLAST_FURNACE, name), plugin, player);
    }


}
