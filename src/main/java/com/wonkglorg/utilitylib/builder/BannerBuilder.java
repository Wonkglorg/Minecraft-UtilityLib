package com.wonkglorg.utilitylib.builder;

import net.kyori.adventure.text.Component;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Wonkglorg
 */
@SuppressWarnings("unused")
public final class BannerBuilder extends ItemBuilder{
	
	private final List<BannerLayer> bannerLayerList = new ArrayList<>();
	
	/**
	 * Instantiates a new Banner builder.
	 *
	 * @param color the color
	 * @param amount the amount
	 * @param name the name
	 * @param lore the lore
	 * @param durability the durability
	 * @param meta the meta
	 * @param flags the flags
	 * @param enchantments the enchantments
	 */
	public BannerBuilder(@NotNull DyeColor color,
						 int amount,
						 Component name,
						 List<Component> lore,
						 int durability,
						 ItemMeta meta,
						 List<ItemFlag> flags,
						 Map<Enchantment, Integer> enchantments) {
		super(Material.valueOf(color.name() + "_BANNER"), amount, name, lore, durability, meta, flags, enchantments);
	}
	
	/**
	 * Instantiates a new Banner builder.
	 *
	 * @param color the color
	 */
	public BannerBuilder(@NotNull DyeColor color) {
		super(Material.valueOf(color.name() + "_BANNER"));
	}
	
	/**
	 * Instantiates a new Banner builder.
	 *
	 * @param color the color
	 * @param itemFlags the item flags
	 */
	public BannerBuilder(@NotNull DyeColor color, ItemFlag... itemFlags) {
		super(Material.valueOf(color.name() + "_BANNER"), itemFlags);
	}
	
	/**
	 * Instantiates a new Banner builder.
	 *
	 * @param color the color
	 * @param name the name
	 */
	public BannerBuilder(@NotNull DyeColor color, Component name) {
		super(Material.valueOf(color.name() + "_BANNER"), name);
	}
	
	/**
	 * Instantiates a new Banner builder.
	 *
	 * @param color the color
	 * @param description the description
	 */
	public BannerBuilder(@NotNull DyeColor color, List<Component> description) {
		super(Material.valueOf(color.name() + "_BANNER"), description);
	}
	
	/**
	 * Instantiates a new Banner builder.
	 *
	 * @param color the color
	 * @param amount the amount
	 */
	public BannerBuilder(@NotNull DyeColor color, int amount) {
		super(Material.valueOf(color.name() + "_BANNER"), amount, (String) null);
	}
	
	/**
	 * Instantiates a new Banner builder.
	 *
	 * @param color the color
	 * @param amount the amount
	 * @param name the name
	 */
	public BannerBuilder(@NotNull DyeColor color, int amount, Component name) {
		super(Material.valueOf(color.name() + "_BANNER"), amount, name);
	}
	
	/**
	 * Instantiates a new Banner builder.
	 *
	 * @param color the color
	 * @param amount the amount
	 * @param name the name
	 * @param description the description
	 */
	public BannerBuilder(@NotNull DyeColor color, int amount, Component name, List<Component> description) {
		super(Material.valueOf(color.name() + "_BANNER"), amount, name, description);
	}
	
	/**
	 * Add layer banner builder.
	 *
	 * @param color the color
	 * @param patternType the pattern type
	 * @return the banner builder
	 */
	public BannerBuilder addLayer(@NotNull DyeColor color, PatternType patternType) {
		bannerLayerList.add(new BannerLayer(color, patternType));
		return this;
	}
	
	/**
	 * Replace color at banner builder.
	 *
	 * @param index the index
	 * @param color the color
	 * @return the banner builder
	 */
	public BannerBuilder replaceColorAt(int index, @NotNull DyeColor color) {
		if(index < bannerLayerList.size()){
			bannerLayerList.set(index, new BannerLayer(Objects.requireNonNull(color), bannerLayerList.get(index).getPatternType()));
		}
		return this;
	}
	
	/**
	 * Replace pattern at banner builder.
	 *
	 * @param index the index
	 * @param type the type
	 * @return the banner builder
	 */
	public BannerBuilder replacePatternAt(int index, @NotNull PatternType type) {
		if(index < bannerLayerList.size()){
			bannerLayerList.set(index, new BannerLayer(bannerLayerList.get(index).getDyeColor(), Objects.requireNonNull(type)));
		}
		return this;
	}
	
	@Override
	public ItemStack build() {
		List<Pattern> patterns = new ArrayList<>();
		for(BannerLayer bannerLayer : bannerLayerList){
			patterns.add(new Pattern(bannerLayer.getDyeColor(), bannerLayer.getPatternType()));
		}
		super.setItem(new ItemStack(getMaterial(), getAmount()));
		BannerMeta meta = (BannerMeta) super.getItem().getItemMeta();
		meta.setPatterns(patterns);
		super.setMeta(meta);
		return super.build();
	}
	
	/**
	 * Removes last pattern layer
	 *
	 * @return instance of banner builder
	 */
	public BannerBuilder removeLastLayer() {
		bannerLayerList.remove(bannerLayerList.size() - 1);
		return this;
	}
	
	/**
	 * Removes first pattern layer
	 *
	 * @return instance of banner builder
	 */
	public BannerBuilder removeFirstLayer() {
		bannerLayerList.remove(0);
		return this;
	}
	
	/**
	 * Removes banner layer at the selected index
	 *
	 * @param index the index
	 * @return instance of banner builder
	 */
	public BannerBuilder removeLayerAt(int index) {
		if(index < bannerLayerList.size()){
			bannerLayerList.remove(index);
		}
		return this;
	}
	
	private static class BannerLayer{
		private DyeColor dyeColor;
		private PatternType patternType;
		
		/**
		 * Instantiates a new Banner layer.
		 *
		 * @param dyeColor the dye color
		 * @param patternType the pattern type
		 */
		public BannerLayer(DyeColor dyeColor, PatternType patternType) {
			this.dyeColor = dyeColor;
			this.patternType = patternType;
		}
		
		/**
		 * Gets dye color.
		 *
		 * @return the dye color
		 */
		public DyeColor getDyeColor() {
			return dyeColor;
		}
		
		/**
		 * Sets dye color.
		 *
		 * @param dyeColor the dye color
		 */
		public void setDyeColor(DyeColor dyeColor) {
			this.dyeColor = Objects.requireNonNull(dyeColor);
		}
		
		/**
		 * Gets pattern type.
		 *
		 * @return the pattern type
		 */
		public PatternType getPatternType() {
			return patternType;
		}
		
		/**
		 * Sets pattern type.
		 *
		 * @param patternType the pattern type
		 */
		public void setPatternType(PatternType patternType) {
			this.patternType = Objects.requireNonNull(patternType);
		}
	}
	
}