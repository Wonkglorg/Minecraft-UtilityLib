package com.wonkglorg.utilitylib.inventory.guis;

import com.wonkglorg.utilitylib.base.message.Message;
import com.wonkglorg.utilitylib.inventory.InventoryGUI;
import com.wonkglorg.utilitylib.inventory.MenuProfile;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.GrindstoneInventory;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public abstract class GuiGrindstone extends InventoryGUI {

    public GuiGrindstone(String name, Player player, JavaPlugin plugin) {
        super(Bukkit.createInventory(player, InventoryType.GRINDSTONE, Message.color(name)), plugin, player);
    }

    public GuiGrindstone(Component name, Player player, JavaPlugin plugin) {
        super(Bukkit.createInventory(player, InventoryType.GRINDSTONE, name), plugin, player);
    }

    public GuiGrindstone(GrindstoneInventory inventory, JavaPlugin plugin, MenuProfile profile) {
        super(inventory, plugin, profile);
    }

    public GuiGrindstone(GrindstoneInventory inventory, JavaPlugin plugin, Player player) {
        super(inventory, plugin, player);
    }

}
