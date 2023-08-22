package com.wonkglorg.utilitylib.manager.managers;

import com.wonkglorg.utilitylib.inventory.profile.MenuProfile;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

@SuppressWarnings("unused")
public final class ProfileManager<T extends MenuProfile> implements Manager{
    private T defaultMenu;
    private final Map<Player, T> utilityMap = new HashMap<>();

    /**
     * Creates a new ProfileManagerm add the default menu that should be asigned when a player has no menu
     *
     * @param defaultMenu
     */
    public ProfileManager(@NotNull T defaultMenu) {
        this.defaultMenu = defaultMenu;
    }

    /**
     * Sets the default MenuProfile to asign to a player when non could be found for the player.
     * Inherit all values passed with the class besides the owner being reasigned to the new player.
     * @param defaultMenu
     */
    public void setDefaultMenu(@NotNull T defaultMenu) {
        this.defaultMenu = defaultMenu;
    }

    @SuppressWarnings("unchecked")
    public T get(Player player) {
        T profile = (T) defaultMenu.clone();
        profile.setOwner(player);
        utilityMap.keySet().removeIf(Predicate.not(Player::isValid));
        return utilityMap.computeIfAbsent(player, k -> profile);
    }

    public void remove(Player player) {
        utilityMap.remove(player);
    }

    @Override
    public void onShutdown() {

    }

    @Override
    public void onStartup() {

    }
}