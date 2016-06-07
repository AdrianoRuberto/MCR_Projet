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

	public int getMana() {
		return mana;
	}

	public void receiveDamage(int damage, Element element) {
		last = element;
		healthPoints -= damage;
	}

	public Optional<Element> getLastElement() {
		return Optional.ofNullable(last);
	}
}
