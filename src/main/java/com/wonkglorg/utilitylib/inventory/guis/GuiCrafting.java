package com.wonkglorg.utilitylib.inventory.guis;

import com.wonkglorg.utilitylib.base.message.Message;
import com.wonkglorg.utilitylib.inventory.InventoryGUI;
import com.wonkglorg.utilitylib.inventory.MenuProfile;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.plugin.java.JavaPlugin;

    @SuppressWarnings("unused")
    public abstract class GuiCrafting extends InventoryGUI {

        public GuiCrafting(String name, Player player, JavaPlugin plugin) {
            super(Bukkit.createInventory(player, InventoryType.CRAFTING, Message.color(name)), plugin, player);
        }

        public GuiCrafting(Component name, Player player, JavaPlugin plugin) {
            super(Bukkit.createInventory(player, InventoryType.CRAFTING, name), plugin, player);
        }

        public GuiCrafting(CraftingInventory inventory, JavaPlugin plugin, MenuProfile profile) {
            super(inventory, plugin, profile);
        }

        public GuiCrafting(CraftingInventory inventory, JavaPlugin plugin, Player player) {
            super(inventory, plugin, player);
        }

        public CraftingInventory getInventory(){
            return (CraftingInventory) super.getInventory();
        }

    }
