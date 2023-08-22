package com.wonkglorg.utilitylib.inventory.guis;

import com.wonkglorg.utilitylib.base.message.Message;
import com.wonkglorg.utilitylib.inventory.InventoryGUI;
import com.wonkglorg.utilitylib.inventory.MenuProfile;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public abstract class GuiAnvil extends InventoryGUI implements Listener {


    public GuiAnvil(String name, Player player, JavaPlugin plugin) {
        super(Bukkit.createInventory(player, InventoryType.ANVIL, Message.color(name)), plugin,player);
    }

    public GuiAnvil(Component name, Player player, JavaPlugin plugin) {
        super(Bukkit.createInventory(player, InventoryType.ANVIL, name), plugin,player);
    }
    public GuiAnvil(AnvilInventory inventory, JavaPlugin plugin, MenuProfile profile) {
        super(inventory, plugin, profile);
    }

    public GuiAnvil(AnvilInventory inventory, JavaPlugin plugin, Player player) {
        super(inventory, plugin, player);
    }

    @EventHandler
    public void onAnvil(PrepareAnvilEvent e) {

        if (e.getInventory().equals(inventory)) {
            onAnvilEvent(e);
        }
    }


    //todo check which raw slot and only take final output slot?
    @EventHandler
    public void onClick(InventoryClickEvent e){
        if (e.getInventory().equals(inventory)) {
            onOutputClick(e);
        }
    }

    public abstract void onOutputClick(InventoryClickEvent e);
    public abstract void onAnvilEvent(PrepareAnvilEvent e);
}

