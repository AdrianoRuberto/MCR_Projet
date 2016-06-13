package entities;

import spells.Element;

import java.util.Optional;

public abstract class Character {
	protected String name;
	protected int level;
	protected int healthPoints;
	private Element last;
	private int mana;

	public Character(String name, int level, int healthPoints) {
		if (level <= 0) {
			throw new IllegalArgumentException("Level should be > 0");
		} else if (healthPoints <= 0) {
			throw new IllegalArgumentException("HP should be > 0");
		}
		this.name = name;
		this.level = level;
		this.healthPoints = healthPoints;
	}

	public boolean isAlive() {
		return healthPoints > 0;
	}

	public String getName() {
		return name;
	}


	/**
	 * Prints itself in the System.out
	 */
	public void print() {
		System.out.println(this.toString());
	}

	public void removeMana(int n) {
		mana -= n;
	}

	public int getMana() {
		return mana;
	}

	public void receiveDamage(int damage, Element element) {
		last = element;
		receiveDamage(damage);
	}

	public void receiveDamage(int damage) {
		healthPoints -= damage;
		System.out.println(name + " is hit (" + damage + "), " + healthPoints + " remaining");
	}

	public Optional<Element> getLastElement() {
		return Optional.ofNullable(last);
	}
}
