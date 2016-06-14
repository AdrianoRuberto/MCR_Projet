package game.spells;

import game.entities.Character;

/**
 * The ConcreteSpell is a basic implementation of a Spell.
 */
public class ConcreteSpell implements Spell {

	private int damage;
	private int manaCost;

	public ConcreteSpell(int damage, int manaCost) {
		this.damage = damage;
		this.manaCost = manaCost;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void hit(Character from, Character to) {
		System.out.println("Normal spell");
		to.receiveDamage(damage);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getManaCost() {
		return manaCost;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}
