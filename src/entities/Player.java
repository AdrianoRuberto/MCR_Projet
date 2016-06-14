package entities;

import Element;

/**
 * Represents the player, adds level up consideration
 */
public class Player extends Character {
	private int level;

	/**
	 * Constructs the player
	 * @param name the player's name
	 */
	public Player(String name) {
		super(name, 1, 10, 10, Element.NORMAL);
		this.level = 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return String.format("%s: Level %d", name, level);
	}

	/**
	 * Gets the level of the player.
	 *
	 * @return the level of the player
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Level up the player. Changes its stats and restore a bit of its health points and mana.
	 */
	public void levelUp() {
		level += 1;
		healthPoints += 2;
		maxHealthPoints += 2;
		mana += 1;
		maxMana += 1;
		System.out.printf("You've gained a level ! You are now level %d !\n", level);
		System.out.printf(status());
	}

	/**
	 * Gets a string representation of the player's status :
	 * hp [cur]/[max] | mana [cur]/[max]
	 *
	 * @return the string representation of the player's status
	 */
	public String status() {
		return String.format("hp %s | mana %s", healthPoints + "/" + maxHealthPoints, mana + "/" + maxMana);
	}
}
