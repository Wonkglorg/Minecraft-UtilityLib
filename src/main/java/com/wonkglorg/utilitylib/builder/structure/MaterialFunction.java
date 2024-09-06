package com.wonkglorg.utilitylib.builder.structure;

import java.util.function.BiConsumer;
import java.util.function.Function;

public record MaterialFunction(Material material, BiConsumer<Player,Itemstack>) {

	public static MaterialFunction of(Material material){
		return new MaterialFunction(material);
	}

	public static MaterialFunction AIR(){
		return new MaterialFunction(Material.air);
	}
}
