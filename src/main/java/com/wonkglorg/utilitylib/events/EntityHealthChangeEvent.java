package com.wonkglorg.utilitylib.events;

import org.bukkit.Material;
import org.bukkit.WorldBorder;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EntityHealthChangeEvent extends EntityEvent implements Cancellable {

	private static final HandlerList HANDLER_LIST = new HandlerList();

	private final HealthChangeCause cause;
	private boolean cancelled;
	private final double oldHealthValue;
	private final double newHealthValue;

	public EntityHealthChangeEvent(@NotNull LivingEntity entity, double oldHealthValue,
			double newHealthValue, @Nullable EntityHealthChangeEvent.HealthChangeCause cause) {
		super(entity);
		this.oldHealthValue = oldHealthValue;
		this.newHealthValue = newHealthValue;
		this.cause = cause;
		this.cancelled = false;
	}

	/**
	 * Returns true if this event was fired due to an increase in health by healing.
	 *
	 * @return true if healed, false otherwise.
	 */
	public boolean isHealed() {
		return oldHealthValue < newHealthValue;
	}

	/**
	 * Returns true if this event was fired due to a decrease in health by damage.
	 *
	 * @return true if damaged, false otherwise.
	 */
	public boolean isDamaged() {
		return oldHealthValue > newHealthValue;
	}

	/**
	 * Gets the health change cause
	 *
	 * @return the cause
	 */
	public @Nullable EntityHealthChangeEvent.HealthChangeCause getCause() {
		return cause;
	}


	@Override
	public @NotNull HandlerList getHandlers() {
		return HANDLER_LIST;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancelled = cancel;
	}

	public enum HealthChangeCause {
		/**
		 * When a player regains health from regenerating due to Peaceful mode
		 * (difficulty=0)
		 */
		REGEN,
		/**
		 * When a player regains health from regenerating due to their hunger
		 * being satisfied
		 */
		SATIATED,
		/**
		 * When an animal regains health from eating consumables
		 */
		EATING,
		/**
		 * When an ender dragon regains health from an ender crystal
		 */
		ENDER_CRYSTAL,
		/**
		 * When a player is healed or damaged by a potion or spell
		 */
		MAGIC,
		/**
		 * When a player is healed over time by a potion or spell
		 */
		MAGIC_REGEN,
		/**
		 * When a wither is filling its health during spawning
		 */
		WITHER_SPAWN,
		/**
		 * When an entity is damaged or healed by the Wither potion effect
		 */
		WITHER,
		/**
		 * Any other reason not covered by the reasons above applies to both healing and damage
		 */
		CUSTOM,
		/**
		 * Damage caused by /kill command
		 * <p>
		 * Damage: {@link Float#MAX_VALUE}
		 */
		KILL,
		/**
		 * Damage caused by the World Border
		 * <p>
		 * Damage: {@link WorldBorder#getDamageAmount()}
		 */
		WORLD_BORDER,
		/**
		 * Damage caused when an entity contacts a block such as a Cactus,
		 * Dripstone (Stalagmite) or Berry Bush.
		 * <p>
		 * Damage: variable
		 */
		CONTACT,
		/**
		 * Damage caused when an entity attacks another entity.
		 * <p>
		 * Damage: variable
		 */
		ENTITY_ATTACK,
		/**
		 * Damage caused when an entity attacks another entity in a sweep attack.
		 * <p>
		 * Damage: variable
		 */
		ENTITY_SWEEP_ATTACK,
		/**
		 * Damage caused when attacked by a projectile.
		 * <p>
		 * Damage: variable
		 */
		PROJECTILE,
		/**
		 * Damage caused by being put in a block
		 * <p>
		 * Damage: 1
		 */
		SUFFOCATION,
		/**
		 * Damage caused when an entity falls a distance greater than 3 blocks
		 * <p>
		 * Damage: fall height - 3.0
		 */
		FALL,
		/**
		 * Damage caused by direct exposure to fire
		 * <p>
		 * Damage: 1
		 */
		FIRE,
		/**
		 * Damage caused due to burns caused by fire
		 * <p>
		 * Damage: 1
		 */
		FIRE_TICK,
		/**
		 * Damage caused due to a snowman melting
		 * <p>
		 * Damage: 1
		 */
		MELTING,
		/**
		 * Damage caused by direct exposure to lava
		 * <p>
		 * Damage: 4
		 */
		LAVA,
		/**
		 * Damage caused by running out of air while in water
		 * <p>
		 * Damage: 2
		 */
		DROWNING,
		/**
		 * Damage caused by being in the area when a block explodes.
		 * <p>
		 * Damage: variable
		 */
		BLOCK_EXPLOSION,
		/**
		 * Damage caused by being in the area when an entity, such as a
		 * Creeper, explodes.
		 * <p>
		 * Damage: variable
		 */
		ENTITY_EXPLOSION,
		/**
		 * Damage caused by falling into the void
		 * <p>
		 * Damage: 4 for players
		 */
		VOID,
		/**
		 * Damage caused by being struck by lightning
		 * <p>
		 * Damage: 5
		 */
		LIGHTNING,
		/**
		 * Damage caused by committing suicide.
		 * <p>
		 * <b>Note:</b> This is currently only used by plugins, default commands
		 * like /minecraft:kill use {@link #KILL} to damage players.
		 * <p>
		 * Damage: variable
		 */
		SUICIDE,
		/**
		 * Damage caused by starving due to having an empty hunger bar
		 * <p>
		 * Damage: 1
		 */
		STARVATION,
		/**
		 * Damage caused due to an ongoing poison effect
		 * <p>
		 * Damage: 1
		 */
		POISON,
		/**
		 * Damage caused by being hit by a falling block which deals damage
		 * <p>
		 * <b>Note:</b> Not every block deals damage
		 * <p>
		 * Damage: variable
		 */
		FALLING_BLOCK,
		/**
		 * Damage caused in retaliation to another attack by the Thorns
		 * enchantment.
		 * <p>
		 * Damage: 1-4 (Thorns)
		 */
		THORNS,
		/**
		 * Damage caused by a dragon breathing fire.
		 * <p>
		 * Damage: variable
		 */
		DRAGON_BREATH,
		/**
		 * Damage caused when an entity runs into a wall.
		 * <p>
		 * Damage: variable
		 */
		FLY_INTO_WALL,
		/**
		 * Damage caused when an entity steps on {@link Material#MAGMA_BLOCK}.
		 * <p>
		 * Damage: 1
		 */
		HOT_FLOOR,
		/**
		 * Damage caused when an entity is colliding with too many entities due
		 * to the maxEntityCramming game rule.
		 * <p>
		 * Damage: 6
		 */
		CRAMMING,
		/**
		 * Damage caused when an entity that should be in water is not.
		 * <p>
		 * Damage: 1
		 */
		DRYOUT,
		/**
		 * Damage caused from freezing.
		 * <p>
		 * Damage: 1 or 5
		 */
		FREEZE,
		/**
		 * Damage caused by the Sonic Boom attack from {@link org.bukkit.entity.Warden}
		 * <p>
		 * Damage: 10
		 */
		SONIC_BOOM;
	}
}

