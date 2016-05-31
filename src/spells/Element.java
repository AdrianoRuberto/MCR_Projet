package spells;

import entities.Character;

public abstract class Element {
	public void applyEffect(Character from, Character to) {
		from.removeMana(manaCost());
		to.receiveDamage(damage());
	}
	public abstract int manaCost();
	public abstract int damage();
	public abstract Element effectiveAgainst();
}
