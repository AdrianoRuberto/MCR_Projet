package entities;

import spells.Element;

/**
 * Represents a role played character.
 */
public abstract class Character {
	protected final String name;
	private final Element typeElement;
	protected int level;
	protected int healthPoints;
	protected int maxHealthPoints;
	protected int mana, maxMana;

	/**
	 * Character constructor
	 *
	 * @param name         The character's name
	 * @param level        The character's level
	 * @param healthPoints The character's health points
	 */
	public Character(String name, int level, int healthPoints, int manaPoints, Element element) {
		if (level <= 0) {
			throw new IllegalArgumentException("Level should be > 0");
		} else if (healthPoints <= 0) {
			throw new IllegalArgumentException("HP should be > 0");
		}
		this.name = name;
		this.level = level;
		this.healthPoints = healthPoints;
		this.maxHealthPoints = healthPoints;
		this.mana = manaPoints;
		this.maxMana = manaPoints;
		this.typeElement = element;
	}

	/**
	 * @return whether the character is alive
	 */
	public boolean isAlive() {
		return healthPoints > 0;
	}

	/**
	 * @return the character name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Mana getter
	 *
	 * @return the mana status
	 */
	public int getMana() {
		return mana;
	}

	/**
	 * Sets the mana value
	 *
	 * @param mana the new amount of mana
	 */
	public void setMana(int mana) {
		if (mana > maxMana)
			throw new IllegalArgumentException("Exceeding the maximum mana");
		else if (mana < 0)
			throw new IllegalArgumentException("Mana should be positive");
		this.mana = mana;
	}

	public int getMaxMana() {
		return maxMana;
	}

	public int getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(int hp) {
		this.healthPoints = hp;
	}

	public int getMaxHealthPoints() {
		return maxHealthPoints;
	}

	/**
	 * Gives damage to the character
	 *
	 * @param damage the damage amount received
	 */
	public void receiveDamage(int damage) {
		healthPoints -= damage;
		System.out.println(name + " is hit (" + damage + "dmg), " + healthPoints + " hp remaining");
	}

	/**
	 * @return the element type of the monster
	 */
	public Element getElementType() {
		return typeElement;
	}
}
