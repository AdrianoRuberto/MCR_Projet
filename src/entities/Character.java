package entities;

import spells.Element;

public abstract class Character {
	protected String name;
	protected int level;
	protected int hitpoints;
	private int mana;

	public Character(String name, int level, int hitpoints) {
		this.name = name;
		this.level = level;
		this.hitpoints = hitpoints;
	}

	public boolean isAlive() {
		return hitpoints > 0;
	}

	public String getName() {
		return name;
	}

	public void removeMana(int n) {
		mana -= n;
	}

	public void receiveDamage(int damage, Element e) {
		hitpoints -= damage;
	}

}
