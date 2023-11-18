package com.wonkglorg.utilitylib.manager.enchants;

import com.wonkglorg.utilitylib.base.Convert;
import com.wonkglorg.utilitylib.base.message.Message;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wonkglorg
 */
@SuppressWarnings("unused")
public abstract class CustomEnchantment extends Enchantment {
    private final String name;

    public CustomEnchantment(@NotNull NamespacedKey key, String name) {
        super(key);
        this.name = name;
    }

    public abstract List<Enchantment> conflicts();

    public abstract List<Material> enchantAble();

    public abstract boolean isTradeable();

    // HOW TO BEST IMPLEMENT THIS??????
    public ItemStack setLevel(ItemStack itemStack, Enchantment enchantment, int level) {

        ItemMeta meta = itemStack.getItemMeta();
        List<Component> componentOutput = new ArrayList<>();
        List<Component> components = meta.lore();

        for (Component component : components) {
            String text = Message.convertComponentToString(component);
            if (text.toLowerCase().contains(getName().toLowerCase())) {
                if (level == 0) {
                    continue;
                }

                componentOutput.add(displayName(level));
                meta.addEnchant(enchantment, level, true);
                continue;
            }
            componentOutput.add(component);

        }

        meta.lore(componentOutput);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public void increment(ItemStack itemStack, Enchantment enchantment) {
        setLevel(itemStack, enchantment, itemStack.getEnchantmentLevel(enchantment) + 1);
    }

    public void decrement(ItemStack itemStack, Enchantment enchantment) {
        setLevel(itemStack, enchantment, itemStack.getEnchantmentLevel(enchantment) - 1);
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public boolean conflictsWith(@NotNull Enchantment other) {
        return conflicts().contains(other);
    }

    @Override
    public boolean canEnchantItem(@NotNull ItemStack item) {
        return enchantAble().contains(item.getType());
    }

    @Override
    public @NotNull Component displayName(int level) {
        return Message.color(getName() + " " + Convert.toRoman(level));
    }

}