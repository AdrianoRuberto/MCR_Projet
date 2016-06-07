package entities;

import spells.Element;

public abstract class Character {
	protected String name;
	protected int level;
	protected int healthPoints;
	private int mana;

	public Character(String name, int level, int healthPoints) {
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

	public void removeMana(int n) {
		mana -= n;
	}

	public void receiveDamage(int damage, Element e) {
		healthPoints -= damage;
	}

}
