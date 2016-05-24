package spells;

import entities.Monster;

public abstract class Element {
	public abstract void applyEffect(Monster m);
	public abstract int manaCost();
}
