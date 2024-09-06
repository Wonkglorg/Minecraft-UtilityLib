package com.wonkglorg.utilitylib.builder.structure.function;

import java.util.function.Function;

public abstract class MaterialFunction() {
	private Material material;


	public abstract boolean hasFunction();

	public static MaterialFunction of(Material material) {
		return new MaterialFunction(material);
	}

	public static MaterialFunction AIR() {
		return new MaterialFunction(Material.air);
	}
}
