package entities;

import java.util.Random;

/**
 * Represents a monster the player will combat
 */
public abstract class Monster extends Character {
	private Random random;

	/**
	 * Monster constructor
	 * @param name The monster's name
	 * @param level Its level
	 * @param healthPoints Its full health points
	 */
	public Monster(String name, int level, int healthPoints) {
		super(name, level, healthPoints);
		random = new Random((name+level+healthPoints).hashCode());
	}

	/**
	 * Gives how many hit points the monster gives
	 * @return a random hit points damage
	 */
	public final int hit() {
		int min = minHit();
		int max = maxHit();
		return random.nextInt(max-min)+min;
	}

	/**
	 * Gives what is the minimal hit points
	 * @return the min hit points the monster can do
	 */
	protected abstract int minHit();

	/**
	 * Gives what is the maximal hit points
	 * @return the max hit points the monster can do
	 */
	protected abstract int maxHit();

	/**
	 * Gives how many weapons the monster is wielding
	 */
	protected int equippedWeapons() {
		return 0;
	}

	/**
	 * Gives how many weapons the monster can wield
	 */
	protected int maxEquippedWeapons() {
		return 1;
	}

	/**
	 * @return if the monster wears some armor
	 */
	protected boolean hasArmor() {
		return false;
	}

	/**
	 * @return The string representation of the monster
	 */
	public String toString() {
		return String.format("%s, lvl:%d, hp:%d", this.getName(), level, healthPoints);
	}

}
