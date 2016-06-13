package entities;

import spells.Element;

public class Player extends Character {
	private int level;

	public Player(String name) {
		super(name, 1, 10, 10, Element.NORMAL);
		this.level = 1;
	}

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

	public void levelUp() {
		level += 1;
		healthPoints += 2;
		maxHealthPoints += 2;
		mana += 1;
		maxMana += 1;
		System.out.printf("You've gained a level ! You are now level %d !\n", level);
		System.out.printf("You now have %s hp and %s mana\n", healthPoints+"/"+maxHealthPoints, mana+"/"+maxMana);
	}
}
