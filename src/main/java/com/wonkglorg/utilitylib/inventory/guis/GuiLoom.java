package com.wonkglorg.utilitylib.inventory.guis;

import com.wonkglorg.utilitylib.base.message.Message;
import com.wonkglorg.utilitylib.inventory.InventoryGUI;
import com.wonkglorg.utilitylib.inventory.MenuProfile;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.LoomInventory;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public abstract class GuiLoom extends InventoryGUI {

    public GuiLoom(String name, Player player, JavaPlugin plugin) {
        super(Bukkit.createInventory(player, InventoryType.LOOM, Message.color(name)), plugin, player);
    }

    public GuiLoom(Component name, Player player, JavaPlugin plugin) {
        super(Bukkit.createInventory(player, InventoryType.LOOM, name), plugin, player);
    }

    public GuiLoom(LoomInventory inventory, JavaPlugin plugin, MenuProfile profile) {
        super(inventory, plugin, profile);
    }

    public GuiLoom(LoomInventory inventory, JavaPlugin plugin, Player player) {
        super(inventory, plugin, player);
    }

    public LoomInventory getInventory(){
        return (LoomInventory) super.getInventory();
    }
}

