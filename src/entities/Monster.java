package entities;

import utils.Element;

import java.util.Random;

/**
 * Represents a monster the player will combat
 */
public abstract class Monster extends Character {
	private Random random;

	/**
	 * Monster constructor
	 * @param name The monster's name
	 * @param level Its getLevel
	 * @param healthPoints Its full health points
	 */
	public Monster(String name, int level, int healthPoints, Element elem) {
		super(name, level, healthPoints, 0, elem);
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
	 * @return how many weapons the monster is wielding
	 */
	protected int equippedWeapons() {
		return 0;
	}

	/**
	 * @return how many weapons the monster can wield
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
		return String.format("A wild %s at level %d", this.getName(), level);
	}

}
