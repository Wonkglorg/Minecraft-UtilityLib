package com.wonkglorg.utilitylib.inventory;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum Slot {
	HELMET((player, item) -> matches(item, player.getInventory().getHelmet()),
			(player, item) -> getItemsFromSlot(player.getInventory().getHelmet(), item)),
	CHESTPLATE((player, item) -> matches(item, player.getInventory().getChestplate()),
			(player, item) -> getItemsFromSlot(player.getInventory().getChestplate(), item)),
	LEGGINGS((player, item) -> matches(item, player.getInventory().getLeggings()),
			(player, item) -> getItemsFromSlot(player.getInventory().getLeggings(), item)),
	BOOTS((player, item) -> matches(item, player.getInventory().getBoots()),
			(player, item) -> getItemsFromSlot(player.getInventory().getBoots(), item)),
	MAINHAND((player, item) -> matches(item, player.getInventory().getItemInMainHand()),
			(player, item) -> getItemsFromSlot(player.getInventory().getItemInMainHand(), item)),
	OFFHAND((player, item) -> matches(item, player.getInventory().getItemInOffHand()),
			(player, item) -> getItemsFromSlot(player.getInventory().getItemInOffHand(), item)),
	INVENTORY(Slot::isInInventory, Slot::getItemsFromInventory),
	HOTBAR((player, item) -> matchesInSlots(player, item, 36, 37, 38, 39, 40, 41, 42, 43, 44),
			(player, item) -> getItemsFromSlots(player, item, 36, 37, 38, 39, 40, 41, 42, 43, 44));

	private final BiFunction<Player, ItemStack, Boolean> slotChecker;
	private final BiFunction<Player, ItemStack, List<ItemStack>> itemGetter;

	Slot(BiFunction<Player, ItemStack, Boolean> slotChecker,
			BiFunction<Player, ItemStack, List<ItemStack>> itemGetter) {
		this.slotChecker = slotChecker;
		this.itemGetter = itemGetter;
	}

	private static boolean isInInventory(Player player, ItemStack item) {
		return IntStream.range(0, player.getInventory().getSize())
				.anyMatch(slot -> matches(item, player.getInventory().getItem(slot)));
	}

	private static boolean matchesInSlots(Player player, ItemStack item, int... slots) {
		return IntStream.of(slots).anyMatch(slot -> matches(item,
        player.getInventory().getItem(slot)));
	}

	private static List<ItemStack> getItemsFromSlot(ItemStack slotItem, ItemStack item) {
		return matches(slotItem, item) ? List.of(slotItem) : List.of();
	}

	private static List<ItemStack> getItemsFromSlots(Player player, ItemStack item, int... slots) {
		return IntStream.of(slots).mapToObj(slot -> player.getInventory().getItem(slot))
				.filter(slotItem -> matches(slotItem, item)).collect(Collectors.toList());
	}

	private static List<ItemStack> getItemsFromInventory(Player player, ItemStack item) {
		return IntStream.range(0, player.getInventory().getSize())
				.mapToObj(slot -> player.getInventory().getItem(slot))
				.filter(slotItem -> matches(slotItem, item)).collect(Collectors.toList());
	}

	private static boolean matches(ItemStack item1, ItemStack item2) {
		return item1 != null && item1.isSimilar(item2);
	}

	public boolean isItemInSlot(Player player, ItemStack item) {
		return slotChecker.apply(player, item);
	}

	public List<ItemStack> getValidItems(Player player, ItemStack item) {
		return itemGetter.apply(player, item);
	}
}